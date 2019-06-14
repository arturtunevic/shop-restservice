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
import lt.eif.viko.teamproject.DAO.SalesDAO;
import lt.eif.viko.teamproject.Entities.Sale;
import lt.eif.viko.teamproject.Entities.SalesList;
import org.apache.log4j.Logger;

/**
 * Class to represent an object of class Sales as a resource for rest service
 *
 * @author s028945
 */
@Path("/sales")
@Produces("application/xml")
public class SalesResource {

    SalesDAO dao;
    Logger log = Logger.getLogger(SalesResource.class);

    /**
     * Default constructor for sales resource
     */
    public SalesResource() throws SQLException, ClassNotFoundException {
        dao = new SalesDAO();

    }

    @Context
    UriInfo uriInfo;

    /**
     * This method is used to retrieve list of all sales
     *
     * @return list of all sales
     */
    @GET
    public Response getSales() throws SQLException, ClassNotFoundException {
        log.info("GET REQUEST: " + uriInfo.getRequestUri());
        dao = new SalesDAO();
        SalesList sales = new SalesList();
        sales.setSales(dao.load());
        if (sales.getSales() == null) {
            log.info("RESPONSE: GET /sales FAIL");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Link link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
            sales.setLink(link);
            for (Sale sale : sales.getSales()) {
                Link lnk = Link.fromUri(uriInfo.getPath() + "/" + sale.getSaleID()).rel("self").build();
                sale.setLink(lnk);
            }
            log.info("RESPONSE: GET /sales SUCCESS");
            return Response.ok(sales).build();
        }
    }

    /**
     * Method used to get Sale from Database by using sale ID
     *
     * @param id ID of sale
     * @return a response with corresponding Sale object
     */
    @GET
    @Path("/{saleID}")
    public Response getSaleByID(@PathParam("saleID") int id) {
        log.info("GET REQUEST: " + uriInfo.getRequestUri());
        Sale sale = (Sale) dao.get(id);
        if (sale == null) {
            log.info("RESPONSE: GET /{saleID} FAIL: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            UriBuilder builder = UriBuilder.fromResource(SalesResource.class)
                    .path(SalesResource.class, "getSaleByID");
            Link link = Link.fromUri(builder.build(id)).rel("self").build();
            sale.setLink(link);
            log.info("RESPONSE: GET /{saleID} SUCCESS:" + id);
            return Response.ok(sale).build();
        }
    }

    /**
     * Method used to create new Sale and insert it into database
     *
     * @param sale Entity that we're inserting
     * @return a response that Sale was inserted
     */
    @POST
    @Consumes("application/xml")
    public Response createSale(Sale sale) {
        log.info("POST REQUEST: " + uriInfo.getRequestUri());
        dao.insert(sale);
        Link lnk = Link.fromUri(uriInfo.getPath() + "/" + sale.getSaleID()).rel("self").build();
        log.info("RESPONSE: POST /sales SUCCESS");
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).location(lnk.getUri()).build();
    }

    /**
     * Method that deletes Sale by sale ID
     *
     * @param id Used to identify sale
     * @return response of method
     */
    @DELETE
    @Path("/{saleID}")
    public Response deleteSale(@PathParam("saleID") int id) {
        log.info("DELETE REQUEST: " + uriInfo.getRequestUri());
        Sale sale = (Sale) dao.get(id);
        if (sale == null) {
            log.info("RESPONSE: DELETE /{itemID} FAIL: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            dao.delete(sale);
            log.info("RESPONSE: DELETE /{saleID} SUCCESS: " + id);
            return Response.status(javax.ws.rs.core.Response.Status.OK).build();
        }
    }

    /**
     * Method used to update Sale from database
     *
     * @param sale updated object
     * @return a response that Sale was updated
     */
    @PUT
    @Path("/{saleID}")
    @Consumes("application/xml")
    public Response updateSale(Sale sale) {
        log.info("PUT REQUEST: " + uriInfo.getRequestUri());
        if (sale == null) {
            log.info("RESPONSE: PUT /{saleID} FAIL");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            dao.update(sale);
            log.info("RESPONSE: PUT /{saleID} SUCCESS");
            return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
        }
    }

    /**
     * Method used for activities path
     *
     * @return TopThingsToDoResource that represents new path
     */
    @Path("/{saleID}/cart")
    public CartResource getCart(@PathParam("saleID") int id) throws SQLException, ClassNotFoundException {
        CartResource cartResource = new CartResource(id);
        return cartResource;
    }

}
