package com.epam.training.backend_services.rest.controller;

import com.epam.training.backend_services.rest.dto.UserRequestDto;
import com.epam.training.backend_services.rest.dto.UserResponseDto;
import com.epam.training.backend_services.rest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api")
@CrossOrigin("*")
@Tag(name = "Users API")
public class UserController {

    @Autowired
//    @Qualifier("HeapUserService")
    @Qualifier("DbUserService")
    private UserService userService;

    @Operation(summary = "Add new user", description = "Returns a newly created user", tags = { "users", "post" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created", content = {
                    @Content(schema = @Schema(implementation = UserResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bed request")
    })
    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Validated @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequestDto));
    }

    @Operation(summary = "Get users list", description = "Returns a list of all users", tags = { "users", "get" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Operation(summary = "Get a user by id", description = "Returns a user as per the id", tags = { "users", "get" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The user was not found")
    })
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @Operation(summary = "Update the given user", description = "Returns updated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated")
    })
    @PutMapping("/users")
    public ResponseEntity<UserResponseDto> updateUser(@Validated @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.update(userRequestDto));
    }

    @Operation(summary = "Delete given user by its id", description = "Remove user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted")
    })
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
