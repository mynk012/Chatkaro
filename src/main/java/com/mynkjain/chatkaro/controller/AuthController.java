package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.config.JwtProvider;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.UserRepository;
import com.mynkjain.chatkaro.request.LoginRequest;
import com.mynkjain.chatkaro.response.AuthResponse;
import com.mynkjain.chatkaro.service.CustomUserDetailsService;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) throws Exception {
        try{
            User isExist = userRepository.findByEmail(user.getEmail());

            if(isExist != null){
                throw new Exception("this email already used with another account");
            }
            User newUser = new User();

            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser=userRepository.save(newUser);

            Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

            String token = JwtProvider.generateToken(authentication);

            AuthResponse res = new AuthResponse(token, "Register Success");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

            String token = JwtProvider.generateToken(authentication);

            AuthResponse res = new AuthResponse(token, "Login Successful");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e) {

            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);


        if(userDetails == null){
            throw new BadCredentialsException("invalid username");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("wrong password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}

