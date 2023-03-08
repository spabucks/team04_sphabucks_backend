package sphabucks.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.users.model.User;
import sphabucks.users.service.IUserService;
import sphabucks.users.vo.RequestUser;
import sphabucks.users.vo.ResponseUser;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class UserController {
    private final IUserService iUserService;

    @PostMapping("/add")
    public void addUser(@RequestBody RequestUser requestUser) {

        iUserService.adduser(requestUser);
    }

    @GetMapping("/get/{id}")
    public ResponseUser getUser(@PathVariable Long id) {
        return iUserService.getUser(id);
    }

    @GetMapping
    public ResponseUser getUserByEmail(@RequestParam String email) {
        return iUserService.getUserByEmail(email);
    }

    @GetMapping("get/all")
    public List<User> getAll(){
        return iUserService.getAll();
    }
}
