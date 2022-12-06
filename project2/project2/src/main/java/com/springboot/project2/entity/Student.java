package com.springboot.project2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {
    @Id
    private Integer id;

    @Column(unique = true)
    private String studentCode;

    @OneToOne
    @PrimaryKeyJoinColumn //dung chung id với user
    private User user;
}
