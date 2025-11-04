package com.bankaccountapp.model

data class BankAccount(
    val accountType: AccountType,
    var balance: Int = 0
) {
    fun withdraw(amount: Int): TransactionResult {
        when (accountType) {
            AccountType.DEBIT -> return debitWithdraw(amount)
            AccountType.CREDIT -> {
                // Credit accounts can have negative balance (debt)
                balance -= amount
                return TransactionResult.Success(amount, balance)
            }
            AccountType.CHECKING -> {
                balance -= amount
                return TransactionResult.Success(amount, balance)
            }
        }
    }

    private fun debitWithdraw(amount: Int): TransactionResult {
        if (balance == 0) {
            return TransactionResult.Error("Can't withdraw, no money on this account!")
        } else if (amount > balance) {
            return TransactionResult.Error("Not enough money on this account! The current balance is $balance dollars.")
        } else {
            balance -= amount
            return TransactionResult.Success(amount, balance)
        }
    }

    fun deposit(amount: Int): TransactionResult {
        when (accountType) {
            AccountType.CREDIT -> return creditDeposit(amount)
            AccountType.DEBIT, AccountType.CHECKING -> {
                balance += amount
                return TransactionResult.Success(amount, balance)
            }
        }
    }

    private fun creditDeposit(amount: Int): TransactionResult {
        if (balance == 0) {
            return TransactionResult.Error("This account is completely paid off! Do not deposit money!")
        } else if (balance + amount > 0) {
            return TransactionResult.Error("Deposit failed, you tried to pay off an amount greater than the credit balance. The credit balance is $balance dollars.")
        } else if (amount == -balance) {
            balance = 0
            return TransactionResult.Success(amount, balance, "You have paid off this account!")
        } else {
            balance += amount
            return TransactionResult.Success(amount, balance)
        }
    }
}

sealed class TransactionResult {
    data class Success(
        val amount: Int,
        val newBalance: Int,
        val message: String = "Transaction successful!"
    ) : TransactionResult()

    data class Error(val message: String) : TransactionResult()
}

