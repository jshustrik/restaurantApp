package Commands.AdminCommands

import Command
import Methods
import ConsoleUI
import Entities.Dish
import Utilities.DataBase.WriterService

class AddDishCommand : Command {
    private val writer : WriterService = WriterService()
    override fun execute(request: String?, consoleUI: ConsoleUI) {
        print("Input the name of the dish: ")
        val name = readln()
        print("Input amount of dish: ")
        val count = Methods.validateInput()
        print("Input the price of the dish: ")
        val price = Methods.validateInput()
        print("Input the cooking time of the dish: (in seconds) ")
        val time = Methods.validateInput()
        consoleUI.dishes.add(Dish(name, count, price, time))
        writer.writeInCsv(consoleUI.dishes)
        print("The dish has been successfully added!\n")
    }
}