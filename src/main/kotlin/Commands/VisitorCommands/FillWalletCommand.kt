package Commands.VisitorCommands

import Command
import ConsoleUI
import Entities.Visitor
import Utilities.DataBase.WriterService

class FillWalletCommand : Command {
    val writer : WriterService = WriterService()
    override fun execute(request: String?, program: ConsoleUI) {
        val user = program.currentUser as Visitor
        println("How much money to deposit? ")
        val input = Methods.validateInput()
        user.wallet += input
        writer.writeInCsv(program.users)
        println("You have transferred ${input} to your wallet")
        println("Now you have ${user.wallet}")
    }
}