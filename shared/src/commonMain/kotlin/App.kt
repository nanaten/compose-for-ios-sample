import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.CatsTheme
import ui.MainScreen


@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    CatsTheme {
        Surface {
            Navigator(MainScreen())
        }
    }
}

expect fun getPlatformName(): String
