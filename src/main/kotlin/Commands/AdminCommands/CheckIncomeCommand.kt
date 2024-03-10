package Commands.AdminCommands

import Command
import ConsoleUI

class CheckIncomeCommand : Command {
    override fun execute(request: String?, program: ConsoleUI) {
        println("Your current income is ${program.income}\n")
    }
}