package com.example.ltech.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ltech.R
import com.example.ltech.databinding.SplashFragmentBinding
import com.example.ltech.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val binding by viewBinding(SplashFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToMainScreen()
    }
    private fun navigateToMainScreen() {

        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                logoAnimation()
                navigateToMainFragment()
            }
        }
    }

    private fun navigateToMainFragment() {
        val action = SplashFragmentDirections.actionSplashFragmentToAuthenticationFragment()
        view?.findNavController()?.safeNavigate(action)
    }

    private suspend fun logoAnimation() {
        binding.imageViewSplashLogo.animate().apply {
            duration = 2000
            rotationYBy(360f)
        }.start()
        delay(2000)
    }
}