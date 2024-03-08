package ngoproject.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserService {

	@Autowired
	UserRepository userRepository;
	
	Optional<User> getUserByEmailAndPassword(String email, String password) {
		return Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));
	}
	
	String saveUser(User user) {
		userRepository.save(user);
		return "User saved succesfully";
	}
	
	List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	String updateUser(User uUser) {
		Optional<User> optional = userRepository.findById(uUser.getId());
		
		if(optional.isPresent()) {
			User user = optional.get();
			user.setEmail(uUser.getEmail());
			user.setFirstName(uUser.getFirstName());
			user.setLastName(uUser.getLastName());
			user.setRole(uUser.getRole());
			user.setPassword(uUser.getPassword());
			
			// save user
			userRepository.save(user);
			
			return "User updated Successfully";
		} else {
			throw new RuntimeException("BE User Update Service Fail ID: "+ uUser.getId());
		}
	}
	
	String deleteUser(Integer id) {
		Optional<User> optional = userRepository.findById(id);
		
		if(optional.isPresent()) {
			User user = optional.get();
			userRepository.delete(user);
			return "User delete successful";
		} else {
			throw new RuntimeException("BE Delete User Fail ID: " + id);
		}
	}
}
