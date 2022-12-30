package net.javaguide.springbackend.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import net.javaguide.springbackend.model.Users;
import net.javaguide.springbackend.repository.UserInterface;

import org.apache.tomcat.util.json.JSONParser;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;
//import exepction.ResourceNotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserInterface userRepository;

	@Autowired
//	private BCrypt bCryptPasswordEncoder;

	@GetMapping("/employees")
	public List<Users> getAllEmployees() {

		return userRepository.findAll();
	}

	@PostMapping("/register")
	public Users createUser(@RequestBody Users user) {
		String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		Users users = new Users();
		users.setName(user.getName());
		users.setPassword(encodedPassword);
		users.setEmail(user.getEmail());
		users.setPhone(user.getPhone());
		users.setAddress(user.getAddress());
		users.setAvatar(user.getAvatar());
		users.setAccountBalance(user.getAccountBalance());
		users.setRole(user.getRole());
//		return users;
		return userRepository.save(users);

	}

	@PostMapping("/login")
	public String login(@RequestBody Users loginForm) {
		LinkedHashMap<String, Object> result = new LinkedHashMap();
		Users user = null;
		try {
			user = userRepository.findByEmail(loginForm.getEmail());
			if (user != null) {
				boolean valuate = BCrypt.checkpw(loginForm.getPassword(), user.getPassword());
				if (valuate == true) {
					result.put("infor", "login success");
					result.put("code", 200);
					result.put("msg", "success");
					result.put("data", user);

				} else {
					result.put("infor", "wrong passsword");
					result.put("code", 202);
					result.put("data", "success");
				}
			} else {
				result.put("info", "user not exist");
				result.put("code", 201);
				result.put("data", "success");
			}
		} catch (Exception e) {
			result.put("info", "error");
			result.put("code", 400);
			result.put("data", "fail");
			System.out.println(e);

		}

		String json = JSON.toJSON(result).toString();
		return json;

	}

	@PostMapping("/register-user")
	public String register(@RequestBody Users registerForm) {
		LinkedHashMap<String, Object> result = new LinkedHashMap();
		String encodedPassword = BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt(12));
		try {
			Users user = new Users();
			user.setName(registerForm.getName());
			user.setAccountBalance("1000");
			user.setAddress(registerForm.getAddress());
			user.setAvatar(registerForm.getAvatar());
			user.setEmail(registerForm.getEmail());
			user.setPassword(encodedPassword);
			user.setPhone(registerForm.getPhone());
			user.setRole("2");
			userRepository.save(user);
			result.put("infor", "register success");
			result.put("code", 200);
			result.put("msg", "success");
		} catch (Exception e) {
			result.put("infor", "register failed");
			result.put("code", 400);
			result.put("msg", "failed");
		}

		String json = JSON.toJSON(result).toString();
		return json;
	}

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Users emailPass) {
//			Users user = userRepository.findByEmail();
//		
////			return users;
//	}
}
