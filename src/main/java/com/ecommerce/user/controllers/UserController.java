package com.ecommerce.user.controllers;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class UserController {

    private final UserService userService;
    private Object user;
//    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
//        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getAUser(@PathVariable String id) {

        log.info("Request received for user: {}", id);

        log.trace("his is Trace level -- Very detailed logs");

        return userService.fetchAUsers(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok("User Added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
        boolean updated = userService.updateUser(id,userRequest);
        if (updated) return ResponseEntity.ok("User updated successfully");
        return ResponseEntity.notFound().build();
    }

}
