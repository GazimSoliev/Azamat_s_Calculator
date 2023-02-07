package ui.calculator_window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import ui.calculator_app.CalculatorViewModel


class SimpleViewModel : CalculatorViewModel {

    private val scope = CoroutineScope(Dispatchers.Main + Job())

    private val _text = MutableStateFlow(TextFieldValue())

    init {
        scope.launch(Dispatchers.IO) {
            _text.collectLatest {
                println(it)
            }
        }
    }

    @Composable
    override fun getText(): TextFieldValue = _text.collectAsState().value

    override fun setText(text: TextFieldValue) {
        scope.launch(Dispatchers.IO) {
            _text.value = text
        }
    }

    fun onClose() = scope.cancel()

}