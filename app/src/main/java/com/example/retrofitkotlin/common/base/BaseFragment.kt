package com.example.retrofitkotlin.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.retrofitkotlin.presentation.state.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val binding: ViewBinding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        setupListeners()
        setupRequest()
        setupObservers()
    }

    protected open fun initialization(){

    }

    protected open fun setupListeners(){

    }

    protected open fun setupRequest(){

    }

    protected open fun setupObservers(){

    }

    protected fun <T> StateFlow<UIState<T>>.subscribe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: (UIState<T>) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                this@subscribe.collect {
                    action(it)
                }
            }
        }
    }


}