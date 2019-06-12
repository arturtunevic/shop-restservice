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
 *
 */
public class CartResourceTest {

    @BeforeClass
    public static void setUpClass() throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("http://localhost:8080/RestService/rest/sales/1/cart");
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
     * Test of loadCart method, of class CartResource.
     */
    @Test
    public void testLoadCart() {
        when().
                get("/sales/1/cart/").
                then().
                statusCode(200);
    }

    /**
     * Test of getCart method, of class CartResource.
     */
    @Test
    public void testGetCart() {
        when().
                get("/sales/1/cart/{id}", 2).
                then().
                statusCode(200);
    }

    /**
     * Test of createCart method, of class CartResource.
     */
    @Test
    public void testCreateCart() {
        given()
                .contentType("application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Cart>\n"
                        + "    <Cart_ID>0</Cart_ID>\n"
                        + "    <Quantity>45</Quantity>\n"
                        + "    <Item_ID>1</Item_ID>\n"
                        + "    <Sale_ID>1</Sale_ID>\n"
                        + "</Cart>").
                when()
                .post("/sales/1/cart").
                then()
                .statusCode(201);
    }

    /**
     * Test of deleteCart method, of class CartResource.
     */
    @Test
    public void testDeleteCart() {
        when().
                delete("/sales/cart/10").
                then().
                statusCode(404);
    }

    /**
     * Test of updateCart method, of class CartResource.
     */
    @Test
    public void testUpdateCart() {
        given()
                .contentType("application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Cart>\n"
                        + "    <Cart_ID>0</Cart_ID>\n"
                        + "    <Quantity>1500</Quantity>\n"
                        + "    <Item_ID>1</Item_ID>\n"
                        + "    <Sale_ID>1</Sale_ID>\n"
                        + "</Cart>").
                when()
                .put("/sales/1/cart/1").
                then()
                .statusCode(200);
    }

}
