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
public class ItemsResourceTest {

    @BeforeClass
    public static void setUpClass() throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("http://localhost:8080/RestService/rest/items");
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
     * Test of getItems method, of class ItemsResource.
     */
    @Test
    public void testGetItems() {
        when().
                get("/items").
                then().
                statusCode(200);
    }

    /**
     * Test of getItemByID method, of class ItemsResource.
     */
    @Test
    public void testGetItemByID() {
        when().
                get("/items/{id}", 2).
                then().
                statusCode(200);
    }

    /**
     * Test of createItem method, of class ItemsResource.
     */
    @Test
    public void testCreateItem() {
        given()
                .contentType("application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Item>\n"
                        + "    <Item_ID>1</Item_ID>\n"
                        + "    <Item_name>Test</Item_name>\n"
                        + "    <item_price>52</item_price>\n"
                        + "    <Item_quantity>100</Item_quantity>\n"
                        + "</Item>").
                when()
                .post("/items").
                then()
                .statusCode(201);
    }

    /**
     * Test of deleteItem method, of class ItemsResource.
     */
    @Test
    public void testDeleteItem() {
        when().
                delete("/items/8").
                then().
                statusCode(500);
    }

    /**
     * Test of updateItem method, of class ItemsResource.
     */
    @Test
    public void testUpdateItem() {
        given()
                .contentType("application/xml")
                .body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Item>\n"
                        + "    <Item_ID>7</Item_ID>\n"
                        + "    <Item_name>Tested</Item_name>\n"
                        + "    <item_price>12</item_price>\n"
                        + "    <Item_quantity>99</Item_quantity>\n"
                        + "</Item>").
                when()
                .put("/items/7").
                then()
                .statusCode(200);
    }

}
