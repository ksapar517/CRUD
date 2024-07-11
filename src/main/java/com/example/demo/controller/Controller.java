package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class Controller {
    private final UserService userService;

    @PostMapping("/create")
    public String create(@ModelAttribute("User") UserDto userDto) {
        System.out.println(userDto);
        userService.create(userDto);
        return "userCreate";
    }
    @GetMapping("/read")
    public String read(Model model) {
        model.addAttribute("users", userService.read());
        return "read";
    }

    @GetMapping("/info/{userId}")
    public String getUserInfo(@PathVariable Long userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "info";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("User") UserDto userDto) {
        userService.update(userDto);
        return "userUpdate";
    }

    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable Long userId, Model model) {
        System.out.println(userId);
        String aa = userService.delete(userId);
        if(aa != null) {
            return "delete";
        }else
        return "error";
    }
}
