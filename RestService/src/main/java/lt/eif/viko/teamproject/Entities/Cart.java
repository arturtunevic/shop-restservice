/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is cart entity that contains information about items bought
 *
 * @author s028945
 */
@XmlRootElement(name = "Cart")
public class Cart {

    private int cartID;
    private int quantity;
    private int itemID;
    private int saleID;
    private Link link;

    /**
     * This is default constructor
     */
    public Cart() {

    }

    /**
     * Method to get cart ID
     *
     * @return id of cart
     */
    public int getCartID() {
        return cartID;
    }

    /**
     * Method to set cart ID
     *
     * @param cartID contains id of cart
     */
    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    /**
     * Method to get quantity of item
     *
     * @return quantity of item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method to set quantity of item
     *
     * @param quantity specifies quantity of item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method that returns item ID
     *
     * @return id of item
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Method to set item ID
     *
     * @param itemID id used to identify item
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * Method to get sale ID
     *
     * @return id of cart
     */
    public int getSaleID() {
        return saleID;
    }

    /**
     * Method to set sale ID
     *
     * @param saleID contains id of sale
     */
    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    /**
     * Get link uri
     *
     * @return uri of customer list
     */
    @JsonProperty("link")
    public URI getLink() {
        return link.getUri();
    }

    /**
     * Method to set link
     *
     */
    public void setLink(Link link) {
        this.link = link;
    }
}
