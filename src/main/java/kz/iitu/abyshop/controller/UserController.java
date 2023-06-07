package kz.iitu.abyshop.controller;

import kz.iitu.abyshop.dto.user.SignupResponseDto;
import kz.iitu.abyshop.dto.user.SignInResponseDto;
import kz.iitu.abyshop.dto.user.SignupDto;
import kz.iitu.abyshop.dto.user.SignInDto;
import kz.iitu.abyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(userService.signup(signupDto), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInDto signInDto){
        return new ResponseEntity<>(userService.signIn(signInDto), HttpStatus.OK);
    }
}
