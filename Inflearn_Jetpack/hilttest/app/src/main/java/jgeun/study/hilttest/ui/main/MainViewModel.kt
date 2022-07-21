package jgeun.study.hilttest.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import jgeun.study.hilttest.data.MyRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: MyRepository
): ViewModel() {

    fun getRepositoryHash() = repository.hashCode().toString()
}