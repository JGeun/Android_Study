package jgeun.study.hilttest.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import jgeun.study.hilttest.R
import jgeun.study.hilttest.data.MyRepository
import jgeun.study.hilttest.di.qualifier.ActivityHash
import jgeun.study.hilttest.di.qualifier.AppHash
import jgeun.study.hilttest.ui.second.SecondActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val activityViewModel by activityViewModels<MainViewModel>()
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

        view.findViewById<Button>(R.id.button_activity).setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.button_fragment).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

        Log.d("MainFragment", "appHash: ${applicationHash}")
        Log.d("MainFragment", "activityHash: ${activityHash}")
        Log.d("MainFragment", "viewModel: ${viewModel.getRepositoryHash()}")
        Log.d("MainFragment", "activityViewModel: ${activityViewModel.getRepositoryHash()}")
    }
}