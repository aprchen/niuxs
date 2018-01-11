package cn.niuxs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.niuxs.domain.User;
import cn.niuxs.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * DAO
     */
    private final UserRepository userRepository;

    /**
     *  注入传参
     * @param userRepository {@link UserRepository}
     */
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/")
    public User save(@RequestParam String name){
        User user = new User();
        user.setName(name.trim());
        if(this.userRepository.save(user)){
            System.out.printf("用户 %s 保存成功\n",user);
        }
        return  user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id){
        return this.userRepository.get(id);
    }



}
