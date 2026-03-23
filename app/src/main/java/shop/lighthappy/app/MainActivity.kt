package shop.lighthappy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import shop.lighthappy.app.ui.composable.approot.AppRoot
import shop.lighthappy.app.ui.theme.ProductAppIKEHPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppIKEHPTheme {
                AppRoot()
            }
        }
    }
}