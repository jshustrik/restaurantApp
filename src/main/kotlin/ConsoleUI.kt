import Entities.*

class ConsoleUI() {
    var income : Int = 0
    lateinit var currentUser: User
    lateinit var currentOrder : Order
    val commandExecutor: CommandExecutor = CommandExecutor()
    var users : MutableList<User> = mutableListOf()
    var dishes: MutableList<Dish> = mutableListOf()
    var reviews : MutableList<Review> = mutableListOf()
    var finishedOrders : MutableList<Order> = mutableListOf()

    fun showStartMenu() {
        println("Continue as:")
        println("   1. Visitor")
        println("   2. Admin")
        println("   3. Exit")
        print("Your option: ")
    }

    fun showUserMenu() {
        println("Login with current account or sign in:")
        println("   1. Login into account")
        println("   2. Sign in new account\n")
        print("Your option: ")
    }

    fun showAdminMenu() {
        println("Admin. Choose an option:")
        println("   1. Show information about dishes")
        println("   2. Add dish")
        println("   3. Change dish")
        println("   4. Remove dish")
        println("   5. Check income")
        println("   6. Get statistic")
        println("   7. Exit.")
        //println("   Change password")
        // println("   Login into another user account")
        //println("   Add new admin")
        print("\nEnter you option: ")
    }

    fun showVisitorOptions() {
        println("Choose as option:")
        println("   1. Make order.")
        println("   2. Change order.")
        println("   3. Cancel order.")
        println("   4. Check orders.")
        println("   5. Pay for the order.")
        println("   6. Fill your wallet.")
        println("   7. Exit.")
//        println("   Amount of cash in the wallet")
//        println("   Change password")
        print("Your option: ")
    }

    fun showChangingOption() {
        println("What part you want to change?")
        println("   1. Change amount")
        println("   2. Change price")
        println("   3. Change cooking time")
        println("   4. Exit")
    }

    fun showStatisticMenu() {
        println("What type of statistic you want?")
        println("   1. The number of orders")
        println("   2. Average order rating")
        println("   3. Most popular dish")
        println("   4. Exit")
    }

    fun showOrderOptions() {
        println("Make your order:")
        println("   1. Add dish to order")
        println("   2. Finish order")
        println("   3. Exit")
        print("Your option: ")
    }

    fun showReviewOption() {
        println("Would you like to leave a review?")
        println("   1. Yes")
        println("   2. No")
    }
}