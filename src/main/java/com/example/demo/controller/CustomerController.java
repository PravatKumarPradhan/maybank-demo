package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.util.ResponseDTO;
import com.example.demo.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  Pravat
 */
@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    final CustomerService customerService;


    /**
     *
     * @param data
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO data) {
        String message = customerService.createCustomer(data);
        ResponseDTO responseDTO = new ResponseDTO();
        Status status = new Status();
        if (message.equalsIgnoreCase("0")) {
            status.setStatusMessage("OK");
            status.setCode("0");
            responseDTO.setStatus(status);
            responseDTO.setMessage("Account Number Generated Successfully");
            return new ResponseEntity(responseDTO, HttpStatus.CREATED);
        } else {
            status.setStatusMessage("NOK");
            status.setCode("1");
            responseDTO.setStatus(status);
            responseDTO.setMessage("Account Number Not  Generated ");
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }
    }
}
