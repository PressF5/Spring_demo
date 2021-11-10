package com.app.inventory.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "number", nullable = false)
    private Long number;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "count_items", nullable = false)
    private int countItems;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_office")
    private Office office;
}
