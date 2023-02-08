package com.calculator.ui.calculator_window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import net.objecthunter.exp4j.ExpressionBuilder
import com.calculator.ui.calculator_app.CalculatorViewModel


class SimpleViewModel : CalculatorViewModel {


    private val regexInput = Regex("""[0-9.*/+-×÷]*""")
    private val regexResult = Regex("""\.0+$""")

    private fun replaceSpecSymbols(text: TextFieldValue) =
        text.copy(text = text.text.replace('*', '×').replace('/', '÷'))

    private fun replaceSpecSymbolsBack(text: String) =
        text.replace('×', '*').replace('÷', '/')


    private val _result = MutableStateFlow("")

    private val scope = CoroutineScope(Dispatchers.Main + Job())

//    init {
//        scope.launch(Dispatchers.IO) {
//            _text.collectLatest {
//                println(it)
//                println(it.selection.collapsed)
//                println(it.selection.length)
//                println(it.selection.start)
//                println(it.selection.end)
//            }
//        }
//    }

    private val _text = MutableStateFlow(TextFieldValue(""))

    @Composable
    override fun getText(): TextFieldValue = _text.collectAsState().value

    override fun setText(text: TextFieldValue) {
        scope.launch(Dispatchers.IO) {
            if (regexInput.matches(text.text)) {
                _text.value = replaceSpecSymbols(text)
                _result.value =
                    runCatching {
                        ExpressionBuilder(replaceSpecSymbolsBack(_text.value.text)).build().evaluate().toString()
                    }.getOrNull()?.replace(regexResult, "") ?: ""
            }
        }
    }

    @Composable
    override fun getResult(): String = _result.collectAsState().value

    override fun equal() {
        scope.launch(Dispatchers.IO) {
            if (_result.value.isBlank()) _result.value = "Error"
            else _text.value = TextFieldValue(text = _result.value, selection = TextRange(_result.value.length))

        }
    }

    fun onClose() = scope.cancel()

}