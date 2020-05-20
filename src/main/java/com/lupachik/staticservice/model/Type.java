package com.lupachik.staticservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
//    private Set<Event> events;

//    @Override
//    public String toString() {
//        return title;
//    }
}
