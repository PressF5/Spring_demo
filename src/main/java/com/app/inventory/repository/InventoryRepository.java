package com.app.inventory.repository;

import com.app.inventory.entity.Item;
import com.app.inventory.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class InventoryRepository {
    @Autowired
    private EntityManager entityManager;

    public void saveInventory(Item item) {
        int officeNumber = item.getOffice().getOfficeNumber();
        TypedQuery<Item> query1 = entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i left join i.office io where i.number = :invNumber and io.officeNumber = :officeNumber", Item.class).
                setParameter("invNumber", item.getNumber()).
                setParameter("officeNumber", item.getOffice().getOfficeNumber());
        if(query1.getResultList().size()!=0){
            Item item1 = query1.getSingleResult();
            item1.setCountItems(item1.getCountItems() + item.getCountItems());
            entityManager.merge(item1);
        } else {
            TypedQuery<Office> query = entityManager.createQuery("from Office o where o.officeNumber = :officeNumber", Office.class);
            query.setParameter("officeNumber", officeNumber);
            if(query.getResultList().size() != 0)
                item.getOffice().setId(query.getSingleResult().getId());
            entityManager.merge(item);}
    }

    public void moveInventory(int invNumber, int fromOffice, int toOffice, int countItems) {
        TypedQuery<Item> query = entityManager.
                createQuery("select new Item(i.id, i.number, i.description, i.countItems, io.id, io.officeNumber) from Item i left join i.office io where i.number = :invNumber and io.officeNumber = :officeNumber", Item.class).
                setParameter("invNumber", invNumber).
                setParameter("officeNumber", fromOffice);
        if(query.getResultList().size() != 0) {
            int leftCountItem = query.getSingleResult().getCountItems() - countItems;
            if(leftCountItem > 0) {
                Item item = new Item();
                item.setNumber(query.getSingleResult().getNumber());
                item.setDescription(query.getSingleResult().getDescription());
                item.setCountItems(countItems);
                Office office = new Office();
                //toOffice
                TypedQuery<Office> officeHQL = entityManager.createQuery("from Office o where o.officeNumber = :officeNumber", Office.class);
                officeHQL.setParameter("officeNumber", toOffice);
                if(officeHQL.getResultList().size() != 0) {
                    office.setId(officeHQL.getSingleResult().getId());
                }
                office.setOfficeNumber(toOffice);
                item.setOffice(office);
                entityManager.merge(item);

                entityManager.
                        createQuery("UPDATE Item set count_items = :count_items WHERE id = :id").
                        setParameter("count_items", leftCountItem).
                        setParameter("id", query.getSingleResult().getId()).
                        executeUpdate();
            }
            else if(leftCountItem == 0) {
//                Item item = new Item();
//                item.setNumber(query.getSingleResult().getNumber());
//                item.setDescription(query.getSingleResult().getDescription());
//                item.setCountItems(countItems);
//                Office office = new Office();
//                //toOffice
                TypedQuery<Office> officeHQL = entityManager.createQuery("from Office o where o.officeNumber = :officeNumber", Office.class);
                officeHQL.setParameter("officeNumber", toOffice);
//                if(officeHQL.getResultList().size() != 0) {
//                    office.setId(officeHQL.getSingleResult().getId());
//                }
//                office.setOfficeNumber(toOffice);
//                item.setOffice(office);
//                entityManager.createQuery("delete from Item where id = :iid").setParameter("iid", query.getSingleResult().getId());
//                System.out.println(query.getSingleResult().getId());
//                entityManager.merge(item);
                  entityManager.createNativeQuery("update items set id_office = " + officeHQL.getSingleResult().getId() + "where id = " + query.getSingleResult().getId());
//                entityManager.
//                        createQuery("UPDATE Item set Office.id = :idOffice WHERE id = :id").
//                        setParameter("idOffice", officeHQL.getSingleResult().getId()).
//                        setParameter("id", query.getSingleResult().getId()).
//                        executeUpdate();







                //int id = query.getSingleResult().getId();







                // Обновить внешний ключ(т.е. офис) у инвентаря если переносят весь инвентарь из кабинета в (существующий)кабинет
                // И создать новый офис на тот случай если переносится все в новый офис но его нет в БД
            }
        }




    }
}
