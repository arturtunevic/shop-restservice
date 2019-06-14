/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.Service;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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
public class SalesResourceTest {
    

    @BeforeClass
    public static void setUpClass() throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("http://localhost:8080/RestService/rest/sales");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //connection.connect();
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
     * Test of getSales method, of class SalesResource.
     */
    @Test
    public void testGetSales() throws Exception {
        when().
                get("/sales").
                then().
                statusCode(200);
    }

    /**
     * Test of getSaleByID method, of class SalesResource.
     */
    @Test
    public void testGetSaleByID() {
        when().
                get("/sales/{id}", 2).
                then().
                statusCode(200);
    }
    /**
     * Test of createSale method, of class SalesResource.
     */
    @Test
    public void testCreateSale() {
        given()
                .contentType("application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Sale>\n"
                        + "    <Sales_ID>1</Sales_ID>\n"
                        + "    <Sales_Date>Test</Sales_Date>\n"
                        + "    <Customer_ID>52</Customer_ID>\n"
                        + "</Sale>").
                when()
                .post("/sales").
                then()
                .statusCode(201);
    }

    /**
     * Test of deleteSale method, of class SalesResource.
     */
    @Test
    public void testDeleteSale() {
        when().
                delete("/sales/10").
                then().
                statusCode(500);
    }


    /**
     * Test of updateSale method, of class SalesResource.
     */
    @Test
    public void testUpdateSale() {
        given()
                .contentType("application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Sale>\n"
                        + "    <Sales_ID>1</Sales_ID>\n"
                        + "    <Sales_Date>Test</Sales_Date>\n"
                        + "    <Customer_ID>52</Customer_ID>\n"
                        + "</Sale>").
                when()
                .put("/sales/1").
                then()
                .statusCode(200);
    }
    
}
