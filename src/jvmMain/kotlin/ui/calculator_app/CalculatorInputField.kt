package ui.calculator_app

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign

@Composable
fun InputField(modifier: Modifier, text: TextFieldValue, setText: (TextFieldValue) -> Unit) {
    BasicTextField(
        text,
        modifier = modifier,
        onValueChange = {
            setText(it)
        },
        textStyle = MaterialTheme.typography.displayLarge.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Right
        ),
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant)
    )
}