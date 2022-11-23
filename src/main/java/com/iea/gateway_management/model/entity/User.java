package com.iea.gateway_management.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@SequenceGenerator(name = "USERS_SEQUENCE", sequenceName = "USERS_SEQ", initialValue = 1, allocationSize = 1)
@Data
@Table(name = "USERS")
@Entity
public class User
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQUENCE")
    @Column(name = "USER_ID")
    @Id
    private Integer userID;

    @Column(nullable = false, unique = true, length = 120)
    private String username;

    @Column(nullable = false)
    private  String password;

    private Date created;
}
