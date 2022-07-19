package jgeun.study.datatransfer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment(R.layout.fragment_first) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
            view.findViewById<ImageView>(R.id.imageView).setImageURI(it)
        }

        val getStartActivityForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            activityResult.data?.let { intent ->
                intent.extras?.let { bundle ->
                    Log.d("FirstFragment", "result: : ${bundle.getString("data", "world")}")
                }
            }
        }

        view.findViewById<Button>(R.id.button).setOnClickListener {
            // MIME TYPE
            Intent(requireActivity(), ResultActivity::class.java).apply {
                getStartActivityForResult.launch(this)
            }
        }
    }
}