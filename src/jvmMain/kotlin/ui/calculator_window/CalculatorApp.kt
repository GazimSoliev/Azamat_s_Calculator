package ui.calculator_window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import ui.calculator_app.CalculatorApp

@Composable
fun ApplicationScope.CalculatorWindow() {
    val viewModel = remember { SimpleViewModel() }
    Window(
        onCloseRequest = {
            viewModel.onClose()
            exitApplication()
        },
        title = "Calculator",
        state = WindowState(size = DpSize(width = 500.dp, height = 900.dp)),
        resizable = false
    ) {
        CalculatorApp(viewModel)
    }
}
