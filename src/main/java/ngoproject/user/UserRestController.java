package ngoproject.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ngoproject.model.LoginRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user")
class UserRestController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public void registerUser(@RequestBody User user) {
		userService.saveUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Optional<User> optional = userService.getUserByEmailAndPassword(email, password);
		
		if(optional.isPresent() ) {
			
			User user = optional.get();
			return ResponseEntity.ok(user);
			
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User uUser) {
		uUser.setId(id);
		String updateMessage = userService.updateUser(uUser);
		return ResponseEntity.ok(updateMessage);
	}
	
	@DeleteMapping("deleteUser/{id}")
	ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		String updateMessage = userService.deleteUser(id);
		return ResponseEntity.ok(updateMessage);
	}
}
