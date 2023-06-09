package com.calculator.ui.calculator_app

import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageBitmap
import com.calculator.resRepository.Resources
import com.calculator.ui.theme.AppTheme

@Composable
@Preview
fun CalculatorApp(viewModel: CalculatorViewModel) {
    val imageBitmap = remember {
        Resources.getAndUseInputStream("img.png") {
            it?.let(::loadImageBitmap)
        }
    }
    AppTheme {
        Surface(Modifier.fillMaxSize()) {
            var showPhoto by remember { mutableStateOf(false) }
            AnimatedVisibility(!showPhoto, enter = fadeIn(), exit = fadeOut()) {
                CalculatorInterface(
                    modifier = Modifier.fillMaxSize(),
                    result = viewModel.getResult(),
                    text = viewModel.getText(),
                    setText = viewModel::setText,
                    equal = viewModel::equal
                ) { showPhoto = true }
            }
            AnimatedVisibility(
                showPhoto,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()
            ) {
                ShowAzamatPhoto(imageBitmap) { showPhoto = false }
            }
        }
    }
}