/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Entities;

/**
 * This is Item entity, that contains information about item
 *
 * @author s028945
 */
public class Item {

    private int itemID;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;

    /**
     * This is default non-parameterized constructor of Item entity
     */
    public Item() {
    }

    /**
     * This is parameterized constructor of Item entity
     *
     * @param itemID contains item ID
     * @param itemName contains item name
     * @param itemPrice contains item price
     * @param itemQuantity contains item quantity
     */
    public Item(int itemID, String itemName, double itemPrice, int itemQuantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    /**
     * This function is used to get item's ID
     *
     * @return item's id
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * This function is used to set item's ID
     *
     * @param itemID contains item's ID
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * This function is used to get item's name
     *
     * @return name of item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This function is used to set item's name
     *
     * @param itemName contains item's name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * This function is used to get item's price
     *
     * @return item's price
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * This function is used to set item's price
     *
     * @param itemPrice contains item's price
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * This function is used to get quantity of item
     *
     * @return
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * This function is used to set quantity of item
     *
     * @param itemQuantity contains quantity of item
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * This is toString method for Item
     *
     * @return item information
     */
    @Override
    public String toString() {
        return "Item{" + "itemID=" + itemID + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemQuantity=" + itemQuantity + '}';
    }

}
