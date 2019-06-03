/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import lt.eif.viko.teamproject.DAO.CartDAO;
import lt.eif.viko.teamproject.Entities.Cart;

/**
 * Class to represent an object of Cart entity as a resource for WS
 *
 * @author s208945
 */
@Path("/cart")
@Produces("application/xml")
public class CartResource {

    CartDAO dao;
    int saleID;

    /**
     * Default CartResource constructor
     *
     * @throws ClassNotFoundException class not found exceptions
     * @throws SQLException sql exceptions
     */
    public CartResource() throws ClassNotFoundException, SQLException {
        dao = new CartDAO();
    }

    /**
     * Parameterized CartResource constructor
     *
     * @param saleID id of sale
     * @throws java.lang.ClassNotFoundException class not found exception
     * @throws java.sql.SQLException sql exception
     */
    public CartResource(int saleID) throws ClassNotFoundException, SQLException {
        dao = new CartDAO();
        this.saleID = saleID;
    }

    @Context
    UriInfo uriInfo;

    /**
     * Method used to get activities list from database
     *
     * @return an object of class Country
     */
    @GET
    public List<Cart> loadCart() {
        List<Cart> cartList = new ArrayList<>();
        cartList = dao.loadBySale(saleID);

        UriBuilder builder = UriBuilder.fromResource(SalesResource.class)
                .path(SalesResource.class, "getSaleByID");

        for (Cart cart : cartList) {
            Link lnk = Link.fromUri(builder.build(saleID) + "/" + cart.getCartID()).rel("self").build();
            cart.setLink(lnk);
        }

        return cartList;
    }

    /**
     * Method used to get cart by id from database
     *
     * @param id id of cart
     * @return an object of class Cart
     */
    @GET
    @Path("/{cartID}")
    public Cart getCart(@PathParam("cartID") int id) {
        Cart cart = (Cart) dao.get(id);
        UriBuilder builder = UriBuilder.fromResource(SalesResource.class)
                .path(SalesResource.class, "getSaleByID");
        Link lnk = Link.fromUri(builder.build(saleID) + "/cart/" + cart.getCartID()).rel("self").build();
        cart.setLink(lnk);
        return cart;
    }

    /**
     * Method used to create cart and insert it into database
     *
     * @return a response that cart was inserted
     */
    @POST
    @Consumes("application/xml")
    public Response createActivity(Cart cart) {
        dao.insert(cart);
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).build();
    }

    /**
     * Method used to delete cart from database
     *
     * @param id id of cart
     * @return a response that cart was deleted
     */
    @DELETE
    @Path("/{cartID}")
    public Response deleteActivity(@PathParam("cartID") int id) {
        Cart cart = (Cart) dao.get(id);
        dao.delete(cart);

        return Response.status(javax.ws.rs.core.Response.Status.OK).build();
    }

    /**
     * Method used to update activity from database
     *
     * @return a response that activity was updated
     */
    @PUT
    @Path("/{cartID}")
    @Consumes("application/xml")
    public Response updateActivity(Cart cart) {
        dao.update(cart);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }

}
