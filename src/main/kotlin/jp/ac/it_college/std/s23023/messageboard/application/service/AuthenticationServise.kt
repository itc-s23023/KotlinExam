package jp.ac.it_college.std.s23023.messageboard.application.service

import jp.ac.it_college.std.s23023.messageboard.domain.model.Users
import jp.ac.it_college.std.s23023.messageboard.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService (
    private val userRepository: UserRepository
){
    fun findUser(email:String): Users? {
        return userRepository.findByEmail(email)
    }
}

