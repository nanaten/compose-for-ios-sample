package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator


@Stable
class NavigationState(private val navigator: Navigator) {
    val shouldShowNavigationBack: Boolean
        get() = navigator.canPop

}

@Composable
fun rememberNavigationState(navigator: Navigator) = remember(navigator) {
    NavigationState(navigator)
}