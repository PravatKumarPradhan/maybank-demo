package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {



    @PostMapping("/add")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO data){
        return null;
    }
}
