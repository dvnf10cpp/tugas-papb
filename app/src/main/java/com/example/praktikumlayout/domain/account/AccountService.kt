package com.example.praktikumlayout.domain.account

class AccountService private constructor() {
    companion object {
        private var instance: AccountService? = null

        fun getInstance(): AccountService {
            if (instance == null) {
                instance = AccountService()
            }

            return instance!!
        }
    }

    fun accountMatched(account: Account): Boolean {
        return AccountsData.accounts.any { a ->
            a.email == account.email &&
                    a.password == account.password
        }
    }

    fun isAlreadyRegistered(account: Account): Boolean {
        return AccountsData.accounts.any { a ->
            a.email == account.email
        }
    }

    fun getAccount(account: Account): Account {
        return AccountsData.accounts.find {
            it.email == account.email &&
                    it.password == account.password
        }!!
    }

    fun registerAccount(account: Account): String {

        if (account.fullname.isEmpty() || account.email.isEmpty() || account.password.isEmpty()) {
            // invalid validation
            return "Input cannot be empty"
        }

        if (isAlreadyRegistered(account)) {
            // already registered
            return "Email already registered"
        }

        AccountsData.accounts.add(account)

        return ""
    }

    fun updateAccount(account: Account) {
        AccountsData.accounts.forEachIndexed { idx, item ->
            if (item.email == account.email) {
                AccountsData.accounts[idx] = account
            }
        }
    }
}