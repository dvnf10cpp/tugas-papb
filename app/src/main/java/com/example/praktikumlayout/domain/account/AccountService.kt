package com.example.praktikumlayout.domain.account

import android.content.Context
import android.util.Log
import com.example.praktikumlayout.domain.response.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.tasks.await

class AccountService private constructor() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    companion object {
        private var instance: AccountService? = null

        fun getInstance(): AccountService {
            if (instance == null) {
                instance = AccountService()
            }

            return instance!!
        }
    }

    suspend fun loginAccount(account: Account): Response {
        var resp = Response()

        if (account.email.isEmpty() || account.password.isEmpty()) {
            resp.code = 400
            resp.message = "Fields must not be empty"
            return resp
        }

        try {
            firebaseAuth
                .signInWithEmailAndPassword(
                    account.email,
                    account.password
                ).await()

            resp.code = 200
            resp.message = "Login success"
        } catch (e: Exception) {
            resp.code = 500
            resp.message = e.message ?: "Login failed"
        }

        return resp
    }

    suspend fun registerAccount(account: Account): Response {
        var resp = Response()

        if (account.email.isEmpty() || account.password.isEmpty()) {
            resp.code = 400
            resp.message = "Fields must not be empty"
            return resp
        }

        try {
            firebaseAuth
                .createUserWithEmailAndPassword(
                    account.email,
                    account.password
                ).await()

            resp.code = 200
            resp.message = "Register success"
        } catch (e: Exception) {
            resp.code = 500
            resp.message = e.message ?: "Register failed"
        }

        return resp

    }

    fun updateAccount(account: Account) {
        AccountsData.accounts.forEachIndexed { idx, item ->
            if (item.email == account.email) {
                AccountsData.accounts[idx] = account
            }
        }
    }
}