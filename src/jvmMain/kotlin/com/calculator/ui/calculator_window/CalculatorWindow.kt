package com.calculator.ui.calculator_window

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import com.calculator.ui.calculator_app.CalculatorApp
import com.calculator.ui.theme.md_theme_light_tertiary

@Composable
fun ApplicationScope.CalculatorWindow() {
    val viewModel = remember { SimpleViewModel() }
    Window(
        onCloseRequest = {
            viewModel.onClose()
            exitApplication()
        },
        title = "Calculator",
        state = WindowState(size = DpSize(width = 420.dp, height = 800.dp)),
        resizable = false,
        icon = Icons.Default.Calculate.let { image ->
            rememberVectorPainter(
                defaultWidth = image.defaultWidth,
                defaultHeight = image.defaultHeight,
                viewportWidth = image.viewportWidth,
                viewportHeight = image.viewportHeight,
                name = image.name,
                tintColor = md_theme_light_tertiary,
                tintBlendMode = image.tintBlendMode,
                autoMirror = image.autoMirror,
                content = { _, _ -> RenderVectorGroup(group = image.root) }
            )
        }
    ) {
        CalculatorApp(viewModel)
    }
}
