package ui.calculator_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue

interface CalculatorViewModel {

    @Composable
    fun getText(): TextFieldValue

    @Composable
    fun getResult(): String

    fun setText(text: TextFieldValue)

    fun equal()

}