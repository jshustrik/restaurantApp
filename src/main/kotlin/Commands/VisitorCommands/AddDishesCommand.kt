package Commands.VisitorCommands

import Command
import ConsoleUI
import Utilities.DataBase.WriterService

class AddDishesCommand : Command {
    val writer : WriterService = WriterService()
    override fun execute(request: String?, program: ConsoleUI) {
        println("List of all available dishes:")
        var i = 1
        if (program.dishes.size == 0) {
            println("No dishes available")
            return
        } else {
            for (dish in program.dishes) {
                println("${i}. ${dish.dishName} with amount of ${dish.dishAmount}. Price: ${dish.dishPrice}")
                i++
            }
        }
        val input = Methods.validateInput()
        if (input > 0 && input <= program.dishes.size) {
            if (program.dishes[input - 1].dishAmount <= 0) {
                println("This dish type is out of stock")
                return
            }
            program.currentOrder.dishList.add(program.dishes[input - 1])
            program.currentOrder.price = Methods.getOrderPrice(program.currentOrder.dishList)
            program.dishes[input - 1].dishAmount--
            writer.writeInCsv(program.dishes)
        } else {
            println("Wrong dish number")
        }
    }
}