package ui.calculator_app

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.theme.AppTheme

@Composable
@Preview
fun CalculatorApp(viewModel: CalculatorViewModel) {
    AppTheme {
        Surface(Modifier.fillMaxSize()) {
            CalculatorInterface(modifier = Modifier.fillMaxSize(), viewModel.getText(), viewModel::setText)
        }
    }
}