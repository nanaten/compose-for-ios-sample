import androidx.compose.runtime.Composable
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView() {
    initializeNapier()
    App()
}

fun initializeNapier() {
    Napier.base(DebugAntilog())
}
