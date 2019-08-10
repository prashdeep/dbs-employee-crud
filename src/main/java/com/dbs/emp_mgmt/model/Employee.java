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
public class Employee implements Serializable, Comparable<Employee> {

    public Employee(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="EMPLOYEE_ID")
    private long id;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "department_name")
    private String departmentName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BankAccount> bankAccountSet = new HashSet<>();
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYEE_COLLEAGE",
                joinColumns={@JoinColumn(name="EMPLOYEE_ID")},
                inverseJoinColumns = {@JoinColumn(name="COLLEAGE_ID")} 
              )
    private Set<Employee> colleagues = new HashSet<>();

    @ManyToMany(mappedBy = "colleagues")
    private Set<Employee> teamMates = new HashSet<>();

    public Set<Employee> getColleagues() {
        return colleagues;
    }

    public void setColleagues(Set<Employee> colleagues) {
        this.colleagues = colleagues;
    }


    public void addColleague(Employee colleague){
        colleague.getTeamMates().add(this);
        this.getColleagues().add(colleague);
    }
    public Set<Employee> getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(Set<Employee> teamMates) {
        this.teamMates = teamMates;
    }

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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<BankAccount> getBankAccountSet() {
        return bankAccountSet;
    }

    public void setBankAccountSet(Set<BankAccount> bankAccountSet) {
        this.bankAccountSet = bankAccountSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(dateOfBirth, employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfBirth);
    }
}