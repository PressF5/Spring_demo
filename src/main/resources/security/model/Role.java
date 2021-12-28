//package com.app.inventory.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//    @Column(name = "name")
//    private String name;
////    @ManyToMany
////    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"),
////            inverseJoinColumns = @JoinColumn(name = "user_id"))
////    private List<User> users = new ArrayList<>();
//}
