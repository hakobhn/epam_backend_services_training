package com.epam.training.backend_services.authdemo.web;

import com.epam.training.backend_services.authdemo.service.UserService;
import com.epam.training.backend_services.authdemo.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admins-preAuth")
@RequiredArgsConstructor
public class AdminPreauthController {
    private final UserService userService;

    @GetMapping
    public String getAllAdmins(Model model){
        List<UserDto> admins = new ArrayList<>(userService.getAllAdmins());
        model.addAttribute("admins", admins);
        model.addAttribute("module", "admins-preAuth");
        return "admins";
    }
}
