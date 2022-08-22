package jgeun.study.datatransfer

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map[Manifest.permission.ACCESS_FINE_LOCATION]!!) {
            Toast.makeText(requireContext(), "ACCESS_FINE_LOCATION 성공", Toast.LENGTH_SHORT).show()
        }

        if (map[Manifest.permission.ACCESS_COARSE_LOCATION]!!) {
            Toast.makeText(requireContext(), "ACCESS_COARSE_LOCATION 성공", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            requestPermission.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        }
    }
}