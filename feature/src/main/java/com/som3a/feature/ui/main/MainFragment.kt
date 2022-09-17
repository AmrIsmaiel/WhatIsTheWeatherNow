package com.som3a.feature.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.som3a.base.BaseFragment
import com.som3a.common.CapturedImage
import com.som3a.common.getCapturedImage
import com.som3a.common.haveNetworkConnection
import com.som3a.feature.R
import com.som3a.feature.databinding.FragmentMainBinding
import com.som3a.feature.ui.contract.MainContract
import com.som3a.feature.ui.details.DetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val createLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    private val viewModel: MainViewModel by viewModels()
    private var photoURI: Uri? = null
    private var photoResult = CapturedImage()

    override fun createView(savedInstanceState: Bundle?) {
        binding.rvList.adapter = adapter
        initObservers()
        getMoviesList()
        binding.tryAgainButton.setOnClickListener { getMoviesList() }
        binding.addNewPhoto.setOnClickListener {

        }
    }

    private val requestCameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var granted = true
            permissions.entries.forEach {
                if (it.value == false)
                    granted = false
            }
            if (granted) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                photoResult = requireActivity().getCapturedImage()
                photoURI = photoResult.uri
                if (photoURI != null) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                }
                startCameraIntent.launch(intent)
            }
        }

    private val startCameraIntent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    if (photoURI != null) {
                        photoResult.absPath?.let { startDetailsFragmet(it) }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    private fun startDetailsFragmet(absPath: String) {
        val args = DetailsFragmentArgs(path = absPath).toBundle()
        findNavController().navigate(R.id.detailFragment, args)
    }

    private fun captureImage() = requestCameraPermission.launch(
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    private fun getMoviesList() =
        viewModel.setEvent(MainContract.Event.OnFetchWeatherList)

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.viewState) {
                        is MainContract.WeatherState.Idle -> {
                            binding.emptyState.isVisible = false
                            binding.progressBar.isVisible = false
                        }
                        is MainContract.WeatherState.Loading -> {
                            binding.emptyState.isVisible = false
                            binding.progressBar.isVisible = true
                        }
                        is MainContract.WeatherState.WeatherListSuccess -> {
                            val data = state.weatherDetailsList
                            adapter.submitList(data)
                            binding.emptyState.isVisible = data.isEmpty()
                            binding.progressBar.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            binding.emptyState.isVisible = true
                            if (!requireContext().haveNetworkConnection()) {
                                Toast.makeText(
                                    requireContext(),
                                    "No internet connection, please try again later",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                }
            }
        }
    }
}