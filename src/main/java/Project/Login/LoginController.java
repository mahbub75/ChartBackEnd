package Project.Login;

import Project.ExceptionHandler.CustomException;
import Project.User.User;
import Project.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;


    @PostMapping("/login")
    @CrossOrigin
    public User login(@RequestBody Map<String, String> body) throws Exception {
        String name = body.get("name");
        String password = body.get("password");
        Boolean o = userRepository.existsByName(name);
        Boolean o1 = userRepository.existsByNameAndPassword(name,password);
        if (o) {
           if (o1){
               return userRepository.findByNameAndPassword(name, password);
           } else {
               throw new CustomException("رمز عبور اشتباه است");
           }
        } else {
            throw new CustomException("کاربر"+name+" در سیستم ثبت نشده است");
        }


    }


}
