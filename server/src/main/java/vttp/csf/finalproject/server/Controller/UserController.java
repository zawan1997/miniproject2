package vttp.csf.finalproject.server.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.csf.finalproject.server.Config.JwtGenerator;
import vttp.csf.finalproject.server.Models.User;
import vttp.csf.finalproject.server.Services.UserService;


@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
	UserService userServiceImpl;	
	
	@Autowired
	JwtGenerator jwtGenerator;
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User userReq) {		
		User res = userServiceImpl.create(userReq);
		System.out.println("res : "+res);
		
		ResponseEntity<User> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
		
		return responseEntity;
	
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User userReq) {		
		User res = userServiceImpl.update(userReq);
		System.out.println("res : "+res);
		
		ResponseEntity<User> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@GetMapping(path = {""})
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> res = userServiceImpl.get();
		System.out.println("res : "+res);
		
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(res, HttpStatus.ACCEPTED);
		
		return responseEntity;
	}

	@GetMapping(path = {"/{id}"})
	public ResponseEntity<User> getUser(@PathVariable(name="id",required=true) int id) {
		System.out.println("id : "+id);

		User res = userServiceImpl.get(id);
		System.out.println("res : "+res);
		
		ResponseEntity<User> responseEntity = new ResponseEntity<>(res, HttpStatus.ACCEPTED);
		
		return responseEntity;
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		String res = userServiceImpl.delete(id);
		System.out.println("res : "+res);
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
		User res = userServiceImpl.loginUser(user);
		ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<>(jwtGenerator.generateToken(res), 
				HttpStatus.ACCEPTED);		

		return responseEntity;
	}
}
