package Commands.VisitorCommands
import Command
import ConsoleUI
import Entities.Visitor
import Utilities.AuthenticationService
import Utilities.RegistrationService
import java.lang.Exception

class StartVisitorCommand : Command {
    override fun execute(request: String?, program: ConsoleUI) {
        val regService = RegistrationService()
        val logService = AuthenticationService()
        var input: String? = ""
        var visitor: Visitor

        while (true) {
            try {
                while (input != "1" && input != "2") {
                    program.showUserMenu()
                    input = readln()
                    println()
                }
                visitor = if (input == "1") {
                    logService.loginUser(program, "Visitor") as Visitor
                } else {
                    regService.signInUser(program, "Visitor") as Visitor
                }
                break
            } catch (ex: IllegalArgumentException) {
                println(ex.message + "\n")
                input = ""
            }
        }

        while (true) {
            try {
                program.showVisitorOptions()
                input = readln()
                println()
                if (input == "7") {
                    break
                }
                program.commandExecutor.executeCommand("$input-User", program)
            } catch (ex: IllegalArgumentException) {
                println("${ex.message}\n")
            } catch (ex: Exception) {
                println("Some error occurred - ${ex.message}\n")
            }
        }
    }
}