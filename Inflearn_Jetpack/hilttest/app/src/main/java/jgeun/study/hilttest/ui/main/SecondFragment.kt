package jgeun.study.hilttest.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import jgeun.study.hilttest.R
import jgeun.study.hilttest.data.MyRepository
import jgeun.study.hilttest.di.qualifier.ActivityHash
import jgeun.study.hilttest.di.qualifier.AppHash
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : Fragment(R.layout.fragment_second) {

    private val viewModel by viewModels<MainViewModel>()


    @Inject
    lateinit var repository: MyRepository

    @AppHash
    @Inject
    lateinit var applicationHash: String

    @ActivityHash
    @Inject
    lateinit var activityHash: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_mainFragment)
        }

        Log.d("SecondFragment", "applicationHash: ${applicationHash}")
        Log.d("SecondFragment", "activityHash: ${activityHash}")
        Log.d("SecondFragment", "viewModel: ${viewModel.getRepositoryHash()}")
    }
}