package ui.calculator_app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    modifier: Modifier,
    result: String,
    text: TextFieldValue,
    setText: (TextFieldValue) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    Box(modifier, contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            BasicTextField(
                value = text,
                onValueChange = {
                    setText(text.copy(selection = if (it.text.length == text.text.length) it.selection else text.selection))
                },
                modifier = Modifier.focusRequester(focusRequester).onFocusChanged {
                    if (!it.hasFocus) focusRequester.requestFocus()
                }.fillMaxWidth(),
                textStyle = MaterialTheme.typography.displayLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Right
                ),
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Spacer(modifier = Modifier.height(16.dp))
            BasicTextField(
                value = result,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.surfaceTint,
                    textAlign = TextAlign.Right
                ),
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant),
                readOnly = true
            )

        }
    }


}