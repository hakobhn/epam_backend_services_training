package com.epam.training.backend_services.authdemo.web;

import com.epam.training.backend_services.authdemo.service.UserService;
import com.epam.training.backend_services.authdemo.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model){
        List<UserDto> users = new ArrayList<>(userService.getAll());
        model.addAttribute("users", users);
        model.addAttribute("module", "users");
        return "users";
    }

    @GetMapping(path = "/{id}")
    public String getUser(@PathVariable("id") long userId, Principal principal, Model model) {
        UserDto user = userService.get(userId);
        model.addAttribute("user", user);
        model.addAttribute("module", "users");
        return "detailed_user";
    }
}
