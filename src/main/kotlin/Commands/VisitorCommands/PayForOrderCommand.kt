package Commands.VisitorCommands

import Command
import ConsoleUI
import Methods
import Entities.Order
import Entities.Visitor
import Utilities.DataBase.WriterService

class PayForOrderCommand : Command {
    val writer : WriterService = WriterService()
    override fun execute(request: String?, program: ConsoleUI) {
        var selectedOrder : Order? = null
        val finishedOrders : MutableList<Order> = mutableListOf()
        val user : Visitor = program.currentUser as Visitor
        while (true) {
            var i = 1
            println("Choose an order for payment")
            for (order in user.orderList) {
                if (order.status == "Finished") {
                    println("$i ${order.user.login} order with price ${order.price}")
                    finishedOrders.add(order)
                    i++
                }
            }
            println()
            if (i == 1) {
                println("There is no ready orders\n")
                return
            }
            val input = Methods.validateInput()
            if (input in 1..finishedOrders.size) {
                selectedOrder = finishedOrders[input - 1]
                program.currentOrder = selectedOrder
                break
            } else {
                println("Wrong input")
            }
        }
        if (selectedOrder != null && user.wallet < selectedOrder.price) {
            println("Not enough money to pay")
        } else if (selectedOrder != null) {
            user.wallet -= selectedOrder.price
            writer.writeInCsv(program.users)
            println("Success payment")
            program.income += selectedOrder.price
            writer.writeInCsv(mutableListOf(selectedOrder))
            user.orderList.remove(selectedOrder)
            while (true) {
                program.showReviewOption()
                when(val input = readln()) {
                    "1" -> {program.commandExecutor.executeCommand("$input-Review",program);break}
                    "2" -> {println("Thanks for your order!");break}
                    else -> println("Wrong input")
                }
            }
        }
    }
}
