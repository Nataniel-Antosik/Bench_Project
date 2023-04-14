package com.antosik.benchproject.app.common.theme

import androidx.compose.ui.graphics.Color

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color,
) {
    object Night : ThemeColors(
        background = Color(0xFF000000),
        surface = Color(0xFF000000),
        primary = Color(0xFFBB86FC),
        text = Color(0xFFB7B7B7)
    )

    object Day : ThemeColors(
        background = Color(0xFFFFFFFF),
        surface = Color(0xFFFFFFFF),
        primary = Color(0xFF6200EE),
        text = Color(0xFF747474)
    )
}
