package  com.example.demo.service

import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository){

    @Transactional(readOnly = true)
    fun findAll(): Iterable<User> {
        return userRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    @Transactional
    fun save(user: User): User {
        return userRepository.save(user)
    }
    @Transactional
    fun deleteById(id: Long){
        return userRepository.deleteById(id)
    }

    @Transactional
    fun updateUserEmail(id: Long, newEmail: String) {
        val user = userRepository.findById(id).orElseThrow { RuntimeException("User not found") }
        userRepository.save(user.copy(email = newEmail))
    }
}