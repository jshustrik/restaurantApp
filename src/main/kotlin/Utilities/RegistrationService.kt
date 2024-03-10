package Utilities

import Builders.AdminBuilder
import Builders.Director
import Builders.VisitorBuilder
import ConsoleUI
import Entities.Admin
import Entities.User
import Entities.Visitor
import Utilities.DataBase.WriterService

class RegistrationService {
    val writer : WriterService  = WriterService()
    fun signInUser(program: ConsoleUI, userType : String) : User {
        print("Input login: ")
        var login = readln()
        if (userType == "Visitor") {
            while (program.users.find { user -> user.userName == login && user is Visitor } != null) {
                println("Account with login $login already exists. Choose another login.")
                print("Input login: ")
                login = readln()
            }
        } else {
            while (program.users.find { user -> user.userName == login && user is Admin} != null) {
                println("Account with login $login already exists. Choose another login.")
                print("Input login: ")
                login = readln()
            }
        }

        print("Input password: ")
        val password = readln()
        println()
        val builder = if (userType == "Visitor") VisitorBuilder(program) else AdminBuilder(program)
        val director = Director(builder,null,login,password, false)
        director.make()
        val user = builder.getResult()
        program.users.add(user)
        program.currentUser = user
        writer.writeInCsv(program.users)
        return user
    }
}