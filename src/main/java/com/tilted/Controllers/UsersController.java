package com.tilted.Controllers;

import com.tilted.Models.UserDTO;
import com.tilted.Models.ValidationError;
import com.tilted.Services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService userService;

    public UsersController(UsersService usersService) {
        this.userService = usersService;
    }

    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return userService.GetAll();
    }

    @PostMapping("/check")
    public ResponseEntity<?> getUserById(@Valid @RequestBody String login) {
        boolean response = userService.GetById(login);
        var responseModel = new HashMap<String, Boolean>();
        responseModel.put("status", response);
        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
        var createdUser = userService.Create(user);
        var responseModel = new HashMap<String, Integer>();
        responseModel.put("id", createdUser.Id);

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationError handleValidationErrors(MethodArgumentNotValidException ex) {
        var validationError = new ValidationError();
        validationError.Status = "Bad request";

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            validationError.Errors.put(fieldName, errorMessage);
        });

        return validationError;
    }
}
