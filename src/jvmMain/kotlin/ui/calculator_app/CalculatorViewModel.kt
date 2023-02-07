package ui.calculator_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.text.input.TextFieldValue

interface CalculatorViewModel {
    @Composable
    fun getText(): TextFieldValue

    fun setText(text: TextFieldValue)
}