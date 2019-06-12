/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Service;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.teamproject.DAO.ItemDAO;
import lt.eif.viko.teamproject.Entities.Item;
import lt.eif.viko.teamproject.Entities.ItemList;
import org.apache.log4j.Logger;

/**
 * Class to represent an object of class Items as a resource for rest service
 *
 * @author Tomas
 */
@Path("/items")
@Produces("application/xml")
public class ItemsResource {

    ItemDAO dao;
    Logger log = Logger.getLogger(ItemsResource.class);

    /**
     * Default constructor for item resource
     */
    public ItemsResource() throws SQLException, ClassNotFoundException {
        dao = new ItemDAO();

    }

    @Context
    UriInfo uriInfo;

    /**
     * This method is used to retrieve list of all items
     *
     * @return list of all items
     */
    @GET
    public Response getItems() throws SQLException, ClassNotFoundException {
        log.info("GET REQUEST: " + uriInfo.getRequestUri());
        dao = new ItemDAO();
        ItemList items = new ItemList();
        items.setItems(dao.load());
        if (items.getItems() == null) {
            log.info("RESPONSE: GET /items FAIL");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Link link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
            items.setLink(link);

            for (Item item : items.getItems()) {
                Link lnk = Link.fromUri(uriInfo.getPath() + "/" + item.getItemID()).rel("self").build();
                item.setLink(lnk);
            }
            log.info("RESPONSE: GET /items SUCCESS ");
            return Response.ok(items).build();
        }
    }

    /**
     * Method used to get Item from Database by using item ID
     *
     * @param id ID of item
     * @return a response with corresponding Item object
     */
    @GET
    @Path("/{itemID}")
    public Response getItemByID(@PathParam("itemID") int id) {
        log.info("GET REQUEST: " + uriInfo.getRequestUri());
        Item item = (Item) dao.get(id);
        if (item == null) {
            log.info("RESPONSE: GET /{itemID} FAIL: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            UriBuilder builder = UriBuilder.fromResource(ItemsResource.class)
                    .path(ItemsResource.class, "getItemByID");
            Link link = Link.fromUri(builder.build(id)).rel("self").build();
            item.setLink(link);
            log.info("RESPONSE: GET /{itemID} SUCCESS: " + id);
            return Response.ok(item).build();
        }
    }

    /**
     * Method used to create new Item and insert it into database
     *
     * @return a response that Item was inserted
     */
    @POST
    @Consumes("application/xml")
    public Response createItem(Item item) {
        log.info("POST REQUEST: " + uriInfo.getRequestUri());
        dao.insert(item);
        Link lnk = Link.fromUri(uriInfo.getPath() + "/" + item.getItemID()).rel("self").build();
        log.info("RESPONSE: POST SUCCESS: " + item.getItemName());
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).location(lnk.getUri()).build();
    }

    /**
     * Method that deletes Item by item ID
     *
     * @param id Used to identify item
     * @return response of method
     */
    @DELETE
    @Path("/{itemID}")
    public Response deleteItem(@PathParam("itemID") int id) {
        log.info("DELETE REQUEST: " + uriInfo.getRequestUri());
        Item item = (Item) dao.get(id);
        if (item == null) {
            log.info("RESPONSE: DELETE /{itemID} FAIL");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.delete(item);
        log.info("RESPONSE: DELETE SUCCESS: " + id);
        return Response.status(javax.ws.rs.core.Response.Status.OK).build();
    }

    /**
     * Method used to update Item from database
     *
     * @param item updated object
     * @return a response that Item was updated
     */
    @PUT
    @Path("/{itemID}")
    @Consumes("application/xml")
    public Response updateItem(Item item) {
        log.info("PUT REQUEST: " + uriInfo.getRequestUri());
        if (item == null) {
            log.info("RESPONSE: PUT /{itemID} FAIL");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.update(item);
        log.info("RESPONSE: PUT SUCCESS: " + item.getItemName());
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }

}
