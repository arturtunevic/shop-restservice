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
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.teamproject.DAO.SalesDAO;
import lt.eif.viko.teamproject.Entities.Sale;
import lt.eif.viko.teamproject.Entities.SalesList;

/**
 * Class to represent an object of class Sales as a resource for rest service
 *
 * @author s028945
 */
@Path("/sales")
@Produces("application/xml")
public class SalesResource {

    SalesDAO dao;
    
    /**
     * Default constructor for sales resource
     */
    public SalesResource() {

    }

    @Context
    UriInfo uriInfo;

    /**
     * This method is used to retrieve list of all sales
     *
     * @return list of all sales
     */
    @GET
    public SalesList getSales() {

        SalesList sales = new SalesList();
        sales.setSales(dao.load());

        Link link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
        sales.setLink(link);

        for (Sale sale : sales.getSales()) {
            Link lnk = Link.fromUri(uriInfo.getPath() + "/" + sale.getSaleID()).rel("self").build();
            sale.setLink(lnk);
        }
        return sales;
    }

    /**
     * Method used to create new Sale and insert it into database
     *
     * @param sale Entity that we're inserting
     * @return a response that Sale was inserted
     */
    @POST
    @Consumes("application/xml")
    public Response createItem(Sale sale) {
        dao.insert(sale);
        Link lnk = Link.fromUri(uriInfo.getPath() + "/" + sale.getSaleID()).rel("self").build();
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
    public Response deleteItem(@PathParam("saleID") int id) {
        Sale sale = (Sale) dao.get(id);
        dao.delete(sale);

        return Response.status(javax.ws.rs.core.Response.Status.OK).build();
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
    public Response updateCountry(Sale sale) {
        dao.update(sale);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }

}
