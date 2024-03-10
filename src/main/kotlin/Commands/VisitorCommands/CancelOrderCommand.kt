package Commands.VisitorCommands

import Command
import ConsoleUI
import Methods
import Entities.Order
import Entities.Visitor
import Utilities.GetUnfinishedOrders

class CancelOrderCommand : Command {
    override fun execute(request: String?, program: ConsoleUI) {
        val user = program.currentUser as Visitor
        var selectedOrder: Order? = null
        val orderChecker = GetUnfinishedOrders()
        while(true) {
            if (user.orderList.isEmpty()) {
                println("You have no orders right now\n")
                return
            }
            println("Choose order to cancel: (input number)")
            Methods.printUnfinished(user)
            if (Methods.findOrder(program, user)) {
                break
            }
        }
        selectedOrder = program.currentOrder
        selectedOrder.timer.cancelTimer()
        user.orderList.remove(selectedOrder)
        println("Your order has been deleted")
    }
}