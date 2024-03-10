package Commands.VisitorCommands

import Command
import ConsoleUI
import Methods
import Entities.Order
import Entities.Visitor
import Utilities.GetUnfinishedOrders

class ChangeDishCommand : Command {
    override fun execute(request: String?, program: ConsoleUI) {
        var selectedOrder: Order? = null
        val user = program.currentUser as Visitor
        while(true) {
            if (user.orderList.isEmpty()) {
                println("You have no orders right now\n")
                return
            }
            println("Choose order to change: (input number)")
            Methods.printUnfinished(user)
            if (Methods.findOrder(program, user)) {
                break
            }
        }
        selectedOrder = program.currentOrder
        selectedOrder.timer.pauseTimer()
        println(selectedOrder.timer.isPaused)
        while (true) {
            program.showOrderOptions()
            val input = readln()
            if (input == "3") {
                break
            } else if (input == "1"){
                program.commandExecutor.executeCommand("$input-Order", program)
            } else if (input == "2"){
                if (selectedOrder.status == "Creating") {
                    if (selectedOrder.dishList.isEmpty()) {
                        println("No dishes to cook")
                        break
                    }
                    program.commandExecutor.executeCommand("$input-Order", program)
                    break
                } else {
                    val newTime = Methods.getTimeToCook(selectedOrder.dishList)
                    selectedOrder.price = Methods.getOrderPrice(selectedOrder.dishList)
                    selectedOrder.timer.time = newTime
                    selectedOrder.timer.pauseTimer()
                    break
                }
            } else {
                println("Wrong option")
            }
        }
    }
}