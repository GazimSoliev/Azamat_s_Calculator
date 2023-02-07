package ui.calculator_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorInterface(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    setText: (TextFieldValue) -> Unit
) {
    Surface {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            InputField(
                Modifier.height(256.dp).fillMaxWidth().background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp)
                ).padding(horizontal = 16.dp, vertical = 32.dp), text, setText
            )
            Keyboard(modifier = Modifier.weight(1f), text, setText)
        }
    }
}