package com.example.praktikumlayout

import androidx.lifecycle.ViewModel

object SharedStateView {
    val viewModel: State by lazy { State() }
}

class State : ViewModel() {
    var fullname: String? = null
    var isLoggedIn: Boolean = false
}