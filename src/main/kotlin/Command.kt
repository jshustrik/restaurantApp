internal interface Command {
    fun execute(request: String?, consoleUI: ConsoleUI)
}