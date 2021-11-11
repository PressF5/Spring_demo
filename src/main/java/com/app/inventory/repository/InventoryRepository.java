package com.app.inventory.repository;

import com.app.inventory.entity.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class InventoryRepository {
    @Autowired
    private EntityManager entityManager;

    public void saveInventory(Office office) {
        entityManager.merge(office);
        System.out.println("Инвентарь добавлен!!!");
    }
}
