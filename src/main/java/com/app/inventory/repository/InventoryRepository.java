package com.app.inventory.repository;

import com.app.inventory.entity.Item;
import com.app.inventory.entity.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void saveInventory(Office office) {
        Session session = sessionFactory.getCurrentSession();
        session.save(office);
        System.out.println("Инвентарь добавлен!!!");
    }
}
