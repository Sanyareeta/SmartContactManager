package com.scm.scm20.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name="user")
@Table
@AllArgsConstructor
@NoArgsConstructor
public class User {
@Id
private String userId;
@Column(nullable = false)
private String name;
@Column(unique=true,nullable = false)
private String email;
private String password;
    @Lob
    private String about;
    @Lob
    private String profilePic;
private String phoneNumber;
private boolean enabled=false;
private boolean emailVerified;
private boolean phoneNumberVerified;
@Enumerated(value=EnumType.STRING)
private Providers provider=Providers.SELF;
private String providerUserId;
@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
private List<Contact> contacts=new ArrayList<>();


}
