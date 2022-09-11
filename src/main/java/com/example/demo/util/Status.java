package com.example.demo.util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author  Pravat
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Status {
    String  code;
    String statusMessage;
}