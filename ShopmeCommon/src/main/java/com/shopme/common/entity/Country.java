package com.shopme.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, nullable = false, length=45)
    private String name;

    @Column(unique=true, nullable = false, length = 5)
    private String code;

    @OneToMany(mappedBy = "country")
    private Set<State> states = new HashSet<>();

    public Country(Integer id) {
        this.id = id;
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Country(Integer id, String name, String code, Set<State> states) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.states = states;
    }

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Country [code=" + code + ", id=" + id + ", name=" + name + ", states=" + states + "]";
    }
}
