package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("interface")
public class InterfaceController {
    private final UserService userService;

    @GetMapping("create")
    public String create() {
        System.out.println("create");
        return "create";
    }

    @GetMapping("/update/{userId}")
    public String update(@PathVariable Long userId, Model model) {
        System.out.println(userId);
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "update";
    }

}
