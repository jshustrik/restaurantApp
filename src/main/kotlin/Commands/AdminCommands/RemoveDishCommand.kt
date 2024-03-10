package Commands.AdminCommands

import Command
import ConsoleUI
import Methods
import Entities.Dish
import Utilities.DataBase.WriterService

class RemoveDishCommand : Command{
    private val writer : WriterService = WriterService()
    override fun execute(request: String?, program: ConsoleUI) {
        println("Choose dish to delete")
        var i = 1
        for (dish in program.dishes) {
            println("$i ${dish.dishName} with amount ${dish.dishAmount}, price ${dish.dishPrice} and difficulty ${dish.dishDifficulty}")
            i++
        }
        if (i == 1) {
            println("There is no dish to delete")
            return
        }
        val input = Methods.validateInput()
        if (input > 0 && input <= program.dishes.size) {
            program.dishes.removeAt(input - 1)
            writer.writeInCsv(program.dishes)
            println("Deleted!")
        } else {
            println("Wrong input")
        }
    }
}