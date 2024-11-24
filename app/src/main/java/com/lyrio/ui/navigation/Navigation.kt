package com.lyrio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lyrio.LyrioApp
import com.lyrio.ui.auth.RecoverPass1
import com.lyrio.ui.auth.RecoverPass2
import com.lyrio.ui.auth.RecoverPass3
import com.lyrio.ui.auth.SignIn
import com.lyrio.ui.auth.SignUp1
import com.lyrio.ui.auth.SignUp2
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.auth.SignUp3
import com.lyrio.ui.data.viewmodels.PaymentsViewModel
import com.lyrio.ui.data.viewmodels.ViewModel
import com.lyrio.ui.data.viewmodels.WalletViewModel
import com.lyrio.ui.layout.DefaultLayout
import com.lyrio.ui.pages.AddCardSuccessful
import com.lyrio.ui.pages.AddCreditCard
import com.lyrio.ui.pages.AddInvestment
import com.lyrio.ui.pages.ChangeAlias
import com.lyrio.ui.pages.CreditCards
import com.lyrio.ui.pages.Home
import com.lyrio.ui.pages.Invest
import com.lyrio.ui.pages.LandingPage
import com.lyrio.ui.pages.Money
import com.lyrio.ui.pages.Movements
import com.lyrio.ui.pages.Paylink
import com.lyrio.ui.pages.Profile
import com.lyrio.ui.pages.ReceiveMoney
import com.lyrio.ui.pages.Transfer1
import com.lyrio.ui.pages.Transfer2
import com.lyrio.ui.pages.WithdrawInvestment
import com.lyrio.ui.pages.ChangeAliasSuccessful
import com.lyrio.ui.pages.TransferSuccessful

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    val viewModel: ViewModel  = viewModel(
        factory = ViewModel .provideFactory(
            LocalContext.current.applicationContext as LyrioApp
        )
    )

    val userViewModel: UserViewModel =
        viewModel(factory = UserViewModel.provideFactory(LocalContext.current.applicationContext as LyrioApp))

    val walletViewModel: WalletViewModel = viewModel(
        factory = WalletViewModel.provideFactory(
            LocalContext.current.applicationContext as LyrioApp,
            userViewModel
        )
    )
    val paymentsViewModel: PaymentsViewModel = viewModel(
        factory = PaymentsViewModel.provideFactory(
            LocalContext.current.applicationContext as LyrioApp,
            userViewModel
        )
    )

    NavHost(navController = navController, startDestination = Screen.SignIn) {

        composable<Screen.Landing> {
            LandingPage(navigateSignIn = {
                navController.navigate(Screen.SignIn)
            }, navigateSignUp = {
                navController.navigate(Screen.SignUp1)
            })
        }

        composable<Screen.SignIn> {
            SignIn(
                navigateSignUp = {
                    navController.navigate(Screen.SignUp1)
                },
                navigateHome = {
                    navController.navigate(Screen.Home)
                },
                navigateRecoverPass1 = {
                    navController.navigate(Screen.RecoverPass1)
                },
                viewModel = userViewModel
            )
        }

        composable<Screen.SignUp1> {
            SignUp1(
                navigateSignUp2 = {
                    navController.navigate(Screen.SignUp2)
                },
                navigateSignIn = {
                    navController.navigate(Screen.SignIn)
                },
                viewModel = userViewModel
            )
        }

        composable<Screen.SignUp2> {
            SignUp2(
                viewModel = userViewModel,
                navigateSignUp3 = {
                    navController.navigate(Screen.SignUp3)
                }
            )
        }

        composable<Screen.SignUp3> {
            SignUp3(
                navigateSignIn = {
                    navController.navigate(Screen.SignIn)
                },
                viewModel = userViewModel
            )
        }

        composable<Screen.RecoverPass1> {
            RecoverPass1(
                navigateRecoverPass2 = {
                    navController.navigate(Screen.RecoverPass2)
                },
                viewModel = userViewModel
            )
        }

        composable<Screen.RecoverPass2> {
            RecoverPass2(
                navigateRecoverPass3 = {
                    navController.navigate(Screen.RecoverPass3)
                },
                viewModel = userViewModel
            )
        }

        composable<Screen.RecoverPass3> {
            RecoverPass3(
                navigateSignIn = {
                    navController.navigate(Screen.SignIn)
                },
                viewModel = userViewModel
            )
        }

        composable<Screen.Home> {
            DefaultLayout(navController, viewModel ) {
                Home(
                    navigateTransfer1 = {
                        navController.navigate(Screen.Transfer1)
                    },
                    navigateReceiveMoney = {
                        navController.navigate(Screen.ReceiveMoney)
                    },
                    navigateProfile = {
                        navController.navigate(Screen.Profile)
                    },
                    navigateMovements = {
                        navController.navigate(Screen.Movements)
                    },
                    navigateMoney = {
                        navController.navigate(Screen.Money)
                    },
                    viewModelWallet = walletViewModel,
                    viewModelPayments = paymentsViewModel,
                    viewModelUser = userViewModel

                )
            }
        }

        composable<Screen.Profile> {
            DefaultLayout(navController, viewModel) {
                Profile(
                    navigateChangeAlias = {
                        navController.navigate(Screen.ChangeAlias)
                    },
                    viewModelUser = userViewModel,
                    viewModelWallet = walletViewModel
                )
            }
        }

        composable<Screen.Transfer1> {
            DefaultLayout(navController,viewModel) {
                Transfer1(
                    navigateTransfer2 = {
                        navController.navigate(Screen.Transfer2)
                    },
                    paymentsViewModel = paymentsViewModel
                )
            }
        }

        composable<Screen.Transfer2> {
            DefaultLayout(navController, viewModel) {
                Transfer2(
                    navigateTransferSuccessful = {
                    navController.navigate(Screen.TransferSuccessful)
                },
                    paymentsViewModel = paymentsViewModel,
                    walletViewModel = walletViewModel,
                    userViewModel = userViewModel
                )
            }
        }

        composable<Screen.TransferSuccessful> {
            DefaultLayout(navController, viewModel) {
                TransferSuccessful(
                    navigateHome = {
                        navController.navigate(Screen.Home)
                    },
                    navigateTransfer1 = {
                        navController.navigate(Screen.Transfer1)
                    }
                )
            }
        }

        composable<Screen.Money> {
            DefaultLayout(navController, viewModel) {
                Money(
                    walletViewModel = walletViewModel,
                    userViewModel = userViewModel,
                    paymentsViewModel = paymentsViewModel
                )
            }
        }

        composable<Screen.Movements> {
            DefaultLayout(navController, viewModel) {
                Movements(
                    viewModelPayments = paymentsViewModel,
                    viewModelUser = userViewModel
                )
            }
        }

        composable<Screen.Invest> {
            DefaultLayout(navController, viewModel) {
                Invest(
                    navigateAddInvestment = {
                        navController.navigate(Screen.AddInvestment)
                    },
                    navigateWithdrawInvestment = {
                        navController.navigate(Screen.WithdrawInvestment)
                    }
                )
            }
        }

        composable<Screen.AddInvestment> {
            DefaultLayout(navController, viewModel) {
                AddInvestment(
                    navigateInvest = {
                        navController.navigate(Screen.Invest)
                    }
                )
            }
        }

        composable<Screen.WithdrawInvestment> {
            DefaultLayout(navController, viewModel) {
                WithdrawInvestment(
                    navigateInvest = {
                        navController.navigate(Screen.Invest)
                    }
                )
            }
        }

        composable<Screen.ReceiveMoney> {
            DefaultLayout(navController, viewModel) {
                ReceiveMoney(
                    navController.context,
                    navigatePaylink = {
                        navController.navigate(Screen.Paylink)
                    }
                )
            }
        }

        composable<Screen.ChangeAlias> {
            DefaultLayout(navController, viewModel) {
                ChangeAlias(
                    navigateChangeAliasSuccessful = {
                        navController.navigate(Screen.ChangeAliasSuccessful)
                    },
                    walletViewModel = walletViewModel,
                    userViewModel = userViewModel
                )
            }
        }

        composable<Screen.ChangeAliasSuccessful> {
            DefaultLayout(navController, viewModel) {
                ChangeAliasSuccessful(
                    navigateProfile = {
                        navController.navigate(Screen.Profile)
                    },
                    walletViewModel = walletViewModel
                )
            }
        }

        composable<Screen.Paylink> {
            DefaultLayout(navController, viewModel) {
                Paylink(
                    navController.context,
                    navigateHome = {
                        navController.navigate(Screen.Home)
                    }
                )
            }
        }

        composable<Screen.CreditCards> {
            DefaultLayout(navController, viewModel) {
                CreditCards(
                    navigateAddCreditCard = {
                        navController.navigate(Screen.AddCreditCard)
                    },
                    walletViewModel = walletViewModel,
                    userViewModel = userViewModel
                )
            }
        }

        composable<Screen.AddCreditCard> {
            DefaultLayout(navController, viewModel) {
                AddCreditCard(
                    navigateAddCardSuccessful = {
                        navController.navigate(Screen.AddCardSuccessful)
                    },
                    walletViewModel = walletViewModel,
                    userViewModel = userViewModel
                )
            }
        }

        composable<Screen.AddCardSuccessful> {
            DefaultLayout(navController, viewModel) {
                AddCardSuccessful(
                    navigateAddCardSuccessful = {
                        navController.navigate(Screen.AddCreditCard)
                    },
                    navigateHome = {
                        navController.navigate(Screen.Home)
                    }
                )
            }
        }
    }
}