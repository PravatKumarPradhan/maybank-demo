
package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;


/**
 * @author Pravat
 */

@Entity
@Table(name = "customer")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    Long id;
    @Column
    String customerName;
    @Column
    String accountNumber;
    @Column
    String accountType;
    @Column
    Date dob;

    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedDate;
    @Column(name = "updated_by")
    String updatedBy;
    @Column(name = "created_by")
    String createdBy;


    @PrePersist
    void createdAt() {
        this.createdDate = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedDate = new Date();
    }


}

