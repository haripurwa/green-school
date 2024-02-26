package com.green.school.green.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Dosen {
    @Id
    private UUID id;

    public String name;
    public String kelasDosen;
    public String email;
    public Date createdAt;
    public Date updatedAt;

    @PrePersist
    public void setModel() {
        this.createdAt = new Date();
        this.id = UUID.randomUUID();
    }
}
