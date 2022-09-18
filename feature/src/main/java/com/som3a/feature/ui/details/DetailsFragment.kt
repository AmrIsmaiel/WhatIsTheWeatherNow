package com.som3a.feature.ui.details

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.som3a.base.BaseFragment
import com.som3a.common.Utils.loadImageFromStorage
import com.som3a.domain.entity.WeatherRequest
import com.som3a.feature.databinding.FragmentDetailsBinding
import com.som3a.feature.model.WeatherDetailsUiModel
import com.som3a.feature.ui.contract.MainContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File


@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val mViewModel: WeatherDetailsViewModel by viewModels()
    private val args by navArgs<DetailsFragmentArgs>()

    override val createLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun createView(savedInstanceState: Bundle?) {
        initObservers()
        getWeatherDetails()
        binding.backButton.setOnClickListener{ findNavController().popBackStack() }
        binding.shareTweeter.setOnClickListener { shareIntent(args.path) }
        binding.shareFaceBook.setOnClickListener { shareIntent(args.path) }
    }

    private fun getWeatherDetails() =
        mViewModel.setEvent(
            MainContract.Event.OnFetchWeatherDetails(
                WeatherRequest(
                    args.lat,
                    args.lon
                )
            )
        )

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.uiState.collect {
                    when (val state = it.viewState) {
                        is MainContract.WeatherState.Idle -> {
                            binding.progressBar.isVisible = false
                            binding.image.isVisible = false
                        }
                        is MainContract.WeatherState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.image.isVisible = false
                        }
                        is MainContract.WeatherState.WeatherSuccess -> {
                            binding.image.isVisible = true
                            setData(state.weatherDetails)
                            binding.progressBar.isVisible = false
                            binding.shareTweeter.isVisible = true
                            binding.shareFaceBook.isVisible = true
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            binding.image.isVisible = false
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setData(weatherDetails: WeatherDetailsUiModel) {
        val bitmap = loadImageFromStorage(weatherDetails.photoPath!!)?.let { Bitmap.createBitmap(it) }
        val canvas = bitmap?.let { Canvas(it) }
        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 10f
        val weather =
            "Feels like ${weatherDetails.feelsLike}, Temperature: ${weatherDetails.temp}, overall: ${weatherDetails.main}"
        canvas?.drawText(weather, 0f, 0f, paint)
    }

    private fun shareIntent(imagePath: String): Intent {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "*/*"
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(File(imagePath)))
        return sharingIntent
    }

}