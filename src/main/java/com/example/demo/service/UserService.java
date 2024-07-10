package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String create(UserDto userDto) {
        try {
            if (userDto.getLastname() != null && userDto.getName() != null && userDto.getBirthday() != null) {
                User user = new User();
                user.setLastname(userDto.getLastname());
                user.setName(userDto.getName());
                user.setBirthday(userDto.getBirthday());
                userRepository.save(user);

                return "Success full";
            } else {
                return "Missing required fields";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<UserDto> read() {
        UserDto resp = new UserDto();
        try {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setLastname(user.getLastname());
            userDto.setName(user.getName());
            Timestamp timestamp = (Timestamp) user.getBirthday();
            if (timestamp != null) {
                userDto.setBirthday(new Date(timestamp.getTime()));
            }
            userDtos.add(userDto);
        }
        return userDtos;
        } catch (Exception e) {

            System.out.println(e.getMessage());
            resp.setError(e.getMessage());
            return Collections.singletonList(resp);
        }
    }


    public User update(UserDto userDto) {
        User response = new User();
        try {
            User user = userRepository.findById(userDto.getId());
            System.out.println(user);
            if(user.getId()!=null) {
                if(userDto.getLastname() !=null)
                user.setLastname(userDto.getLastname());
                if(userDto.getName() !=null)
                user.setName(userDto.getName());
                if(userDto.getBirthday() !=null)
                user.setBirthday(userDto.getBirthday());
                userRepository.save(user);
                response.setStatusCode(200);

                return user;
            }
            else {
                response.setStatusCode(404);
                return response;
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
            return response;
        }

    }
    @Transactional
    public String delete(Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return "Deleted Successfully";
            } else {
                return "User not found";
            }
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }

}
