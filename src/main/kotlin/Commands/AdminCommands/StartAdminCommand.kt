package Commands.AdminCommands

import Command
import CommandExecutor
import ConsoleUI
import Entities.Admin
import Entities.User
import Utilities.AuthenticationService
import Utilities.DataBase.DataBaseService
import Utilities.RegistrationService
import java.lang.Exception

class StartAdminCommand : Command {
    override fun execute(request: String?, program: ConsoleUI) {
        val logService = AuthenticationService()
        var input = ""
        var admin: Admin
        val regService: RegistrationService = RegistrationService()
        while (true) {
            try {
                while (input != "1" && input != "2") {
                    program.showUserMenu()
                    input = readln()
                    println()
                }
                if (input == "1") {
                    logService.loginUser(program, "Admin") as Admin
                } else if (Methods.isAdmin()) {
                    regService.signInUser(program, "Admin") as Admin
                } else {
                    println("Wrong code!")
                    return
                }
                break
            } catch (ex: IllegalArgumentException) {
                println(ex.message + "\n")
                input = ""
            }
        }
        while (true) {
            try {
                program.showAdminMenu()
                val input = readln()
                println()
                if (input == "7") {
                    break
                }
                program.commandExecutor.executeCommand("$input-Admin", program)
            } catch (ex: IllegalArgumentException) {
                println("${ex.message}\n")
            } catch (ex: Exception) {
                println("Some error occurred - ${ex.message}\n")
            }
        }
    }
}