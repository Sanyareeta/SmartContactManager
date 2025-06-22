package com.scm.scm20.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Contact {
 @Id
private String id;
private String name;
private String email;
private String phoneNumber;
private String address;
private String picture;
@Lob
private String description;
private boolean favourite=false;
private String websiteLink;
private String LinkedIn;
//private List<String> socialLinks=new ArrayList<>();
@ManyToOne
private User user;

 @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true )
 private List<SocialLink> socialLinks=new ArrayList<>();
}
