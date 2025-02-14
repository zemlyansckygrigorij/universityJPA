package com.learn.universityjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class TestController
 * для работы с web сайтом /test
 *
 * localhost:8082/test
 */
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    @GetMapping("/welcome")
    public String welcome() {
        return "This is unprotected page";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String pageForUser() {
        return "This is page for only users";
    }


    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdmins() {
        return "This is page for only admins";
    }


    @GetMapping("/all")
    public String pageForAll() {
        return "This is page for all employees";
    }
}
