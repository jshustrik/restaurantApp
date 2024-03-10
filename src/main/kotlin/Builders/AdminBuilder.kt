package Builders

import ConsoleUI
import Entities.Admin
import Utilities.DataBase.DataBaseService
import java.util.*

class AdminBuilder (val program : ConsoleUI) : Builder {
    val hasher : DataBaseService = DataBaseService(program)
    lateinit var id : UUID
    var login : String = ""
    var password : String = ""
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

    override fun getResult() : Admin {
        return Admin(id, login, password)
    }
}