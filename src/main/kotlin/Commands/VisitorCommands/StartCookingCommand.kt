package Commands.VisitorCommands

import Command
import ConsoleUI
import Entities.Order
import Methods
import Utilities.CookingTimer

class StartCookingCommand : Command{
    override fun execute(request: String?, program: ConsoleUI) {
        val order : Order = program.currentOrder
        val time : Long = Methods.getTimeToCook(order.dishList)
        program.currentOrder.timer = CookingTimer(time, order)
        order.status = "In process..."
        order.price = Methods.getOrderPrice(order.dishList)
        program.currentOrder.timer.startTimer()
    }

}