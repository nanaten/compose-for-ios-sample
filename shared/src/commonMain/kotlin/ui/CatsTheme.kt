package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Composable
fun CatsTheme(
    colors: Colors = Colors(),
    content: @Composable () -> Unit,
) {
    val theme = lightColors(
        primary = colors.primary,
        secondary = colors.secondary,
        background = colors.background
    )
    MaterialTheme(
        colors = theme,
        content = content,
    )
}

@Immutable
data class Colors(
    val primary: Color = Color(0xFF2a5caa),
    val secondary: Color = Color(0xFF263966),
    val background: Color = Color(0xFFFFFFFF),
    val textPrimary: Color = Color(0xFF2D3436),
)