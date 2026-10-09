package com.example.education

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.education.ui.theme.EducationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receiveText = intent.getStringExtra("EXTRA_TEXT") ?: "Ничего не получено"
            setContent {
            EducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondActivityScreen(
                        modifier = Modifier.padding(innerPadding),
                        text = receiveText
                    )
                }
            }
        }
    }
}

@Composable
fun SecondActivityScreen(modifier: Modifier = Modifier,text: String){
    var currentText by rememberSaveable { mutableStateOf(text) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(currentText)
    }
}