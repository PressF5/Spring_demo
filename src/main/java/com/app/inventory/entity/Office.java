package com.app.inventory.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

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
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "office")
    private List<Item> itemList;

    public void setItemsToOffice(List<Item> item) {
        this.itemList = item;
        item.forEach(it -> {it.setOffice(this);});
    }

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
