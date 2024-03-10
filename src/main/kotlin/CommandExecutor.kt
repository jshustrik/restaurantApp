import Commands.*
import Commands.AdminCommands.*
import Commands.VisitorCommands.*

class CommandExecutor {
    private var map: MutableMap<String, Command>

    init {
        map = HashMap()
        map["1-Start"] = StartVisitorCommand()
        map["2-Start"] = StartAdminCommand()
        map["1-Admin"] = ShowDishesCommand()
        map["2-Admin"] = AddDishCommand()
        map["3-Admin"] = ChangeMenuCommand()
        map["4-Admin"] = RemoveDishCommand()
        map["5-Admin"] = CheckIncomeCommand()
        map["6-Admin"] = GetStatisticCommand()
        map["1-User"] = MakeOrderCommand()
        map["2-User"] = ChangeDishCommand()
        map["3-User"] = CancelOrderCommand()
        map["4-User"] = CheckOrderCommand()
        map["5-User"] = PayForOrderCommand()
        map["6-User"] = FillWalletCommand()
        map["1-Order"] = AddDishesCommand()
        map["2-Order"] = StartCookingCommand()
        map["1-Review"] = MakeReviewCommand()
    }

    fun executeCommand(request: String, consoleUI: ConsoleUI) {
        val command = map[request]
        if (command != null) {
            command.execute(request, consoleUI)
        } else {
            throw IllegalArgumentException("Wrong command! Try again, please")
        }
    }
}