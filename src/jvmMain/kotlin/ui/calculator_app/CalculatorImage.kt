package ui.calculator_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import resRepository.Resources

@Composable
fun ShowAzamatPhoto(onBack: () -> Unit) {
    val file = Resources.getFile("img.png")
    val imageBitmap = remember {
        file?.run {
            loadImageBitmap(inputStream())
        }
    }
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
            IconButton(
                modifier = Modifier.align(Alignment.Start).padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface, CircleShape), onClick = onBack
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = contentColorFor(MaterialTheme.colorScheme.surface)
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
                        text = "Автор фотографии запретил использовать его фото в этом приложении",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }
        }

    }

}