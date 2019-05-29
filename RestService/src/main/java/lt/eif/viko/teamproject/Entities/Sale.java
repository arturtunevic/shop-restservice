/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Entities;

import java.net.URI;
import java.util.List;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This is sales Entity that contains information about purchase
 *
 * @author s028945
 */
@XmlRootElement(name = "Sale")
public class Sale {

    private int saleID;
    private Customer customer;
    private List<Item> items;
    private int cartID;
    private String saleDate;
    private Link link;

    /**
     * This is default non-parameterized constructor for Sales entity
     *
     */
    public Sale() {
    }

    /**
     * This is parameterized constructor for Sales entity
     *
     * @param saleID
     * @param customer
     * @param items
     * @param saleDate
     */
    public Sale(int saleID, Customer customer, List<Item> items, String saleDate, int cartID) {
        this.saleID = saleID;
        this.customer = customer;
        this.items = items;
        this.cartID = cartID;
        this.saleDate = saleDate;
    }

    /**
     * This method is used to retrieve sale ID
     *
     * @return sale ID
     */
    public int getSaleID() {
        return saleID;
    }

    /**
     * This method is used to set cart ID
     *
     * @param id id of cart
     */
    public void setCartID(int id) {
        cartID = id;
    }

    /**
     * This method returns id of cart
     *
     * @return id of cart
     */
    public int getCartID() {
        return cartID;
    }

    /**
     * This method returns id of customer
     *
     * @return id of customer
     */
    public int getCustomerID() {
        return customer.getCustomerID();
    }

    /**
     * This method is used to set ID of sale
     *
     * @param saleID ID of sale
     */
    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    /**
     * This method is used to retrieve customer object
     *
     * @return customer object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * This method is used to set Customer
     *
     * @param customer Object that contains information about customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * This method is used to retrieve list of items
     *
     * @return list of items
     */
    @XmlElementWrapper(name = "Items")
    @XmlElement(name = "Item")
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * This method set's item list to given one
     *
     * @param items list of items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * This method retrieves sale date
     *
     * @return date of sale
     */
    public String getSaleDate() {
        return saleDate;
    }

    /**
     * This method set's date of sale;
     *
     * @param saleDate date of sale
     */
    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * This is toSring method
     *
     * @return Sale entity information
     */
    @Override
    public String toString() {
        return "Sales{" + "saleID= " + saleID + ", customer= " + customer + ", items= " + items + ", saleDate= " + saleDate + '}';
    }

    /**
     * Get link uri
     *
     * @return uri of customer list
     */
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    @XmlElement
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
