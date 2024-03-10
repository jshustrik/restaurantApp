package Commands.AdminCommands

import Command
import ConsoleUI
import Methods
import Entities.Dish
import Utilities.DataBase.WriterService

class ChangeMenuCommand : Command {

    val writer = WriterService()
    override fun execute(request: String?, program: ConsoleUI) {
        println("Choose dish to change")
        var i = 1
        for (dish in program.dishes) {
            println("$i ${dish.dishName} with amount ${dish.dishAmount}, price ${dish.dishPrice} and difficulty ${dish.dishDifficulty}")
            i++
        }
        if (i == 1) {
            println("There is no dish to change")
            return
        }
        val input = Methods.validateInput()
        if (input > 0 && input <= program.dishes.size) {
            val selectedDish : Dish = program.dishes[input - 1]
            program.showChangingOption()
            val choose = readln();
            when(choose) {
                "1" -> {
                    println("Input new amount:")
                    val amount = Methods.validateInput()
                    selectedDish.setAmount(amount)}
                "2" -> {
                    println("Input new price:")
                    val price = Methods.validateInput()
                    selectedDish.setPrice(price)}
                "3" -> {
                    println("Input new difficulty (in seconds):")
                    val diff = Methods.validateInput()
                    selectedDish.setDifficulty(diff)}
                "4" -> return;
                else -> println("Wrong input");
            }
            writer.writeInCsv(program.dishes)
        } else {
            println("Wrong input")
        }
    }
}