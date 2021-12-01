package com.app.inventory.service;

import com.app.inventory.entity.Item;
import com.app.inventory.entity.Office;
import com.app.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void saveInventory(Item item) {
        int officeNumber = item.getOffice().getOfficeNumber();
        TypedQuery<Item> itemOffice = inventoryRepository.
                getItemByInventoryNumberAndOfficeNumber(item.getNumber(), officeNumber);
        if (item.getId() == 0) {
            if (itemOffice.getResultList().size() != 0) {
                Item item1 = itemOffice.getSingleResult();
                item1.setCountItems(item1.getCountItems() + item.getCountItems());
                inventoryRepository.merge(item1);
            } else {
                TypedQuery<Office> office = inventoryRepository.
                        getOfficeByOfficeNumber(officeNumber);
                if (office.getResultList().size() != 0)
                    item.getOffice().setId(office.getSingleResult().getId());
                inventoryRepository.merge(item);
            }
        } else if (item.getId() > 0)
            inventoryRepository.merge(item);
    }

    @Transactional
    public void moveInventory(int fromOffice, int toOffice, int invNumber, int countItems) {
        TypedQuery<Item> fromQuery = inventoryRepository.getItemByInventoryNumberAndOfficeNumber(invNumber, fromOffice);
        TypedQuery<Item> toQuery = inventoryRepository.getItemByInventoryNumberAndOfficeNumber(invNumber, toOffice);
        TypedQuery<Office> officeQuery = inventoryRepository.getOfficeByOfficeNumber(toOffice);

        int leftCount = fromQuery.getSingleResult().getCountItems() - countItems;

        if (leftCount == 0) {
            if (toQuery.getResultList().size() != 0) {
                inventoryRepository.updateCountItemsByIdItem(toQuery.getSingleResult().getCountItems() + countItems, toQuery.getSingleResult().getId());
                inventoryRepository.deleteItemByIdItem(fromQuery.getSingleResult().getId());
            } else if (officeQuery.getResultList().size() != 0) {
                inventoryRepository.updateForeignKeyInItemByItemId(officeQuery.getSingleResult().getId(), fromQuery.getSingleResult().getId());
            } else {
                Office office = new Office();
                office.setOfficeNumber(toOffice);
                inventoryRepository.merge(office);
                TypedQuery<Office> officeQueryCreate = inventoryRepository.getOfficeByOfficeNumber(toOffice);
                inventoryRepository.updateForeignKeyInItemByItemId(officeQueryCreate.getSingleResult().getId(), fromQuery.getSingleResult().getId());
            }
        } else if (leftCount > 0) {

            if (toQuery.getResultList().size() != 0) {
                inventoryRepository.updateCountItemsByIdItem(toQuery.getSingleResult().getCountItems() + countItems, toQuery.getSingleResult().getId());
            } else if (officeQuery.getResultList().size() != 0) {
                Office office = new Office();
                office.setId(officeQuery.getSingleResult().getId());
                office.setOfficeNumber(toOffice);

                Item item = new Item();
                item.setDescription(fromQuery.getSingleResult().getDescription());
                item.setNumber(fromQuery.getSingleResult().getNumber());
                item.setCountItems(countItems);
                item.setOffice(office);

                inventoryRepository.merge(item);
            } else {
                Office office = new Office();
                office.setOfficeNumber(toOffice);

                Item item = new Item();
                item.setDescription(fromQuery.getSingleResult().getDescription());
                item.setNumber(fromQuery.getSingleResult().getNumber());
                item.setCountItems(countItems);
                item.setOffice(office);

                inventoryRepository.merge(item);
            }
            inventoryRepository.updateCountItemsByIdItem(fromQuery.getSingleResult().getCountItems() - countItems, fromQuery.getSingleResult().getId());
        } else {
            System.err.println("ERROR!!!");
        }
    }

    @Transactional
    public void removeItemsFromOffice(int fromOffice, int invNumber, int countItems) {
        TypedQuery<Item> itemFromOffice = inventoryRepository.getItemByInventoryNumberAndOfficeNumber(invNumber, fromOffice);
        if (itemFromOffice.getResultList().size() != 0) {
            if ((itemFromOffice.getSingleResult().getCountItems() - countItems) == 0) {
                inventoryRepository.deleteItemByIdItem(itemFromOffice.getSingleResult().getId());
            } else if ((itemFromOffice.getSingleResult().getCountItems() - countItems) > 0) {
                inventoryRepository.updateCountItemsByIdItem(itemFromOffice.getSingleResult().getCountItems() - countItems, itemFromOffice.getSingleResult().getId());
            }
        } else {
            System.err.println("ERROR!!!");
        }
    }

    @Transactional
    public List<Item> getItemsByInvNumberOrOfficeNumber(int invNumberOrNumberOffice, String type) {
        if ("office".equals(type))
            return inventoryRepository.getItemsByOffice(invNumberOrNumberOffice);
        else
            return inventoryRepository.getItemsByInvNumber(invNumberOrNumberOffice);
    }

    @Transactional
    public Item getItemById(int itemId) {
        return inventoryRepository.getItemById(itemId);
    }

    @Transactional
    public void deleteItemById(int itemId) {
        inventoryRepository.deleteItemById(itemId);
    }
}
