package com.softtechbootcamp.case3.app.usr.entity;

import com.softtechbootcamp.case3.app.gen.entity.BaseEntity;
import com.softtechbootcamp.case3.app.usr.enums.UsrUserType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usr_user")
public class UsrUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", length = 30)
    private UsrUserType usrUserType;

}
