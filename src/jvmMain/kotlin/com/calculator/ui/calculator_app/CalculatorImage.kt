package com.calculator.ui.calculator_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Suppress("SpellCheckingInspection")
@Composable
fun ShowAzamatPhoto(imageBitmap: ImageBitmap?, onBack: () -> Unit) {
    val containerColor = MaterialTheme.colorScheme.surface
    val contentColor = MaterialTheme.colorScheme.onSurface
    Box {
        imageBitmap?.let {
            Image(
                painter = BitmapPainter(image = it),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter)) {
            FilledTonalIconButton(
                modifier = Modifier.align(Alignment.Start).padding(8.dp), onClick = onBack,
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = containerColor,
                    contentColor = contentColor
                )
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(Modifier.height(16.dp))
            ElevatedCard(Modifier.padding(horizontal = 32.dp)) {
                Column(
                    Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "Warning",
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "Авторское лицо запретило использовать его фото в этом приложении",
                        style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
                    )
                }

            }
        }

    }

}