package com.green.school.green.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "siswa")
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "nik")
    public String nik;

    @Column(name = "gender")
    public String gender;

    @Column(name = "address")
    public String address;

    @Column(name = "pob")
    public String pob;

    @Column(name = "dob")
    public String dob;

    @Column(name = "email")
    public String email;

    @Column(name = "created_at")
    public Date createdAt;

    @Column(name = "updated_at")
    public Date updatedAt;

    @PrePersist
    public void setModel() {
        this.createdAt = new Date();
    }
}
