package Commands.VisitorCommands

import Command
import ConsoleUI
import Methods
import Entities.Review
import Utilities.DataBase.WriterService

class MakeReviewCommand : Command {
    val writer : WriterService = WriterService()
    override fun execute(request: String?, program: ConsoleUI) {
        var rating : Int
        println("Rate the order from 1 to 5:")
        while (true) {
            rating = Methods.validateInput()
            if (rating < 1 || rating > 5) {
                println("Wrong input! Repeat please")
            } else {
                break
            }
        }
        println("You can add commentary for your review: (input text or leave blank)")
        val text = readln()
        val review = Review(program.currentUser, rating, text)
        program.reviews.add(review)
        writer.writeInCsv(program.reviews)
        print("The review has been successfully added! Thank you!\n")
    }
}