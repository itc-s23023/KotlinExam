package jp.ac.it_college.std.s23023.messageboard.presentation.contoroller

import jp.ac.it_college.std.s23023.messageboard.infrastructure.database.dao.ThreadsTable.title
import jp.ac.it_college.std.s23023.messageboard.infrastructure.database.dao.ThreadsTable.updatedAt
import jp.ac.it_college.std.s23023.messageboard.infrastructure.database.dao.ThreadsTable.userId
import jp.ac.it_college.std.s23023.messageboard.presentation.from.*
import jp.ac.it_college.std.s23023.messageboard.application.service.MessageBoardUserDetailsService
import jp.ac.it_college.std.s23023.messageboard.application.service.ThreadService
import kotlinx.datetime.toJavaLocalDateTime
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/threads")
@CrossOrigin
class ThreadController(
    private val service: ThreadService
) {
    @GetMapping("/list")
    fun getList(): GetThreadListResponse {
        val threadList = service.getThreadlist().map { thread ->
            ThreadInfo(
                id = thread.id,
                title = thread.title,
                createdAt = thread.createdAt.toJavaLocalDateTime()
            )
        }
        return GetThreadListResponse(threadList)
    }

//    @PostMapping("/create")
//    fun create(
//        @RequestBody body: PostThreadRequest,
//        @AuthenticationPrincipal user: MessageBoardUserDetailsService
//    ): CreatedThreadResponse {
//        val newId = service.newPost(body.title, body.message, user.getId())
//        return CreatedThreadResponse(newId)
//    }
}


//    @PutMapping("/update/{id}")
//    fun update(
//        @PathVariable id: Long,
//        @RequestBody body: PutThreadUpdateRequest,
//        @AuthenticationPrincipal user: MessageBoardUserDetailsService
//    ): ThreadUpdateResponse {
//        val thread = service.updateTitle(id, body.title, user.getId())
//        return ThreadUpdateResponse(thread.id, thread.title)
//    }
//
//    @DeleteMapping("/delete/{id}")
//    fun deleteThread(
//        @PathVariable id: Long,
//        @AuthenticationPrincipal user: MessageBoardUserDetailsService
//    ): ThreadDeleteResponse {
//        val result = service.deleteThread(id)
//        return ThreadDeleteResponse(result.id, result.title, result.updatedAt)
//    }
//}
