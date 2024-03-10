import Utilities.DataBase.ReaderService

// admin admin
// meow meowmeow

fun main() {
    // Initializing data.
    val consoleUI = ConsoleUI()

    // Restoring serialized data.
    val reader = ReaderService(consoleUI)
    reader.readCsv("DataFiles/Users.csv")
    reader.readCsv("DataFiles/Dishes.csv")

    // Choosing an option of using program.
    var input : String? = ""
    while (true) {
        consoleUI.showStartMenu()
        input = readln()
        when (input) {
            "1","2" -> consoleUI.commandExecutor.executeCommand("$input-Start", consoleUI)
            "3" -> {println("Closing..."); break}
            else -> println("Wrong input")
        }
    }
}
