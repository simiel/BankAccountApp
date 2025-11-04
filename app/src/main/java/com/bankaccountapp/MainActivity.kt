package com.bankaccountapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bankaccountapp.ui.screens.BankAccountScreen
import com.bankaccountapp.ui.theme.BankAccountTheme
import com.bankaccountapp.viewmodel.BankAccountViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val viewModel = BankAccountViewModel()
        
        setContent {
            BankAccountTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BankAccountScreen(viewModel = viewModel)
                }
            }
        }
    }
}

