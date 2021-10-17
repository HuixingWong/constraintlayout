import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.constraintlayout.compose.ConstraintlayoutSample

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        DesktopMaterialTheme {
            ConstraintlayoutSample()
        }
    }
}