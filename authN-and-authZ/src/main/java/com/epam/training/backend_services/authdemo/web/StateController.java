package com.epam.training.backend_services.authdemo.web;

import com.epam.training.backend_services.authdemo.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/states")
@RequiredArgsConstructor
public class StateController {
    private final StateService stateService;

    @GetMapping
    public String getAllStates(Model model){
        model.addAttribute("states", stateService.getAll());
        model.addAttribute("module", "states");
        return "states";
    }

    @GetMapping(path = "/info")
    public String getRandom(Model model) {
        model.addAttribute("state", stateService.getRandom());
        model.addAttribute("module", "states");
        return "random_state";
    }
}
