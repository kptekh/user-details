package com.encrypt.user.controller;

import com.encrypt.user.model.UserRequest;
import com.encrypt.user.entity.User;
import com.encrypt.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Objects;

@RestController
@Validated
@Api(value = "UserController", tags = {"User"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "New User creation", notes = "Creating the new User", tags={ "User"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the User"),
            @ApiResponse(code = 201, message = "User saved in the database"),
            @ApiResponse(code = 400, message = "Invalid data", response = String.class),
            @ApiResponse(code = 415, message = "The content type is unsupported"),
            @ApiResponse(code = 500, message = "An unexpected error has occurred. The error has been logged and is being investigated.") })

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRequest> saveUser(@Valid @RequestBody UserRequest user) throws UnsupportedEncodingException {
        User savedUser = userService.save(this.convertToEntity(user));
        UserRequest userDto = this.convertToDto(savedUser);
        return ResponseEntity.created(URI.create("/users/" + URLEncoder.encode(userDto.getFirstName(), "UTF-8"))).body(userDto);
    }

    @ApiOperation(value = "Last name search", notes = "Fetch the user by Last name", tags={ "User"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Fetched the user successfully"),
            @ApiResponse(code = 201, message = "User saved in the database"),
            @ApiResponse(code = 400, message = "Invalid data", response = String.class),
            @ApiResponse(code = 415, message = "The content type is unsupported"),
            @ApiResponse(code = 500, message = "An unexpected error has occurred. The error has been logged and is being investigated.") })

    @GetMapping(value = "/user/{lastName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRequest> getUserByLastName(@PathVariable("lastName") String lastName) throws UnsupportedEncodingException {
        User user = userService.getUserByLastName(lastName);
        if(Objects.nonNull(user)) {
            UserRequest userDto = this.convertToDto(user);
            return ResponseEntity.created(URI.create("/users/" + URLEncoder.encode(userDto.getFirstName(), "UTF-8"))).body(userDto);
        }else{
            return ResponseEntity.created(URI.create("/users/" + URLEncoder.encode(lastName, "UTF-8"))).body(null);
        }
    }

    private User convertToEntity(UserRequest userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserRequest convertToDto(User user) {
        return modelMapper.map(user, UserRequest.class);
    }

}
