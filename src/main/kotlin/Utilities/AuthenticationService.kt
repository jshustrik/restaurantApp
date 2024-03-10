package Utilities

import ConsoleUI
import Entities.Admin
import Entities.User
import Utilities.DataBase.DataBaseService
import Entities.Visitor

class AuthenticationService {
    fun loginUser(program: ConsoleUI, userType : String) : User {
        val hasher  = DataBaseService(program)
        val currentUser: User
        print("Enter UserName: ")
        val userName = readln()
        currentUser = if (userType == "Visitor") {
            if (program.users.find { visitor -> visitor.userName == userName && visitor is Visitor } == null) {
                throw IllegalArgumentException("There is no users with such username.")
            }
            program.users.find { visitor -> visitor.userName == userName && visitor is Visitor}!!
        } else {
            if (program.users.find { admin -> admin.userName == userName && admin is Admin } == null) {
                throw IllegalArgumentException("There is no users with such username.")
            }
            program.users.find { admin -> admin.userName == userName && admin is Admin}!!
        }
        while (true) {
            print("Enter password: ")
            val password = readln()
            if (currentUser.userPassword == hasher.hashPassword(password)) {
                break
            }
            println("Incorrect password!")
        }
        println()
        program.currentUser = currentUser
        return currentUser
    }
}