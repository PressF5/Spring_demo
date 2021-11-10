package com.app.inventory.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "offices")
@Data
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "office_number", nullable = false)
    private int officeNumber;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_office")
    private List<Item> itemList;

//    public void addItemToOffice(Item item) {
//        if(Objects.nonNull(itemList))
//            itemList = new ArrayList<>();
//        itemList.add(item);
//    }
//
//    public void addItemsToOffice(List<Item> items) {
//        if(Objects.nonNull(items))
//            itemList = items;
//    }
}
