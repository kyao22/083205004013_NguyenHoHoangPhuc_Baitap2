package com.example.cau_2

import android.icu.text.CaseMap.Title

data class Book(
    private val title: String,
    private val author: String,
    private val price: Int,
) {
    // Hàm getter cho từng thuộc tính
    fun getTitle(): String {
        return title
    }

    fun getAuthor(): String {
        return author
    }

    fun getPrice(): Int {
        return price
    }

}
