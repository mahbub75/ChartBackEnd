package Project.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
UserRepository userRepository;
    @GetMapping("/users")
    @CrossOrigin
    public List<User> index(){
        return userRepository.findAll();
    }


    @GetMapping("/user/{id}")
    @CrossOrigin
    public User getUserById(@PathVariable String id){
        int userId = Integer.parseInt(id);
        return userRepository.findOne(userId);
    }

    @PostMapping("user")
    public void create(@RequestBody Map<String, String> body){
        String name = body.get("name");
        String password = body.get("password");
        String FirstLastName = body.get("first_last_name");
        userRepository.save(new User(name,password,FirstLastName));
    }


}
