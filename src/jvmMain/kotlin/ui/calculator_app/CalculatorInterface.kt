package ui.calculator_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    result: String,
    text: TextFieldValue,
    setText: (TextFieldValue) -> Unit,
    equal: () -> Unit,
    changePhoto: () -> Unit
) {
    Surface {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            InputField(
                modifier = Modifier.height(256.dp).fillMaxWidth().background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp)
                ).padding(horizontal = 16.dp, vertical = 32.dp),
                result = result,
                text = text,
                setText = setText
            )
            Spacer(Modifier.height(8.dp))
            Keyboard(modifier = Modifier.weight(1f), text = text, setText = setText, equal = equal, changePhoto = changePhoto)
        }
    }
}