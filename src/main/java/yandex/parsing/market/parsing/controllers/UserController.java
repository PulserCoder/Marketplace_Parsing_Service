package yandex.parsing.market.parsing.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yandex.parsing.market.parsing.models.User;
import yandex.parsing.market.parsing.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "create/")
    public String createUser(@RequestBody User user) {
        user.setId(null);
        User createdUser = userService.createUser(user);
        return "User created with ID: " + createdUser.getId();
    }
}
