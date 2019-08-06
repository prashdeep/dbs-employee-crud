package com.dbs.emp_mgmt.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String street;
    private String city;
    private String state;
    private String zip;

    @OneToOne(mappedBy = "address")
    @PrimaryKeyJoinColumn
    private Employee employee;
}