package com.inventory.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Customer extends BaseEntity {

    private String name;
    private String email;
    private String address;
}
