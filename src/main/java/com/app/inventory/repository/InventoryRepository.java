package com.app.inventory.repository;

import com.app.inventory.entity.Item;
import com.app.inventory.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class InventoryRepository {
    @Autowired
    private EntityManager entityManager;

    public void saveInventory(Item item) {
        int officeNumber = item.getOffice().getOfficeNumber();
        TypedQuery<Office> query = entityManager.createQuery("from Office o where o.officeNumber = :officeNumber", Office.class);
        query.setParameter("officeNumber", officeNumber);
        if(query.getResultList().size() != 0)
            item.getOffice().setId(query.getSingleResult().getId());
        entityManager.merge(item);

    }
}
