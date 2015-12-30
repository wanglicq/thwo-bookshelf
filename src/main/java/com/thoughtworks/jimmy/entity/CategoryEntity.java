package com.thoughtworks.jimmy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "wo_category")
public class CategoryEntity {
    @Id
    @Column(name = "code", length = 30)
    private String code;

    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    @Column(name = "description")
    private String description;

    public CategoryEntity() {
    }

    public CategoryEntity(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
