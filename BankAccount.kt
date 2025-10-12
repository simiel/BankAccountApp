// File: BankAccount.kt

/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
fun main() {
    println("Welcome to your banking system.")
    
    println("What type of account would you like to create?")
	println("")
	println("1. Debit account")
	println("2. Credit account")
	println("3. Checking account")

    var accountType = ""
    var userChoice: Int = 0

    while (accountType == ""){
        println("Choose an option (1-3):")
        userChoice = (1..3).random() // Simulating user input for demonstration
        println("User selected option: $userChoice")
        when (userChoice) {
            1 -> accountType = "Debit"
            2 -> accountType = "Credit"
            3 -> accountType = "Checking"
            else -> println("Invalid option. Please choose a valid option.")
        }
    }

    println("You have selected a $accountType account.")

    var accountBalance = (0..1000).random()
    println("The current balance is ${accountBalance} dollars.")
    val money = (0..1000).random()
    println("The amount you transferred is ${money} dollars.")
 
    fun withdraw(amount: Int): Int {
        accountBalance -= amount
        println("You successfully withdrew ${amount} dollars. The current balance is ${accountBalance} dollars.")
        return amount
    }
 
    fun debitWithdraw(amount: Int): Int {
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The current balance is ${accountBalance} dollars.")
            return 0
        } else {
            return withdraw(amount)
        }
    }
 
    fun deposit(amount: Int): Int {
        accountBalance += amount
        println("You successfully deposited ${amount} dollars. The current balance is ${accountBalance} dollars.")
        return amount
    }
    
    fun creditDeposit(amount: Int): Int {
        if (accountBalance == 0) {
            println("This account is completely paid off! Do not deposit money!")
            return accountBalance
        } else if (accountBalance + amount > 0) {
            println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is ${accountBalance} dollars.")
        	return 0
        } else if (amount == -accountBalance) {
            accountBalance = 0
            println("You have paid off this account!")
            return amount
        } else {
            return deposit(amount)
        }
    }

    fun transfer(mode: String){
        val transferAmount: Int

        when (mode) {
            "withdraw" -> {
                if(accountType == "debit"){
                    transferAmount = debitWithdraw(money)
                } else {
                    transferAmount = withdraw(money)
                }

                println("You have withdrawn $$transferAmount")
            }

            "deposit" -> {
                if (accountType == "credit") {
                    transferAmount = creditDeposit(money)
                } else {
                    transferAmount = deposit(money)
                }
                println("The amount you deposited is ${transferAmount} dollars.")
            }
            else -> return
        }
    }

    var isSystemOpen= true
    var option = 0

    while (isSystemOpen == true) {
        println("What would you like to do?")
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Close the system")
        println("Which option do you choose? (1, 2, 3 or 4)")
 
        option = (1..5).random()
        println("The selected option is ${option}.")
 
        when (option) {
            1 -> println("The current balance is ${accountBalance} dollars.")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }

}