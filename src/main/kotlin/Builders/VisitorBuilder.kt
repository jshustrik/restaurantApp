package Builders

import ConsoleUI
import Utilities.DataBase.DataBaseService
import Entities.Visitor
import java.util.*

class VisitorBuilder(val program : ConsoleUI) : Builder {
    val hasher : DataBaseService = DataBaseService(program)
    lateinit var id : UUID
    var login : String = ""
    var password : String = ""
    private var wallet : Int = 0
    override fun setUUID(id : String?) {
        if (id == null) {
            this.id = UUID.randomUUID()
        } else {
            this.id = UUID.nameUUIDFromBytes(id.toByteArray())
        }
    }

    override fun setUserLogin(login: String) {
        this.login = login
    }

    override fun setUserPassword(password: String, isHashed : Boolean) {
        if (isHashed) {
            this.password = password
        } else {
            this.password = hasher.hashPassword(password)
        }
    }

    fun setMoney(amount: Int) {
        this.wallet = amount
    }

    override fun getResult() : Visitor {
        return Visitor(id, login, password, wallet)
    }
}