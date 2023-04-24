package vttp.csf.finalproject.server.Services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import vttp.csf.finalproject.server.Models.User;
import vttp.csf.finalproject.server.Repositories.UserRepo;

@Service
public class UserService implements MainServiceInt<User> {
    @Autowired
    UserRepo userRepo;
    
    @Override
    public User create(@RequestBody User userReq) {     
        String res = userRepo.createUser(userReq);
        System.out.println("res : "+res);
                
        return userReq;
    }
    
    @Override
    public User update(User userReq) {      
        boolean res = userRepo.updateUser(userReq);
        System.out.println("res : "+res);
        
        return userReq;
    }
    
    public User updatePassword(User userReq) {      
        boolean res = userRepo.updateUserPassword(userReq);
        System.out.println("res : "+res);
        
        return userReq;
    }

    public boolean verifyEmail(String email, String verificationCode) {     
        boolean res = userRepo.verifyUserEmail(email, verificationCode);
        System.out.println("res : "+res);
        
        return res;
    }
    
    
    @Override
    public List<User> get() {
        List<User> res = userRepo.getAllUsers();
        System.out.println("get users res : "+res);
        
        return res;
    }
    
    @Override
    public User get(int id) {
        System.out.println("id : "+id);
        User res = userRepo.getUserById(id);
        System.out.println("res : "+res);

        return res;
    }

    @Override
    public String delete(int id) {
        boolean res = userRepo.deleteUser(id);
        System.out.println("res : "+res);
        
        return "User Delete Response is "+res;
    }

    public User loginUser(Map<String, String> userLoginMap) {
        User res = null;
        
        if(userLoginMap.containsKey("username_email") && userLoginMap.containsKey("password")) {
            res = userRepo.loginUser(userLoginMap);         
        }

        return res;
    }

    public String getProfilePicById(int userId) {
        String res = userRepo.getProfilePicNameById(userId);
        return res;
    }

    public boolean uploadProfilePic(int userId, String profilePicName) {
        boolean res = userRepo.uploadProfilePic(userId,profilePicName);
        return res;
    }


}
