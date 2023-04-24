package vttp.csf.finalproject.server.Repositories;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import vttp.csf.finalproject.server.Models.EmailDets;
import vttp.csf.finalproject.server.Models.User;
import vttp.csf.finalproject.server.Models.UserMapper;
import vttp.csf.finalproject.server.Services.EmailService;
@Repository
public class UserRepo {
// implements UserRepoInt 
// @Value("${server.port}")
// String serverPort;

@Value("${ui_app_base_url}")
String ui_app_base_url;

Logger logger = LoggerFactory.getLogger(UserRepo.class);

JdbcTemplate jdbcTemplate;


private final String SQL_FIND_USER = "select id, name,username,email_id,profile_picture from users where users.id = ?";
private final String SQL_DELETE_USER = "delete from users where id = ?";
private final String SQL_UPDATE_USER = "update users set "
		+ "name = ?, username = ? where id = ?";
private final String SQL_GET_ALL = "select users.id, users.name,users.username,users.email_id,users.profile_picture from users";
private final String SQL_INSERT_USER = "insert into users("
		+ "name, email_id, username, password, emailVerificationCode) values(?,?,?,?,?)";
private final String SQL_FIND_LOGIN_USER = "select users.id,users.name,users.username,users.email_id from users "
		+ "where username=? limit 1";
private final String SQL_UPDATE_USER_VERIFY_EMAIL = "update users set "
		+ "isEmailVerified = 1 where email_id = ? AND "
		+ "emailVerificationCode=?";
private final String SQL_UPDATE_USER_PROFILE_PIC = "update users set "
		+ "profile_picture = ? where id = ?";
private final String SQL_FIND_USER_PROFILE_PIC = 
		"select profile_picture from users where users.id = ?";
private final String SQL_UPDATE_USER_PASSWORD = "update users set "
		+ "password  = ? where id = ?";

//  private final String SQL_FIND_LOGIN_USER = "select users.id,users.username,users.email_id from users "
//          + "where username=? OR email_id=? limit 1";

@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private EmailService emailService;

@Autowired
public UserRepo(DataSource dataSource) {
	jdbcTemplate = new JdbcTemplate(dataSource);
}

@SuppressWarnings("deprecation")
public User getUserById(int id) {
	try {
		return jdbcTemplate.queryForObject(SQL_FIND_USER, new Object[] { id }, 
				new UserMapper());          
	}catch(EmptyResultDataAccessException e) {
		return null;
	}
}

public List<User> getAllUsers() {
	return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
}

public boolean deleteUser(int id) {
	return jdbcTemplate.update(SQL_DELETE_USER, id) > 0;
}

public boolean updateUser(User user) {
	return jdbcTemplate.update(SQL_UPDATE_USER, 
			user.getName(),  
			user.getUsername(),
			user.getId()) > 0;
}

public boolean updateUserPassword(User user) {
	return jdbcTemplate.update(SQL_UPDATE_USER_PASSWORD, 
			passwordEncoder.encode(user.getPassword()), 
			user.getId()) > 0;
}

public boolean verifyUserEmail(String email,String verificationCode) {
	return jdbcTemplate.update(SQL_UPDATE_USER_VERIFY_EMAIL, email, 
			verificationCode) > 0;
}

public String createUser(User user) {   
	String response = "";
	try {
		String emailVerificationCode = randomAlphanumericString(50);
		System.out.println(emailVerificationCode);
		response = jdbcTemplate.update(SQL_INSERT_USER, user.getName(), user.getEmailId(), 
				user.getUsername(),
				passwordEncoder.encode(user.getPassword()), emailVerificationCode) > 0 ? 
						"created":"not created";            
				
		EmailDets emailDetails = new EmailDets();
		emailDetails.setRecipient(user.getEmailId());
		emailDetails.setSubject("Touristy Email Verification");
		emailDetails.setMsgBody("Click this link to verity your email address: " + 
		ui_app_base_url + "/users/verifyEmail/"+user.getEmailId()+
		"/"+emailVerificationCode);
		
		String emailSentStatus
				= emailService.sendSimpleMail(emailDetails);

	}catch(DuplicateKeyException e) {
		if(e.getMessage().indexOf("];")!=-1) {
			response = e.getMessage().substring(e.getMessage().indexOf("];")+2);                        
		}else {
			response = e.getMessage();
		}
	}
	
	return response;
}

@SuppressWarnings("deprecation")
public User loginUser(Map<String, String> userLoginMap) {
	try {
//          User returnedUser = jdbcTemplate.queryForObject(SQL_FIND_LOGIN_USER, 
//                  new Object[] { user.getUsername() }, 
//                  new UserMapper());          
		
		List<User> userList = jdbcTemplate.query("select users.id,users.name,"
				+ "users.profile_picture,"
				+ "users.username,users.email_id,users.password from users "
				+ "where email_id='" + userLoginMap.get("username_email") 
				+ "' OR username='" + userLoginMap.get("username_email")
				+ "' limit 1", new UserMapper());
		
		if(userList.size()>0) {
			User returnedUser = userList.get(0);

			boolean result = passwordEncoder.matches(userLoginMap.get("password"), 
					returnedUser.getPassword());
			if(result) {
				returnedUser.setPassword("");
				return returnedUser;
			}               
		}
			
		return null;
	}catch(EmptyResultDataAccessException e) {
		logger.error(e.getMessage());
		return null;
	}catch(IncorrectResultSizeDataAccessException e) {
		logger.error(e.getMessage());
		return null;            
	}
}

public String getProfilePicNameById(int userId) {
	String profilePic = null;
	try {
		User user = jdbcTemplate.queryForObject(SQL_FIND_USER_PROFILE_PIC, new Object[] { userId }, 
				new UserMapper());
		if(user!=null) {
			profilePic = user.getProfilePic();
		}
	}catch(EmptyResultDataAccessException e) {
		return null;
	}
	
	return profilePic;
}

public boolean uploadProfilePic(int userId, String profilePicName) {
	return jdbcTemplate.update(SQL_UPDATE_USER_PROFILE_PIC, userId, profilePicName) > 0;
}

public static String randomAlphanumericString(int length) {
	String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuv";
	StringBuffer randomString = new StringBuffer(length);
	Random random = new Random();
	
	for (int i = 0; i < length; i++) {
		int randomIndex = random.nextInt(alphanumericCharacters.length());
		char randomChar = alphanumericCharacters.charAt(randomIndex);
		randomString.append(randomChar);
		}
	return randomString.toString();
	}
}

