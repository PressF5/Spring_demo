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
    private int id;
    @Column(name = "inventory_number", nullable = false)
    private int number;
    @Column(name = "description_item", nullable = true)
    private String description;
    @Column(name = "count_items", nullable = false)
    private int countItems;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_office")
    private Office office;

    public Item() {}

    public Item(int id, int number, String description, int countItems, int id_off, int off_number) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.countItems = countItems;
        this.office = new Office();
        this.office.setId(id_off);
        this.office.setOfficeNumber(off_number);
    }

    public Item(int number, String description, int countItems, int officeNumber){
        this.number = number;
        this.description = description;
        this.countItems = countItems;
        this.office = new Office();
        this.office.setOfficeNumber(officeNumber);
    }
    @Override
    public String toString() {
        return """
                id = %s
                number = %s
                description = %s
                countItems = %s
                office = %s
                """.formatted(id, number, description, countItems, office.getOfficeNumber());
    }
}
