package Commands.VisitorCommands

import Command
import ConsoleUI
import Entities.Visitor

class CheckOrderCommand : Command {
    override fun execute(request: String?, program: ConsoleUI) {
        val user = program.currentUser as Visitor
        for (order in user.orderList) {
            println("${order.user.login} order with price ${order.price} has status: ${order.status}")
        }
        if (user.orderList.isEmpty()) {
            println("You have no orders right now\n")
        }
    }
}