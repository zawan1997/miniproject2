package vttp.csf.finalproject.server.Controller;

import java.util.HashMap;
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

// import vttp.csf.finalproject.server.Config.JwtGenerator;
import vttp.csf.finalproject.server.Models.User;
import vttp.csf.finalproject.server.Services.UserService;


@RestController
@RequestMapping("api/v1/users")
public class UserController {
		@Autowired
		UserService userService;    
		
		// @Autowired
		// JwtGenerator jwtGenerator;
		
		@PostMapping("/create")
		public ResponseEntity<User> createUser(@RequestBody User userReq) {     
			User res = userService.create(userReq);
			System.out.println("res : "+res);
			
			ResponseEntity<User> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
			
			return responseEntity;
		
		}
		
		@PutMapping("/update")
		public ResponseEntity<User> updateUser(@RequestBody User userReq) {     
			User res = userService.update(userReq);
			System.out.println("res : "+res);
			
			ResponseEntity<User> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
			
			return responseEntity;
		}
		
		@PutMapping("/updatePassword")  
		public ResponseEntity<User> updateUserPassword(@RequestBody User userReq) {     
			User res = userService.updatePassword(userReq);
			System.out.println("res : "+res);
			
			ResponseEntity<User> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
			
			return responseEntity;
		}
		
		@GetMapping({""})
		public ResponseEntity<List<User>> getAllUsers() {
			List<User> res = userService.get();
			System.out.println("res : "+res);
			
			ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(res, HttpStatus.ACCEPTED);
			
			return responseEntity;
		}
	
		@GetMapping(path = {"/get/{id}"})
		public ResponseEntity<User> getUser(@PathVariable(name="id",required=true) int id) {
			System.out.println("id : "+id);
	
			User res = userService.get(id);
			System.out.println("res : "+res);
			
			ResponseEntity<User> responseEntity = new ResponseEntity<>(res, HttpStatus.ACCEPTED);
			
			return responseEntity;
		}
	
		@GetMapping("/verifyEmail/{email}/{verificationCode}")
		public boolean verifyEmail(@PathVariable(name="email",required=true) String email,
				@PathVariable(name="verificationCode",required=true) String verificationCode) {     
			boolean res = userService.verifyEmail(email, verificationCode);
			System.out.println("res : "+res);
			
			return res;
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
			String res = userService.delete(id);
			System.out.println("res : "+res);
			
			ResponseEntity<String> responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
			
			return responseEntity;
		}
		
		@PostMapping("/login")
		public ResponseEntity<Map<String, String>> loginUser(

				@RequestBody Map<String, String> loginCreds
		) {
	
				
				System.out.println(loginCreds);
				
				User res = userService.loginUser(loginCreds);
				
				Map<String, String> sampleRes = new HashMap<>();
				sampleRes.put("Response", "Wrong Credentials");
				
				ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<>(sampleRes, HttpStatus.NOT_FOUND);
				
				if(res!=null) {
					Map<String, String> responseMap = new HashMap<String, String>();
					// = jwtGenerator.generateToken(res);					
					responseMap.put("userId",String.valueOf(res.getId()));
					responseMap.put("email_id",res.getEmailId());
					responseMap.put("username",res.getUsername());
					responseMap.put("name",res.getName());
					responseMap.put("profile_picture",res.getProfilePic());
	
					responseEntity = new ResponseEntity<>(responseMap, 
							HttpStatus.ACCEPTED);                       
				}
	
			return responseEntity;
		}

	}
	
	
	
