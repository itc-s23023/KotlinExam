package jp.ac.it_college.std.s23023.messageboard.application.service

import org.springframework.security.core.GrantedAuthority

class MessageBoardUserDetailsService(
    private val id: Long,
    private val username: String,
    private val password: String,
    private val authorities: Collection<GrantedAuthority>,
    private val enabled: Boolean = true,
    private val accountNonExpired: Boolean = true,
    private val accountNonLocked: Boolean = true,
    private val credentialsNonExpired: Boolean = true
) {

    fun getId(): Long {
        return id
    }
}

//https://spring.pleiades.io/spring-security/reference/