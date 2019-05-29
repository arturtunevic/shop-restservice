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
 * This is SalesList Entity, that contains list of Sale
 *
 * @author s028945
 */
@XmlRootElement(name = "Sales")
public class SalesList {

    private List<Sale> sales = null;
    private Link link;

    /**
     * Default constructor for SalesList
     */
    public SalesList() {

    }

    /**
     * This function returns list of all sales
     *
     * @return list of sales
     */
    @XmlElementWrapper(name = "sales")
    @XmlElement(name = "sale")
    public List<Sale> getSales() {
        return sales;
    }

    /**
     * This function is used to set list of sales to given one
     *
     * @param sales list of sales
     */
    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    /**
     * Method for deleting Sale from list
     *
     * @param sale is an object of entity Sale
     */
    public void deleteFromList(Sale sale) {
        sales.remove(sale);
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
