package com.springboot.project2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double score;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;
}
