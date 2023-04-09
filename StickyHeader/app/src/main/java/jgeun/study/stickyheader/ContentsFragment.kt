package jgeun.study.stickyheader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jgeun.study.stickyheader.databinding.FragmentContentsBinding

class ContentsFragment : Fragment() {

	private lateinit var binding: FragmentContentsBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentContentsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(binding.categoryContents) {
			setHasFixedSize(true)
			adapter = ContentsAdapter()
		}

	}

	override fun onDestroyView() {
		super.onDestroyView()
	}

	companion object {

		@JvmStatic
		fun newInstance() = ContentsFragment()
	}
}