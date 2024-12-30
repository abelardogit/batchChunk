package com.abelardolg.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name="last_name")
    private String lastName;
    private int age;
    @Column(name="created_at")
    private String createAt;
}
