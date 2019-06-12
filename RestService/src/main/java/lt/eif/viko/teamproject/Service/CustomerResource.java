/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Service;

import java.sql.SQLException;
import java.util.logging.FileHandler;
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
import lt.eif.viko.teamproject.DAO.CustomerDAO;
import lt.eif.viko.teamproject.Entities.Customer;
import lt.eif.viko.teamproject.Entities.CustomerList;
import org.apache.log4j.Logger;

/**
 * Class to represent an object of Customers entity as a resource for WS
 *
 * @author s028945
 */
@Path("/customers")
@Produces("application/xml")
public class CustomerResource {

    Logger log = Logger.getLogger(CustomerResource.class);
    private boolean append = true;
    FileHandler handler;

    CustomerDAO dao;

    /**
     * Default CustomerResource constructor
     */
    public CustomerResource() throws ClassNotFoundException, SQLException{
        dao = new CustomerDAO();
    }

    @Context
    UriInfo uriInfo;

    /**
     * This method is used to retrieve list of all customers
     *
     * @return list of all customers
     * @throws java.sql.SQLException sql exception
     * @throws java.lang.ClassNotFoundException class not found exception
     */
    @GET
    public Response getCustomers() throws SQLException, ClassNotFoundException {

        log.info("GET REQUEST: " + uriInfo.getRequestUri());

        dao = new CustomerDAO();
        CustomerList customers = new CustomerList();
        customers.setCustomers(dao.load());
        if (customers == null) {
            log.info("RESPONSE: GET /customers FAIL");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {

            Link link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
            customers.setLink(link);

            for (Customer customer : customers.getCustomers()) {
                Link lnk = Link.fromUri(uriInfo.getPath() + "/" + customer.getCustomerID()).rel("self").build();
                customer.setLink(lnk);
            }
            log.info("RESPONSE: GET /customers SUCCESS ");
            return Response.ok(customers).build();
        }
    }

    /**
     * Method used to get Customer from Database by using customer ID
     *
     * @param id ID of customer
     * @return a response with corresponding Customer object
     */
    @GET
    @Path("/{customerID}")
    public Response getCustomerByID(@PathParam("customerID") int id) {
        log.info("GET REQUEST: " + uriInfo.getRequestUri());
        Customer customer = (Customer) dao.get(id);
        if (customer == null) {
            log.info("RESPONSE: GET /{customerID} FAIL: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            UriBuilder builder = UriBuilder.fromResource(CustomerResource.class)
                    .path(CustomerResource.class, "getCustomerByID");
            Link link = Link.fromUri(builder.build(id)).rel("self").build();
            customer.setLink(link);
            log.info("RESPONSE: GET /{customerID} SUCCESS: " + id);
            return Response.ok(customer).build();
        }
    }

    /**
     * Method used to create new Customer and insert it into database
     *
     * @param customer object that we are inserting
     * @return a response that customer was inserted
     */
    @POST
    @Consumes("application/xml")
    public Response createCustomer(Customer customer) {
        log.info("POST REQUEST: " + uriInfo.getRequestUri());
        dao.insert(customer);
        Link lnk = Link.fromUri(uriInfo.getPath() + "/" + customer.getCustomerID()).rel("self").build();
        log.info("RESPONSE: POST /customers SUCCESS:" + customer.getCustomerID());
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
        log.info("DELETE REQUEST: " + uriInfo.getRequestUri());
        Customer customer = (Customer) dao.get(id);
        if (customer == null) {
            log.info("RESPONSE: DELETE /{customerID} FAIL: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            dao.delete(customer);
            log.info("RESPONSE: DELETE /{customerID} SUCCESS: " + id);
            return Response.status(javax.ws.rs.core.Response.Status.OK).build();
        }
    }

    /**
     * Method used to update customer from database
     *
     * @return a response that customer was updated
     */
    @PUT
    @Path("/{customerID}")
    @Consumes("application/xml")
    public Response updateCustomer(Customer customer) {
        log.info("PUT REQUEST: " + uriInfo.getRequestUri());
        dao.update(customer);
        if (customer == null) {
            log.info("RESPONSE: PUT /{customerID} FAIL");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            log.info("RESPONSE: PUT /{customerID} SUCCESS: " + customer.getCustomerID());
            return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
        }
    }

}
