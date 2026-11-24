package com.amancino.ecom_proj.controller;

import com.amancino.ecom_proj.model.Users;
import com.amancino.ecom_proj.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "User",
        description = "Endpoints for managing users"
)
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Operation(
            summary = "Register a new user",
            description = """
        Creates a new user in the application and save it to database.
        Returns the newly created user.
        """
    )
    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return service.verify(user);
    }
}
