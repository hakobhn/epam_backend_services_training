package com.epam.training.backend_services.authdemo.web;

import com.epam.training.backend_services.authdemo.service.LoginAttemptService;
import com.epam.training.backend_services.authdemo.web.dto.BlockedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/blocked")
@RequiredArgsConstructor
public class BlockedController {
    private final LoginAttemptService loginAttemptService;

    @GetMapping
    public String getAllBlocked(Model model) {
        Map<String, LocalDateTime> blockedUsers = loginAttemptService.getBlockedUsers();
        List<BlockedDto> blockeds = blockedUsers.entrySet().stream()
                .map(e -> new BlockedDto(e.getKey(), loginAttemptService.shouldWait(e.getKey())))
                .collect(Collectors.toList());
        model.addAttribute("blockedUsers", blockeds);
        model.addAttribute("module", "blocked");
        return "blocked";
    }

}
