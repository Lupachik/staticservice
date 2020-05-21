package com.lupachik.staticservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @Override
    public String toString() {
        return getTitle();
    }
}
