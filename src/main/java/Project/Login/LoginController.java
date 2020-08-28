package Project.Login;
import Project.ExceptionHandler.CustomException;
import Project.User.User;
import Project.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;



    @PostMapping("/login")
    @CrossOrigin
    public User login(@RequestBody Map<String, String> body) throws Exception{
        String name = body.get("name");
        String password = body.get("password");
        User user = userRepository.findByNameAndPassword(name,password);
        if(user==null){
            throw new CustomException("نام کاربری یا رمز عبور اشتباه است");
        }
        return user;
    }


}
