import Entities.*
import Utilities.GetUnfinishedOrders

class Methods {
    companion object {
        fun validateInput(): Int {
            var input: Int? = readln().toIntOrNull()
            while (input == null || input <= 0) {
                print("Incorrect input. Input must be positive integer. Try again: ")
                input = readln().toIntOrNull()
            }
            return input
        }

        public fun isAdmin(): Boolean {
            println("Please input code to create admin profile (it's 'admin'): ")
            val input = readln()
            return input == "admin"
        }

        fun getAverageRating(list : MutableList<Review>) : Double{
            var rating = 0
            for (item in list) {
                rating += item.rating
            }
            return rating.toDouble() / list.size
        }

        fun getMostPopularDish(list: MutableList<Order>): Dish? {
            val map: MutableMap<Dish, Int> = HashMap()
            list.flatMap { it.dishList }.forEach { map[it] = (map[it] ?: 0) + 1 }
            return map.maxByOrNull { it.value }?.key
        }

        fun printUnfinished(user : Visitor) : Boolean{
            var i = 1
            for (order in user.orderList) {
                if (order.status != "Finished") {
                    println("$i ${order.user.login} order with price ${order.price} has status: ${order.status}")
                    i++
                }
            }
            println()
            return i != 1
        }

        fun findOrder(program : ConsoleUI, user : Visitor) : Boolean{
            val checker = GetUnfinishedOrders()
            val input = validateInput()
            val count = checker.getUnfinished(user.orderList)
            return if (input in 1..count) {
                program.currentOrder =  user.orderList[input - 1]
                true
            } else {
                println("Wrong input")
                false
            }
        }

        fun getTimeToCook(list : MutableList<Dish>) : Long {
            var time : Long = 0;
            for (item in list) {
                time += item.dishDifficulty;
            }
            return time
        }

        fun getOrderPrice(list : MutableList<Dish>) : Int {
            var sum : Int = 0
            for (item in list) {
                sum += item.dishPrice
            }
            return sum
        }
    }
}