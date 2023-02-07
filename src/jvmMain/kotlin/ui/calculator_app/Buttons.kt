package ui.calculator_app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import ui.AnimatedFilledTonalButton

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