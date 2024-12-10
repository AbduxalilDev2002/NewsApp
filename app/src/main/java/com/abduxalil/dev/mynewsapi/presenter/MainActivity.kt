package com.abduxalil.dev.mynewsapi.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abduxalil.dev.mynewsapi.presenter.navigation.MyNavigator
import com.abduxalil.dev.mynewsapi.presenter.theme.MyNewsApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNewsApiTheme {
                MyNavigator()
            }
        }
    }
}
