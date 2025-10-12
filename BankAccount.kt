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
    println("Account created successfully!")

}