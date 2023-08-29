package com.example.demo.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Service.Impl.MyUserDetailsService;


@RestController
public class UserSignupController {

	@Autowired
	MyUserDetailsService myUserDetailService;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncorder;

//	@PostMapping("/users")
//	public User addUser(@RequestBody User user) {
//	
//		String password = user.getPassword();
//		String encodedpassword = bcryptPasswordEncorder.encode(password);
//		user.setPassword(encodedpassword);
//		user.setRoles(user.getRoles());
//		return myUserDetailService.addUser(user);
//
//	}
//	
	@PostMapping("/signup")
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		Map<String, String> errors = new HashMap<>();
		String field = null;
		String message = null;
		try {
			String pwd = user.getPassword();
			String bcryptpwd = bcryptPasswordEncorder.encode(pwd);
			user.setPassword(bcryptpwd);
			
			User savedUser = myUserDetailService.addUser(user);
			return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
		} catch (Exception ex) {

			field = "Registration Failed";
			
			message = ex.getMessage();
//
			// }
//				}
//			}
			// }
			errors.put(field, message);
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) {
		return myUserDetailService.getUserById(id);
	}

	@DeleteMapping("/users/{id}")
	public String deleteuser(@PathVariable int id) {
		myUserDetailService.deleteuser(id);
		return "User is deleted";
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return myUserDetailService.getAllUsers();
	}


}
