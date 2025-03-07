package com.example.cau_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgeCheckerApp()
        }
    }
}

@Composable
fun AgeCheckerApp() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "THỰC HÀNH 01", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            label = { Text("Nhập tên") },
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text(text = "Tuổi") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onDone = {
                    result = ageCheck(age, name)
                    showDialog = true
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
                result = ageCheck(age, name)
                showDialog = true
                }
        ) {
            Text(text = "Kiểm tra")
        }
        if (showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Kết Quả") },
                text = { Text("Kết quả kiểm tra: $result") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Đóng")
                    }
                }
            )
        }
    }
}



fun ageCheck(age: String, name: String):String {
    val ageInt = age.toIntOrNull()
    var temp = "Bạn"
    if (name !== ""){
        temp = name
    }
    return when {
        ageInt == null -> "Vui lòng nhập số tuổi hợp lệ!"
        ageInt > 65 -> "$temp là Người già"
        ageInt in 6..65 -> "$temp là Người lớn"
        ageInt in 2..5 -> "$temp là Trẻ em"
        else -> "$temp là Em bé"
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AgeCheckerApp()
}
