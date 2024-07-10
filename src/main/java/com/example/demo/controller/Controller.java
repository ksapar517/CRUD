package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("user"))
@RequiredArgsConstructor
public class Controller {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }
    @GetMapping("/read")
    public ResponseEntity<List<UserDto>> read() {
        return ResponseEntity.ok(userService.read());
    }
    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody UserDto info) {
        return ResponseEntity.ok(userService.update(info));
    }

    @GetMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
