import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import theme.AppTheme
import java.io.File

object Resources {

    fun getFile(patchInResource: String): File? =
        javaClass.classLoader.getResource(patchInResource)?.toURI()?.let(::File)

}


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(width = 600.dp, height = 800.dp)),
        resizable = false
    ) {
        App()
    }
}

@Composable
@Preview
fun App() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CalculatorInterface(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun CalculatorInterface(modifier: Modifier = Modifier) {
    Surface {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            InputField(Modifier.weight(1f).fillMaxWidth().background(MaterialTheme.colorScheme.surfaceVariant))
            Keyboard(modifier = Modifier.weight(3f))
        }
    }

}

@Composable
fun Keyboard(modifier: Modifier) {
    val horizontalArrangement = Arrangement.spacedBy(8.dp)
    val keyboardModifier: RowScope.() -> Modifier = {
        Modifier.height(IntrinsicSize.Min).aspectRatio(1f)
    }
    val rowModifier: ColumnScope.() -> Modifier = {
        Modifier.weight(1f)
    }
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "1")
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "2")
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "3")
            KeyboardAction(modifier = keyboardModifier(), onClick = { }, action = "×")
        }
        Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "4")
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "5")
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "6")
            KeyboardAction(modifier = keyboardModifier(), onClick = { }, action = "÷")
        }
        Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "7")
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "8")
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "9")
            KeyboardAction(modifier = keyboardModifier(), onClick = { }, action = "+")
        }
        Row(modifier = rowModifier(), horizontalArrangement = horizontalArrangement) {
            KeyboardAction(modifier = keyboardModifier(), onClick = { }, action = ".")
            KeyboardNumber(modifier = keyboardModifier(), onClick = { }, number = "0")
            KeyboardSpecialAction(modifier = keyboardModifier(), onClick = { }, specialAction = "=")
            KeyboardAction(modifier = keyboardModifier(), onClick = { }, action = "-")
        }
    }
}

@Composable
fun KeyboardNumber(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    number: String
) = KeyboardUnit(
    modifier = modifier,
    onClick = onClick,
    symbol = number,
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
)

@Composable
fun KeyboardAction(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    action: String
) = KeyboardUnit(
    modifier = modifier,
    onClick = onClick,
    symbol = action,
    containerColor = MaterialTheme.colorScheme.secondaryContainer,
    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
)


@Composable
fun KeyboardSpecialAction(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    specialAction: String
) = KeyboardUnit(
    modifier = modifier,
    onClick = onClick,
    symbol = specialAction,
    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
)

@Composable
fun KeyboardUnit(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    symbol: String,
    containerColor: Color,
    contentColor: Color
) = AnimatedFilledTonalButton(
    modifier = modifier,
    onClick = onClick,
    colors = ButtonDefaults.filledTonalButtonColors(
        containerColor = containerColor,
        contentColor = contentColor
    )
) {
    Text(symbol, style = MaterialTheme.typography.displayMedium, textAlign = TextAlign.Center)
}

@Composable
fun InputField(modifier: Modifier) {
    BasicTextField("Test", modifier = modifier, onValueChange = {})
}
