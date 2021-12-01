package com.app.inventory.repository;

import com.app.inventory.entity.Item;
import com.app.inventory.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class InventoryRepository {
    @Autowired
    private EntityManager entityManager;

    public TypedQuery<Item> getItemByInventoryNumberAndOfficeNumber(int invNumber, int officeNumber) {
        return entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i left join i.office io where i.number = :invNumber and io.officeNumber = :officeNumber", Item.class).
                setParameter("invNumber", invNumber).
                setParameter("officeNumber", officeNumber);
    }

    public TypedQuery<Office> getOfficeByOfficeNumber(int officeNumber) {
        return entityManager.createQuery("from Office o where o.officeNumber = :officeNumber", Office.class).
                setParameter("officeNumber", officeNumber);
    }

    public void updateCountItemsByIdItem(int countItems, int itemId) {
        entityManager.createNativeQuery("update items set count_items = " + countItems + " where id = " + itemId).
                executeUpdate();
    }

    public void deleteItemByIdItem(int itemId) {
        entityManager.createNativeQuery("delete from items where id = " + itemId).
                executeUpdate();
    }

    public void updateForeignKeyInItemByItemId(int officeId, int itemId) {
        entityManager.
                createNativeQuery("update items set id_office = " + officeId + " where id = " + itemId).
                executeUpdate();
    }

    public List<Item> getItemsByOffice(int numberOffice) {
        TypedQuery<Item> itemByOfficeNumber = entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i right join i.office io where io.officeNumber = :officeNumber", Item.class).
                setParameter("officeNumber", numberOffice);
        return itemByOfficeNumber.getResultList();
    }

    public List<Item> getItemsByInvNumber(int invNumber) {
        TypedQuery<Item> itemByInvNumber = entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i left join i.office io where i.number = :invNumber", Item.class).
                setParameter("invNumber", invNumber);
        return itemByInvNumber.getResultList();
    }

    public Item getItemById(int itemId) {
        return entityManager.createQuery("from Item where id = :id", Item.class).
                setParameter("id", itemId).getSingleResult();
    }

    public void deleteItemById(int itemId) {
        entityManager.createQuery("delete Item where id = :id").
                setParameter("id", itemId).
                executeUpdate();
    }

    public <T> T merge(T entity) {
        return entityManager.merge(entity);
    }
}
