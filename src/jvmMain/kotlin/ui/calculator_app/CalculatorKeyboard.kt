package ui.calculator_app

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun Keyboard(modifier: Modifier, text: TextFieldValue, setText: (TextFieldValue) -> Unit) {
    Box(modifier, contentAlignment = Alignment.Center) {
        val horizontalArrangement = Arrangement.spacedBy(8.dp)
        val cellModifier: RowScope.() -> Modifier = {
            Modifier.weight(1f).fillMaxHeight()
        }
        val rowModifier: ColumnScope.() -> Modifier = {
            Modifier.weight(1f)
        }
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                IconFilledTonalButton(modifier = cellModifier(), Icons.Default.Image, "Open image") { }
                Spacer(modifier = cellModifier())
                Spacer(modifier = cellModifier())
                IconFilledTonalButton(modifier = cellModifier(), Icons.Default.Backspace, "Backspace") {
//                    setText(text.value.copy(text = text.value.text.substring(0, text.value.text.length - 1)))
                }
            }
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                ButtonNumber(modifier = cellModifier(), number = "1") { }
                ButtonNumber(modifier = cellModifier(), number = "2") { }
                ButtonNumber(modifier = cellModifier(), number = "3") { }
                ButtonAction(modifier = cellModifier(), action = "×") { }
            }
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                ButtonNumber(modifier = cellModifier(), number = "4") { }
                ButtonNumber(modifier = cellModifier(), number = "5") { }
                ButtonNumber(modifier = cellModifier(), number = "6") { }
                ButtonAction(modifier = cellModifier(), action = "÷") { }
            }
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                ButtonNumber(modifier = cellModifier(), number = "7") { }
                ButtonNumber(modifier = cellModifier(), number = "8") { }
                ButtonNumber(modifier = cellModifier(), number = "9") { }
                ButtonAction(modifier = cellModifier(), action = "+") { }
            }
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                ButtonAction(modifier = cellModifier(), action = ".") { }
                ButtonNumber(modifier = cellModifier(), number = "0") { }
                ButtonSpecialAction(modifier = cellModifier(), specialAction = "=") { }
                ButtonAction(modifier = cellModifier(), action = "-") { }
            }
        }
    }
}