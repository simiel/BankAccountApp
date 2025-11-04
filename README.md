# Bank Account App

A modern Android banking application built with Kotlin and Jetpack Compose.

## Features

- **Account Types**: Create Debit, Credit, or Checking accounts
- **Transactions**: Deposit and withdraw money
- **Smart Validation**: Account-specific rules for transactions
- **Clean UI**: Minimal, neat interface with Material Design 3

## Account Types & Rules

### Debit Account
- Cannot withdraw if balance is zero
- Cannot withdraw more than available balance

### Credit Account
- Can have negative balance (debt)
- Special deposit rules for paying off credit
- Cannot deposit if account is already paid off

### Checking Account
- Standard deposit and withdraw operations

## Building the App

1. Open the project in Android Studio
2. Sync Gradle files
3. Run the app on an emulator or physical device

## Requirements

- Android Studio Hedgehog or later
- Minimum SDK: 24
- Target SDK: 34
- Kotlin 1.9+

