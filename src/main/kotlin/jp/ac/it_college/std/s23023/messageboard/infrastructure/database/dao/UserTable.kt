package jp.ac.it_college.std.s23023.messageboard.infrastructure.database.dao

import org.jetbrains.exposed.dao.id.LongIdTable

object UserTable : LongIdTable("users") {
    var viewName = varchar("view_name", 32)
    var email = varchar("email", 256).uniqueIndex()
    var password = varchar("password", 128)
}
