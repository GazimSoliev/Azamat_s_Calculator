package ui.calculator_app

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun Keyboard(
    modifier: Modifier,
    text: TextFieldValue,
    setText: (TextFieldValue) -> Unit,
    equal: () -> Unit,
    changePhoto: () -> Unit
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        val horizontalArrangement = Arrangement.spacedBy(8.dp)
        val cellModifier: RowScope.() -> Modifier = {
            Modifier.weight(1f).fillMaxHeight()
        }
        val rowModifier: ColumnScope.() -> Modifier = {
            Modifier.weight(1f)
        }
        val onClick = { s: String ->
            val str = text.text
            val selection = text.selection
            text.selection.run {
                setText(
                    when {
                        !collapsed -> {
                            TextFieldValue(
                                text = str.replaceRange(min until max, s),
                                selection = selection.run {
                                    val newSel = if (min < str.length) min + 1 else min
                                    TextRange(newSel, newSel)
                                }
                            )
                        }

                        else -> TextFieldValue(
                            text = str.substring(0, selection.min) + s + str.substring(selection.min),
                            selection = TextRange(selection.min + 1, selection.min + 1)
                        )
                    }
                )
            }
        }
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                IconFilledTonalButton(
                    modifier = cellModifier(),
                    Icons.Default.Image,
                    "Open image",
                    onClick = changePhoto
                )
                Spacer(modifier = cellModifier())
                Spacer(modifier = cellModifier())
                IconFilledTonalButton(modifier = cellModifier(), Icons.Default.Backspace, "Backspace") {
                    val str = text.text
                    text.selection.run {
                        setText(
                            when {
                                !collapsed -> {
                                    TextFieldValue(
                                        text = str.removeRange(min until max),
                                        selection = TextRange(min, min)
                                    )
                                }

                                min != 0 -> TextFieldValue(
                                    text = str.removeRange(min - 1 until min),
                                    selection = TextRange(min - 1, min - 1)
                                )

                                else -> text
                            }
                        )
                    }
                }
            }
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                ButtonNumber(modifier = cellModifier(), number = "1", onClick = onClick)
                ButtonNumber(modifier = cellModifier(), number = "2", onClick = onClick)
                ButtonNumber(modifier = cellModifier(), number = "3", onClick = onClick)
                ButtonAction(modifier = cellModifier(), action = "×", onClick = onClick)
            }
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                ButtonNumber(modifier = cellModifier(), number = "4", onClick = onClick)
                ButtonNumber(modifier = cellModifier(), number = "5", onClick = onClick)
                ButtonNumber(modifier = cellModifier(), number = "6", onClick = onClick)
                ButtonAction(modifier = cellModifier(), action = "÷", onClick = onClick)
            }
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                ButtonNumber(modifier = cellModifier(), number = "7", onClick = onClick)
                ButtonNumber(modifier = cellModifier(), number = "8", onClick = onClick)
                ButtonNumber(modifier = cellModifier(), number = "9", onClick = onClick)
                ButtonAction(modifier = cellModifier(), action = "+", onClick = onClick)
            }
            Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
                ButtonAction(modifier = cellModifier(), action = ".", onClick = onClick)
                ButtonNumber(modifier = cellModifier(), number = "0", onClick = onClick)
                ButtonSpecialAction(modifier = cellModifier(), specialAction = "=") { equal() }
                ButtonAction(modifier = cellModifier(), action = "-", onClick = onClick)
            }
        }
    }
}