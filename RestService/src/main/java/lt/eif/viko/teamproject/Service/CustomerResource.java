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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.teamproject.Entities.Customer;
import lt.eif.viko.teamproject.Entities.CustomerList;

/**
 * Class to represent an object of Customers entity as a resource for WS
 *
 * @author s028945
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    /**
     * Default CustomerResource constructor
     */
    public CustomerResource() {

    }

    @Context
    UriInfo uriInfo;

    /**
     * This method is used to retrieve list of all customers
     *
     * @return list of all customers
     */
    @GET
    public CustomerList getCustomers() {

        CustomerList customers = dao.getCustomers();

        Link link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
        customers.setLink(link);

        for (Customer customer : customers.getCustomers()) {
            Link lnk = Link.fromUri(uriInfo.getPath() + "/" + customer.getCustomerID()).rel("self").build();
            customer.setLink(lnk);
        }
        return customers;
    }

    /**
     * Method used to get Customer from Database by using customer ID
     *
     * @param id ID of customer
     * @return a response with corresponding Customer object
     */
    @GET
    @Path("/{customerID}")
    public Customer getCustomerByID(@PathParam("customerID") int id) {
        Customer customer = (Customer) dao.get(id);
        UriBuilder builder = UriBuilder.fromResource(CustomerResource.class)
                .path(CustomerResource.class, "getCustomerByID");
        Link link = Link.fromUri(builder.build(id)).rel("self").build();
        customer.setLink(link);
        return customer;
    }

    /**
     * Method used to create new Customer and insert it into database
     *
     * @return a response that customer was inserted
     */
    @POST
    @Consumes("application/json")
    public Response createCustomer(Customer customer) {
        dao.createCustomer(customer);
        Link lnk = Link.fromUri(uriInfo.getPath() + "/" + customer.getCustomerID()).rel("self").build();
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).location(lnk.getUri()).build();
    }

    /**
     * Method that deletes Customer by customer ID
     *
     * @param id Used to identify customer
     * @return response of method
     */
    @DELETE
    @Path("/{customerID}")
    public Response deleteCustomer(@PathParam("customerID") int id) {
        Customer customer = (Customer) dao.get(id);
        dao.delete(customer);

        return Response.status(javax.ws.rs.core.Response.Status.OK).build();
    }

}
