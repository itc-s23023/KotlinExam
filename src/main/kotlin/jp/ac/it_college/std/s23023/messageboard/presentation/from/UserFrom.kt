package jp.ac.it_college.std.s23023.messageboard.presentation.from
import kotlinx.serialization.Serializable
@Serializable
data class PostUserRegisterRequest(
    val viewName: String,
    val email: String,
    val password: String
)

@Serializable
data class GetUserInfoResponse(
    val id: Long,
    val viewName: String,
)