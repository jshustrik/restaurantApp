package Commands.AdminCommands

import Command
import ConsoleUI

class ShowDishesCommand : Command {
    override fun execute(request: String?, program: ConsoleUI) {
        var i = 1
        if (program.dishes.isEmpty()) {
            println("There is no dishes in menu\n")
        } else {
            for (dish in program.dishes) {
                println("$i ${dish.dishName} with amount ${dish.dishAmount}, price ${dish.dishPrice} and difficulty ${dish.dishDifficulty}")
                i++
            }
            println()
        }
    }
}