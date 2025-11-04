package com.bankaccountapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bankaccountapp.model.AccountType
import com.bankaccountapp.model.BankAccount
import com.bankaccountapp.ui.theme.BankAccountTheme
import com.bankaccountapp.viewmodel.BankAccountViewModel
import com.bankaccountapp.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankAccountScreen(viewModel: BankAccountViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bank Account", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            if (!uiState.isAccountCreated) {
                AccountCreationScreen(viewModel)
            } else {
                AccountOperationsScreen(viewModel, uiState)
            }

            // Message display
            uiState.message?.let { message ->
                LaunchedEffect(message) {
                    delay(3000)
                    viewModel.clearMessage()
                }
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (message.contains("Error") || message.contains("failed") || message.contains("Can't")) {
                            MaterialTheme.colorScheme.errorContainer
                        } else {
                            MaterialTheme.colorScheme.primaryContainer
                        }
                    )
                ) {
                    Text(
                        text = message,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun AccountCreationScreen(viewModel: BankAccountViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Select the type of account you would like to create:",
                style = MaterialTheme.typography.bodyMedium
            )

            AccountType.values().forEach { accountType ->
                Button(
                    onClick = { viewModel.createAccount(accountType) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = accountType.name,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun AccountOperationsScreen(
    viewModel: BankAccountViewModel,
    uiState: UiState
) {
    val account = uiState.account ?: return

    // Account Info Card
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = account.accountType.name + " Account",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Balance: ${viewModel.getBalance()}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    // Amount Input
    OutlinedTextField(
        value = uiState.amountInput,
        onValueChange = { viewModel.updateAmountInput(it) },
        label = { Text("Amount") },
        placeholder = { Text("Enter amount") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        shape = RoundedCornerShape(8.dp)
    )

    // Action Buttons
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = {
                val amount = uiState.amountInput.toIntOrNull() ?: 0
                if (amount > 0) {
                    viewModel.withdraw(amount)
                    viewModel.updateAmountInput("")
                }
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("Withdraw", modifier = Modifier.padding(vertical = 8.dp))
        }

        Button(
            onClick = {
                val amount = uiState.amountInput.toIntOrNull() ?: 0
                if (amount > 0) {
                    viewModel.deposit(amount)
                    viewModel.updateAmountInput("")
                }
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Deposit", modifier = Modifier.padding(vertical = 8.dp))
        }
    }

    // Balance Check Button
    OutlinedButton(
        onClick = {
            viewModel.uiState.value.message?.let { viewModel.clearMessage() }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Current Balance: ${viewModel.getBalance()}")
    }
}

// Preview Functions
@Preview(showBackground = true, name = "Account Creation")
@Composable
fun AccountCreationScreenPreview() {
    BankAccountTheme {
        AccountCreationScreen(BankAccountViewModel())
    }
}

@Preview(showBackground = true, name = "Account Operations - Debit")
@Composable
fun AccountOperationsScreenPreview() {
    BankAccountTheme {
        val viewModel = BankAccountViewModel()
        val testAccount = BankAccount(AccountType.DEBIT, 500)
        val testState = UiState(
            account = testAccount,
            isAccountCreated = true,
            amountInput = ""
        )
        AccountOperationsScreen(viewModel, testState)
    }
}

@Preview(showBackground = true, name = "Account Operations - Credit")
@Composable
fun AccountOperationsScreenCreditPreview() {
    BankAccountTheme {
        val viewModel = BankAccountViewModel()
        val testAccount = BankAccount(AccountType.CREDIT, -250)
        val testState = UiState(
            account = testAccount,
            isAccountCreated = true,
            amountInput = "100"
        )
        AccountOperationsScreen(viewModel, testState)
    }
}

@Preview(showBackground = true, name = "Full Screen - No Account")
@Composable
fun BankAccountScreenPreview() {
    BankAccountTheme {
        BankAccountScreen(BankAccountViewModel())
    }
}

