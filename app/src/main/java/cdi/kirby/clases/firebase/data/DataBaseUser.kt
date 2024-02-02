package cdi.kirby.clases.firebase.data

import cdi.kirby.clases.firebase.data.DataBaseData
import java.util.Date

data class DataBaseUser
(
    override var id: String? = null,
    var email: String? = null,
    var username: String? = null,
    var photoPath: String? = null,
    var lastLogin: Date? = Date(),
    val admin: Boolean = false
): DataBaseData {
    override fun GetTable() = "users"
}