package com.example.demo.controllers;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")

public class HomeController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<LoginRequestDTO> homePage(Model model) {
        model.addAttribute("data", new LoginRequestDTO());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (dto.getEmail(), dto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.status(HttpStatus.OK).body("User Authorized");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO dto) {
        employeeService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Register Success");
        }
}
