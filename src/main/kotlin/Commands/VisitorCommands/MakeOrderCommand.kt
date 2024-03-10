package Commands.VisitorCommands

import Command
import ConsoleUI
import Entities.Order
import Entities.Visitor
import Utilities.CookingTimer
import Utilities.DataBase.WriterService
import java.util.*

class MakeOrderCommand : Command  {
    val writer : WriterService = WriterService()
    override fun execute(request: String?, program: ConsoleUI) {
        val currentUser : Visitor = program.currentUser as Visitor
        val order : Order = Order(mutableListOf(), "Creating", 0, currentUser, CookingTimer(0, null))
        program.currentOrder = order
        var input : String? = ""
        while (true) {
            program.showOrderOptions()
            input = readln()
            if (input == "3") {
                currentUser.orderList.add(order)
                break
            } else if (input == "1"){
                program.commandExecutor.executeCommand("$input-Order", program)
            } else if (input == "2" ){
                currentUser.orderList.add(order)
                if (order.dishList.isEmpty()) {
                    println("No dishes to cook")
                    break
                }
                program.finishedOrders.add(order)
                program.commandExecutor.executeCommand("$input-Order", program)
                break
            } else {
                println("Wrong option")
            }
        }
    }

}