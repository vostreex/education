package com.example.education

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.education.ui.theme.EducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var textField by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Первый экран",
            modifier = modifier
        )
        OutlinedTextField(
            value = textField,
            onValueChange = {newText -> textField = newText},
            label = { Text("Номер телефона")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        Button(
            onClick = {
                if (textField.isNotBlank()){
                    val intent = Intent(
                        context, SecondActivity::class.java
                    ).apply {
                        putExtra("EXTRA_TEXT",textField)
                    }
                    context.startActivity(intent)
                } else{
                    Toast.makeText(context,"Введите номер",
                        Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Переход ко второй Activity")
        }

        Button(
            onClick = {
                if (textField.isNotBlank()){
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel: $textField")
                    }
                    try {
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "Приложение для звонков не найдено",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else{
                    Toast.makeText(context,"Заполните номер",
                        Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Позвонить другу")
        }

        Button(
            onClick = {
                if (textField.isNotBlank()){
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, textField)
                        type = "text/*"
                    }

                    val shareIntent = Intent.createChooser(intent,"Поделиться через")
                    try {
                        context.startActivity(shareIntent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "Приложение для отправки не найдено",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else{
                    Toast.makeText(context,"Заполните номер",
                        Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Поделиться текстом")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EducationTheme {
        Greeting()
    }
}