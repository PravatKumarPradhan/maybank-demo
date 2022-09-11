package com.example.demo.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO {

    String customerFirstName;
    String customerMiddleName;
    String customerLastName;
    Date dob;
    String idType;
    String idValue;
    String accountType;

    CustomerAddressDTO address;



}
