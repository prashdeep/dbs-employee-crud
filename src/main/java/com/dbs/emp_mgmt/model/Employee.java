package com.dbs.emp_mgmt.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "Employee")
@Data
public class Employee implements Serializable, Comparable<Employee> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "department_name")
    private String departmentName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BankAccount> bankAccountSet = new HashSet<>();

    public Employee(String name, LocalDate dateOfBirth, String departmentName) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.departmentName = departmentName;
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccountSet.add(bankAccount);
        bankAccount.setEmployee(this);
    }
    @Override
    public int compareTo(Employee employee) {
        return (int) (this.id - employee.id);
    }

}