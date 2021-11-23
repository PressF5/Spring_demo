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

    public void saveInventory(Item item) {
        int officeNumber = item.getOffice().getOfficeNumber();
        TypedQuery<Item> itemOffice = entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i left join i.office io where i.number = :invNumber and io.officeNumber = :officeNumber", Item.class).
                setParameter("invNumber", item.getNumber()).
                setParameter("officeNumber", item.getOffice().getOfficeNumber());
        if (itemOffice.getResultList().size() != 0) {
            Item item1 = itemOffice.getSingleResult();
            item1.setCountItems(item1.getCountItems() + item.getCountItems());
            entityManager.merge(item1);
        } else {
            TypedQuery<Office> office = entityManager.createQuery("from Office o where o.officeNumber = :officeNumber", Office.class);
            office.setParameter("officeNumber", officeNumber);
            if (office.getResultList().size() != 0)
                item.getOffice().setId(office.getSingleResult().getId());
            entityManager.merge(item);
        }
    }

    public void moveInventory(int fromOffice, int toOffice, int invNumber, int countItems) {

        TypedQuery<Item> fromQuery = entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i left join i.office io where i.number = :invNumber and io.officeNumber = :officeNumber", Item.class).
                setParameter("invNumber", invNumber).
                setParameter("officeNumber", fromOffice);

        int leftCount = fromQuery.getSingleResult().getCountItems() - countItems;

        TypedQuery<Item> toQuery = entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i left join i.office io where i.number = :invNumber and io.officeNumber = :officeNumber", Item.class).
                setParameter("invNumber", invNumber).
                setParameter("officeNumber", toOffice);

        TypedQuery<Office> officeQuery = entityManager.createQuery("from Office o where o.officeNumber = :officeNumber", Office.class);
        officeQuery.setParameter("officeNumber", toOffice);

        if (leftCount == 0) {
            if (toQuery.getResultList().size() != 0) {
                entityManager.createNativeQuery("update items set count_items = " + (toQuery.getSingleResult().getCountItems() + countItems) + " where id = " + toQuery.getSingleResult().getId()).
                        executeUpdate();
                entityManager.createNativeQuery("delete from items where id = " + fromQuery.getSingleResult().getId()).
                        executeUpdate();
            } else if (officeQuery.getResultList().size() != 0) {
                entityManager.
                        createNativeQuery("update items set id_office = " + officeQuery.getSingleResult().getId() + " where id = " + fromQuery.getSingleResult().getId()).
                        executeUpdate();
            } else {
                Office office = new Office();
                office.setOfficeNumber(toOffice);
                entityManager.merge(office);

                TypedQuery<Office> officeQueryCreate = entityManager.createQuery("from Office o where o.officeNumber = :officeNumber", Office.class);
                officeQueryCreate.setParameter("officeNumber", toOffice);

                entityManager.
                        createNativeQuery("update items set id_office = " + officeQueryCreate.getSingleResult().getId() + " where id = " + fromQuery.getSingleResult().getId()).
                        executeUpdate();
            }
        } else if (leftCount > 0) {

            if (toQuery.getResultList().size() != 0) {
                entityManager.createNativeQuery("update items set count_items = " + (toQuery.getSingleResult().getCountItems() + countItems) + " where id = " + toQuery.getSingleResult().getId()).
                        executeUpdate();
            } else if (officeQuery.getResultList().size() != 0) {
                Office office = new Office();
                office.setId(officeQuery.getSingleResult().getId());
                office.setOfficeNumber(toOffice);

                Item item = new Item();
                item.setDescription(fromQuery.getSingleResult().getDescription());
                item.setNumber(fromQuery.getSingleResult().getNumber());
                item.setCountItems(countItems);
                item.setOffice(office);

                entityManager.merge(item);
            } else {
                Office office = new Office();
                office.setOfficeNumber(toOffice);

                Item item = new Item();
                item.setDescription(fromQuery.getSingleResult().getDescription());
                item.setNumber(fromQuery.getSingleResult().getNumber());
                item.setCountItems(countItems);
                item.setOffice(office);

                entityManager.merge(item);
            }

            entityManager.createNativeQuery("update items set count_items = " + (fromQuery.getSingleResult().getCountItems() - countItems) + " where id = " + fromQuery.getSingleResult().getId()).
                    executeUpdate();

        } else {
            System.err.println("ERROR!!!");
        }
    }

    public void removeItemFromOffice(int fromOffice, int invNumber, int countItems) {
        TypedQuery<Item> itemFromOffice = entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i left join i.office io where i.number = :invNumber and io.officeNumber = :officeNumber", Item.class).
                setParameter("invNumber", invNumber).
                setParameter("officeNumber", fromOffice);
        if (itemFromOffice.getResultList().size() != 0) {
            if((itemFromOffice.getSingleResult().getCountItems() - countItems) == 0) {
                entityManager.createNativeQuery("delete from items where id = " + itemFromOffice.getSingleResult().getId()).executeUpdate();
            } else if((itemFromOffice.getSingleResult().getCountItems() - countItems) > 0){
                entityManager.createNativeQuery("update items set count_items = " + (itemFromOffice.getSingleResult().getCountItems() - countItems) + " where id = " + itemFromOffice.getSingleResult().getId()).executeUpdate();
            }
        } else {
            System.err.println("ERROR!!!");
        }
    }

    public List<Item> getItemsByOffice(int numberOffice) {
        TypedQuery<Item> itemFromOffice = entityManager.
                createQuery("select new Item(i.number, i.description, i.countItems) from Item i left join i.office io where io.officeNumber = :officeNumber", Item.class).
                setParameter("officeNumber", numberOffice);
        return itemFromOffice.getResultList();
    }



}
