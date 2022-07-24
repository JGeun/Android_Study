package jgeun.study.hilttest.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jgeun.study.hilttest.data.MyRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MyRepository
): ViewModel() {

    fun getRepositoryHash() = repository.hashCode().toString()
}