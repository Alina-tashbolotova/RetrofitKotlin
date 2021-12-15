package com.example.retrofitkotlin.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes Id: Int
) : Fragment(Id) {
    protected abstract val binding: ViewBinding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        setupListeners()
        setupRequest()
        setupObservers()
    }

    abstract fun initialization()

    abstract fun setupListeners()

    abstract fun setupRequest()

    abstract fun setupObservers()



}