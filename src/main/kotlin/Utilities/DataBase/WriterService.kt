package Utilities.DataBase

import Entities.*
import java.io.File
import java.io.FileWriter
import java.io.IOException

class WriterService {
    private var header = ""
    private var path = ""
    fun writeInCsv(data : MutableList<*>){
        var append = true
        try {
            when {
                data.any {it is Visitor || it is Admin} -> {header = "Role,ID,Login,Password,MoneyAmount\n"; path = "DataFiles/Users.csv"; append = false}
                data.any {it is Order} -> {header = "Order by,Price\n"; path = "DataFiles/PaidOrders.csv"}
                data.any {it is Dish } -> {header = "Name,Amount,Price,Difficulty\n"; path = "DataFiles/Dishes.csv"; append = false}
                data.any {it is Review } -> {header = "Reviewed by,Rating,Description\n"; path = "DataFiles/Reviews.csv"}
                else -> {header = "Name,Amount,Price,Difficulty\n"; path = "DataFiles/Dishes.csv"; append = false}
            }
            FileWriter(path, append).use { writer ->
                if (File(path).length() == 0L) {
                    writer.append(header)
                }
                for (item in data) {
                    writer.append("$item\n")
                    writer.flush()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}