package Commands.AdminCommands

import Command
import ConsoleUI
import Methods

class GetStatisticCommand : Command {
    override fun execute(request: String?, program: ConsoleUI) {
        while(true) {
            program.showStatisticMenu()
            val input = readln()
            when(input) {
                "1" -> println(program.finishedOrders.size)
                "2" -> println(Methods.getAverageRating(program.reviews))
                "3" -> println(Methods.getMostPopularDish(program.finishedOrders)?.dishName ?: "There is no orders yet")
                "4" -> break
                else -> println("Wrong input")
            }
        }
    }
}