package jp.ac.it_college.std.s23023.messageboard.infrastructure.database.repository

import jp.ac.it_college.std.s23023.messageboard.domain.model.Threads
import jp.ac.it_college.std.s23023.messageboard.domain.repository.ThreadRepository
import jp.ac.it_college.std.s23023.messageboard.infrastructure.database.dao.ThreadsEntity
import jp.ac.it_college.std.s23023.messageboard.infrastructure.database.dao.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import kotlin.concurrent.thread


@Repository
class ThreadRepositoryImpl : ThreadRepository {
    override fun createThread(thread: Threads): Threads {
        return transaction {
            val newThread = ThreadsEntity.new {
                title = thread.title
                userId = UserEntity[thread.userId]
                createdAt = thread.createdAt
                updatedAt = thread.updatedAt
                deleted = thread.deleted
            }
            newThread.toThread()
        }
    }

    override fun getThreadById(id: Long): Threads? {
        return transaction {
            val threadEntity = ThreadsEntity.findById(id)
            threadEntity?.toThread()
        }
    }

    override fun getAllThreads(): List<Threads> {
        return transaction {
            ThreadsEntity.all().map { it.toThread() }
        }
    }

    override fun updateThread(thread: Threads): Threads {
        return transaction {
            val threadEntity = ThreadsEntity.findById(thread.id)
                ?: throw IllegalArgumentException("threads not found with id")

            threadEntity.apply {
                title = thread.title
                userId = UserEntity[thread.userId]
                createdAt = thread.createdAt
                updatedAt = thread.updatedAt
                deleted = thread.deleted
            }
            threadEntity.toThread()
        }
    }

    override fun deleteThread(id: Long) {
        transaction {
            ThreadsEntity.findById(id)?.delete()
        }
    }

    override fun getDetails(id: Long): Threads? {
        return transaction {
            ThreadsEntity.findById(id)?.toThread()
        }
    }

//    override fun save(threads: Threads): Threads {
//        return transaction {
//            val savedEntity = ThreadsEntity.new {
//                title = threads.title
//                userId = threads.userId
//                createdAt = threads.createdAt
//                updatedAt = threads.updatedAt
//            }
//            Threads(
//                id = savedEntity.id.value,
//                title = savedEntity.title,
//                userId = savedEntity.userId,
//                createdAt = savedEntity.createdAt,
//                updatedAt = savedEntity.updatedAt
//            )
//        }
    }



