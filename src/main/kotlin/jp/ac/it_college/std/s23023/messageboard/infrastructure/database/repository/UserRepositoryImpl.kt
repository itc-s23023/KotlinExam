package jp.ac.it_college.std.s23023.messageboard.infrastructure.database.repository

import jp.ac.it_college.std.s23023.messageboard.domain.model.Users
import jp.ac.it_college.std.s23023.messageboard.domain.repository.UserRepository
import jp.ac.it_college.std.s23023.messageboard.infrastructure.database.dao.UserEntity
import jp.ac.it_college.std.s23023.messageboard.infrastructure.database.dao.UserTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findByEmail(email: String): Users? {
        return transaction {
            UserEntity.find {
                UserTable.email eq email
            }.singleOrNull()?.toUser()
        }
    }

    override fun findById(id: Long): Users? {
        return transaction {
            UserEntity.findById(id)?.toUser()
        }
    }

    override fun createUser(user: Users): Users {
        return transaction {
            UserEntity.new {
                this.email = user.email
                this.password = user.password
                this.viewName = user.viewName
            }.toUser()
        }
    }

    override fun updateUser(user: Users): Users {
        return transaction {
            val userEntity = UserEntity.findById(user.id)
                ?: throw IllegalArgumentException("User not found with id: ${user.id}")
            userEntity.apply {
                email = user.email
                password = user.password
                viewName = user.viewName
            }
            userEntity.toUser()
        }
    }

    override fun deleteUser(id: Long) {
        transaction {
            val userEntity = UserEntity.findById(id)
                ?: throw IllegalArgumentException("User not found with id: $id")
            userEntity.delete()
        }
    }

    override fun save(user: Users): Users {
        return if (user.id == 0L) {
            createUser(user)
        } else {
            updateUser(user)
        }
    }
}

