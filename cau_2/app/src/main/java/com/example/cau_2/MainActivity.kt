package com.example.cau_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
            BottomNavBar()
        }
    }
}

@Composable
fun QuanLyScreen() {
    var employeeName by remember { mutableStateOf("Nguyen Van A") }
    var showDialog by remember { mutableStateOf(false)}
    var temp by remember { mutableStateOf(Book("a", "a", 0)) }
    val books = remember {
        mutableStateListOf(
            Book("Sách 01", "Tác giả A", 100),
            Book("Sách 02", "Tác giả B", 200),
            Book("Sách 03", "Tác giả B", 280),
            Book("Sách 04", "Tác giả C", 250),
            Book("Sách 05", "Tác giả D", 210),
            Book("Sách 06", "Tác giả E", 200)
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
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Thông tin sách") },
                text = {
                    Column {
                        Text(text = "📖 Tiêu đề: ${temp.getTitle()}", fontSize = 17.sp)
                        Text(text = "✍️ Tác giả: ${temp.getAuthor()}", fontSize = 17.sp)
                        Text(text = "💰 Giá: ${temp.getPrice()} $", fontSize = 17.sp)
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

@Composable
fun DSSachScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Danh sách Sách", fontSize = 20.sp, color = Color.Black)
    }
}

@Composable
fun NhanVienScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Quản lý Nhân viên", fontSize = 20.sp, color = Color.Black)
    }
}


@Composable
fun BottomNavBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val items = listOf("Quản lý", "DS Sách", "Nhân viên")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Book,
        Icons.Filled.Person
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            when (selectedIndex) {
                0 -> QuanLyScreen()
                1 -> DSSachScreen()
                2 -> NhanVienScreen()
            }
        }

        BottomNavigation(
            modifier = Modifier.fillMaxHeight(0.075f),
            backgroundColor = Color.White,
            elevation = 10.dp,

        ) {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = item,
                                modifier = Modifier.size(32.dp),
                                tint = if (index == selectedIndex) Color.Blue else Color.Gray
                            )
                            Text(
                                text = item,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 4.dp),
                                color = if (index == selectedIndex) Color.Blue else Color.Gray
                            )
                        }
                    },
                    selected = index == selectedIndex,
                    onClick = { selectedIndex = index }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomNavBar()
}