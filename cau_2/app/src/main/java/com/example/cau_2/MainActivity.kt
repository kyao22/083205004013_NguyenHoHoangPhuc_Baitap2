package com.example.cau_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryManagementScreen()
        }
    }
}

@Composable
fun LibraryManagementScreen() {
    var employeeName by remember { mutableStateOf("Nguyen Van A") }
    var showDialog by remember { mutableStateOf(false)}
    var temp by remember { mutableStateOf(Book("a", "a", 0, "a")) }
    val books = remember {
        mutableStateListOf(
            Book("Sách 01", "Tác giả A", 100, "Nguyen Van A"),
            Book("Sách 02", "Tác giả B", 200, "Nguyen Van A")
        )
    }
    val selectedBooks = remember { mutableStateMapOf<Book, Boolean>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))

        Text(
            text = "HỆ THỐNG\nQUẢN LÝ THƯ VIỆN",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        Text(text = "Nhân viên", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween ) {
            TextField(
                value = employeeName,
                onValueChange = { employeeName = it },
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /* Thay đổi nhân viên */ }) {
                Text("Đổi")
            }
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.02f))

        Text(text = "Danh sách sách", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
        LazyColumn(modifier = Modifier.fillMaxHeight(0.6f).clip(RoundedCornerShape(16.dp)).background(Color.LightGray).padding(4.dp)) {
            items(books) { book ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .clickable{showDialog = true
                            temp = book.copy()},
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Checkbox(
                            checked = selectedBooks[book] ?: false,
                            onCheckedChange = { isChecked ->
                                selectedBooks[book] = isChecked
                            }
                        )
                        Text(text = book.getTitle(), modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút thêm
        Button(
            onClick = { /* Xử lý thêm sách */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Thông tin sách") },
                text = {
                    Column {
                        Text(text = "📖 Tiêu đề: ${temp.getTitle()}")
                        Text(text = "✍️ Tác giả: ${temp.getAuthor()}")
                        Text(text = "💰 Giá: ${temp.getPrice()} $")
                        Text(text = "📌 Người phụ trách: ${temp.getPersonInCharge()}")
                    }
                },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Đóng")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LibraryManagementScreen()
}