/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Service;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.teamproject.Entities.Item;
import lt.eif.viko.teamproject.Entities.ItemList;

/**
 * Class to represent an object of class Items as a resource for rest service
 *
 * @author s028945
 */
@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemsResource {

    /**
     * Default constructor for item resource
     */
    public ItemsResource() {

    }

    @Context
    UriInfo uriInfo;

    /**
     * This method is used to retrieve list of all items
     *
     * @return list of all items
     */
    @GET
    public ItemList getItems() {

        ItemList items = dao.getItems();

        Link link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
        items.setLink(link);

        for (Item item : items.getItems()) {
            Link lnk = Link.fromUri(uriInfo.getPath() + "/" + item.getItemID()).rel("self").build();
            item.setLink(lnk);
        }
        return items;
    }

    /**
     * Method used to get Item from Database by using item ID
     *
     * @param id ID of item
     * @return a response with corresponding Item object
     */
    @GET
    @Path("/{itemID}")
    public Item getItemByID(@PathParam("itemID") int id) {
        Item item = (Item) dao.get(id);
        UriBuilder builder = UriBuilder.fromResource(ItemsResource.class)
                .path(ItemsResource.class, "getItemByID");
        Link link = Link.fromUri(builder.build(id)).rel("self").build();
        item.setLink(link);
        return item;
    }

    /**
     * Method used to create new Item and insert it into database
     *
     * @return a response that Item was inserted
     */
    @POST
    @Consumes("application/json")
    public Response createItem(Item item) {
        dao.createItem(item);
        Link lnk = Link.fromUri(uriInfo.getPath() + "/" + item.getItemID()).rel("self").build();
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
        Item item = (Item) dao.get(id);
        dao.delete(item);

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
    @Consumes("application/json")
    public Response updateCountry(Item item) {
        dao.update(item);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }

}
