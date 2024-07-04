package jp.ac.it_college.std.s23023.messageboard.domain.repository

import jp.ac.it_college.std.s23023.messageboard.domain.model.Messages
import java.util.*

interface MessageRepository {
        fun createMessage(message: Messages): Messages

        fun getMessageById(id: Long): Messages?

        fun getMessageByThreadId(threadId: Long): List<Messages>

        fun updateMessage(message: Messages): Messages

        fun deleteMessage(id: Long)

}

