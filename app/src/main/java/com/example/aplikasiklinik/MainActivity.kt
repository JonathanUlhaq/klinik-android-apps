package com.example.aplikasiklinik

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.aplikasiklinik.model.ThemeModeModel
import com.example.aplikasiklinik.ui.theme.AplikasiKlinikTheme
import com.example.aplikasiklinik.view.main.MainScreen
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            val viewModel by viewModels<MainActivityViewModel>()
            val state = viewModel.uiState.collectAsState().value

            AplikasiKlinikTheme(
                darkTheme = if (state.isEmpty()) false else state.first().darkMode || isSystemInDarkTheme()
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(if (state.isEmpty()) false else state.first().darkMode || isSystemInDarkTheme()) {
                        viewModel.insertBoolean(ThemeModeModel(0, !state.first().darkMode))
                    }
                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
    }
}
