package com.bankaccountapp.viewmodel

import androidx.lifecycle.ViewModel
import com.bankaccountapp.model.AccountType
import com.bankaccountapp.model.BankAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UiState(
    val account: BankAccount? = null,
    val message: String? = null,
    val isAccountCreated: Boolean = false,
    val amountInput: String = ""
)

class BankAccountViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun createAccount(accountType: AccountType) {
        val initialBalance = (0..1000).random()
        val account = BankAccount(
            accountType = accountType,
            balance = initialBalance
        )
        _uiState.value = _uiState.value.copy(
            account = account,
            isAccountCreated = true,
            message = "${accountType.name} account created with balance: $${initialBalance}"
        )
    }

    fun withdraw(amount: Int) {
        val account = _uiState.value.account
        if (account == null) {
            _uiState.value = _uiState.value.copy(
                message = "Please create an account first"
            )
            return
        }

        val result = account.withdraw(amount)
        when (result) {
            is com.bankaccountapp.model.TransactionResult.Success -> {
                _uiState.value = _uiState.value.copy(
                    account = account,
                    message = "You successfully withdrew $${amount} dollars. The current balance is $${result.newBalance} dollars."
                )
            }
            is com.bankaccountapp.model.TransactionResult.Error -> {
                _uiState.value = _uiState.value.copy(
                    message = result.message
                )
            }
        }
    }

    fun deposit(amount: Int) {
        val account = _uiState.value.account
        if (account == null) {
            _uiState.value = _uiState.value.copy(
                message = "Please create an account first"
            )
            return
        }

        val result = account.deposit(amount)
        when (result) {
            is com.bankaccountapp.model.TransactionResult.Success -> {
                _uiState.value = _uiState.value.copy(
                    account = account,
                    message = result.message + " The amount you deposited is $${amount} dollars. The current balance is $${result.newBalance} dollars."
                )
            }
            is com.bankaccountapp.model.TransactionResult.Error -> {
                _uiState.value = _uiState.value.copy(
                    message = result.message
                )
            }
        }
    }

    fun updateAmountInput(amount: String) {
        _uiState.value = _uiState.value.copy(amountInput = amount)
    }

    fun clearMessage() {
        _uiState.value = _uiState.value.copy(message = null)
    }

    fun getBalance(): String {
        return _uiState.value.account?.let { "$${it.balance}" } ?: "No account"
    }
}

