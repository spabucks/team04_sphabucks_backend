package sphabucks.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sphabucks.users.model.User;
import sphabucks.users.service.IUserService;
import sphabucks.users.vo.RequestUser;
import sphabucks.users.vo.ResponseUser;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @PostMapping("/add")
    public ResponseUser addUser(@RequestBody RequestUser requestUser) {
        return iUserService.adduser(requestUser);
    }

    @GetMapping("/get/{id}")
    public ResponseUser getUser(@PathVariable Long id) {
        return iUserService.getUser(id);
    }
}
