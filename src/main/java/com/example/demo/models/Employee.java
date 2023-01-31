package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tb_tr_employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "email", nullable = false)
    private String Email;

    @Column(name = "fullname", nullable = false)
    private String Fullname;

    @Column(name = "birthdate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Birthdate;

    @Column(name = "address", nullable = false)
    private String Address;

    @Column(name = "istrainer", nullable = false)
    private Boolean Istrainer;

    
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public LocalDate getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        Birthdate = birthdate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Boolean getIstrainer() {
        return Istrainer;
    }

    public void setIstrainer(Boolean istrainer) {
        Istrainer = istrainer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
