package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import ui.cat.CatListScreen

class MainScreen : Screen {
    @Composable
    override fun Content() {
        Navigator(CatListScreen()) { navigator ->
            val navigationState = rememberNavigationState(navigator)
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { Text("Cat App") },
                        modifier = Modifier.fillMaxWidth(),
                        navigationIcon = {
                            if (navigationState.shouldShowNavigationBack) {
                                IconButton(
                                    onClick = navigator::pop,
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                    )
                }) {
                CurrentScreen()
            }
        }
    }
}
