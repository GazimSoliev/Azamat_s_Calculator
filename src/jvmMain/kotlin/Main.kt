import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import theme.AppTheme
import java.io.File

object Resources {

    fun getFile(patchInResource: String): File? =
        javaClass.classLoader.getResource(patchInResource)?.toURI()?.let(::File)

}

val scope by lazy {
    CoroutineScope(Dispatchers.Main + Job())
}

val text = MutableStateFlow(TextFieldValue(selection = TextRange.Companion.Zero, composition = TextRange.Zero))


fun main() = application {
    Window(
        onCloseRequest = {
            scope.cancel()
            exitApplication()
        },
        state = WindowState(size = DpSize(width = 500.dp, height = 900.dp)),
        resizable = false
    ) {
        App()
    }
}

@Composable
@Preview
fun App() {
    AppTheme {
        Surface(Modifier.fillMaxSize()) {
            CalculatorInterface(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun CalculatorInterface(modifier: Modifier = Modifier) {
    Surface {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            InputField(
                Modifier.height(256.dp).fillMaxWidth().background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp)
                ).padding(horizontal = 16.dp, vertical = 32.dp),
                text.collectAsState().value
            )
            Keyboard(modifier = Modifier.weight(1f))
        }
    }

}

//data class KeyboardOnClick(
//    val
//)

@Composable
fun Keyboard(modifier: Modifier) {
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
                    setText(text.value.copy(text = text.value.text.substring(0, text.value.text.length - 1)))
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

@Composable
fun ButtonNumber(
    modifier: Modifier = Modifier,
    number: String,
    onClick: () -> Unit
) = ButtonUnit(
    modifier = modifier,
    symbol = number,
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick = onClick
)

@Composable
fun IconFilledTonalButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) = ButtonUnitZero(
    modifier = modifier,
    onClick = onClick,
    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
) {
    Icon(
        modifier = Modifier.fillMaxSize(0.5f).align(Alignment.CenterVertically),
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}


@Composable
fun ButtonAction(
    modifier: Modifier = Modifier,
    action: String,
    onClick: () -> Unit
) = ButtonUnit(
    modifier = modifier,
    onClick = onClick,
    symbol = action,
    containerColor = MaterialTheme.colorScheme.secondaryContainer,
    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
)


@Composable
fun ButtonSpecialAction(
    modifier: Modifier = Modifier,
    specialAction: String,
    onClick: () -> Unit
) = ButtonUnit(
    modifier = modifier,
    onClick = onClick,
    symbol = specialAction,
    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
)

@Composable
fun ButtonUnit(
    modifier: Modifier = Modifier,
    symbol: String,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) = ButtonUnitZero(
    modifier = modifier,
    onClick = onClick,
    containerColor = containerColor,
    contentColor = contentColor
) {
    Text(symbol, style = MaterialTheme.typography.displayMedium, textAlign = TextAlign.Center)
}

@Composable
fun ButtonUnitZero(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) = AnimatedFilledTonalButton(
    modifier = modifier,
    colors = ButtonDefaults.filledTonalButtonColors(
        containerColor = containerColor,
        contentColor = contentColor
    ),
    onClick = onClick,
    content = content
)

@Composable
fun InputField(modifier: Modifier, text: TextFieldValue) {
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

fun setText(newText: TextFieldValue) {
    scope.launch(Dispatchers.IO) {
        text.value = newText
    }
}
