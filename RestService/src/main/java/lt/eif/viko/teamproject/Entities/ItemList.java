/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.util.List;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * This is entity that contains all items in list
 *
 * @author s028945
 */
public class ItemList {

    private List<Item> items = null;
    private Link link;

    /**
     * Default constructor for ItemList
     */
    public ItemList() {

    }

    /**
     * This function returns list of all items
     *
     * @return list of items
     */
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public List<Item> getItems() {
        return items;
    }

    /**
     * This function is used to set list of items to given one
     *
     * @param items list of items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Method for deleting Item from list
     *
     * @param item is an object of entity Item
     */
    public void deleteFromList(Item item) {
        items.remove(item);
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
