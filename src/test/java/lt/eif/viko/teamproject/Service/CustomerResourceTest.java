/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Service;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javax.ws.rs.client.ClientBuilder;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assume;

/**
 *
 * @author s028945
 * @author Tomas
 * @author Gintas
 */
public class CustomerResourceTest {

    @BeforeClass
    public static void setUpClass() throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("http://localhost:8080/RestService/rest/customers");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();
        if (code == 404) {
            Assume.assumeTrue(false);
        }
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost/RestService/rest";
        RestAssured.port = 8080;
    }

    /**
     * Test of getCustomers method, of class CustomerResource.
     */
    @Test
    public void testGetCustomers() {
        when().
                get("/customers").
                then().
                statusCode(200);
    }

    /**
     * Test of getCustomerByID method, of class CustomerResource.
     */
    @Test
    public void testGetCustomerByID() {
        when().
                get("/customers/{id}", 2).
                then().
                statusCode(200);
    }

    /**
     * Test of createCustomer method, of class CustomerResource.
     */
    @Test
    public void testCreateCustomer() {
        given()
                .contentType("application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Customer>\n"
                        + "    <ID>0</ID>\n"
                        + "    <Name>Test</Name>\n"
                        + "    <Surname>Tested</Surname>\n"
                        + "</Customer>").
                when()
                .post("/customers").
                then()
                .statusCode(201);
    }

    /**
     * Test of deleteCustomer method, of class CustomerResource.
     */
    @Test
    public void testDeleteCustomer() {
        when().
                delete("/customers/10").
                then().
                statusCode(500);
    }

    /**
     * Test of updateCustomer method, of class CustomerResource.
     */
    @Test
    public void testUpdateCustomer() {
        given()
                .contentType("application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Customer>\n"
                        + "    <ID>1</ID>\n"
                        + "    <Name>Test</Name>\n"
                        + "    <Surname>Testedaas</Surname>\n"
                        + "</Customer>").
                when()
                .put("/customers/1").
                then()
                .statusCode(200);
    }

}
