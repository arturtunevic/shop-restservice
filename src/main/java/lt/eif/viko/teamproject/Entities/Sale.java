/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Entities;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This is sales Entity that contains information about purchase
 *
 * @author s028945
 */
@XmlRootElement(name = "Sale")
@XmlType(propOrder = {"saleID", "customer", "saleDate", "link"})
public class Sale {

    private int saleID;
    private int customer;
    private String saleDate;
    private Link link;

    /**
     * This is default non-parameterized constructor for Sales entity
     *
     */
    public Sale() {
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
    public int getCustomer() {
        return customer;
    }

    /**
     * This method is used to set Customer
     *
     * @param customer Object that contains information about customer
     */
    public void setCustomer(int customer) {
        this.customer = customer;
    }

    /**
     * This method is used to retrieve list of items
     *
     * @return list of items
     */
    /*
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
 /*
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
        return "Sales{" + "saleID= " + saleID + ", customer= " + customer + ", items= " + "" + ", saleDate= " + saleDate + '}';
    }

    /**
     * Get link uri
     *
     * @return uri of customer list
     */
    @XmlElement(name = "link")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    public Link getLink() {
        return link;
    }

    /**
     * Method to set link
     *
     */
    public void setLink(Link link) {
        this.link = link;
    }

}
