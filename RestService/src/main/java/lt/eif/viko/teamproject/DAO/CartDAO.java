/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lt.eif.viko.teamproject.Entities.Cart;
import lt.eif.viko.teamproject.Entities.Item;

/**
 * This class is used to manage Cart data in database
 *
 * @author s028945
 */
public class CartDAO implements DAO<Cart> {

    private final Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    ItemDAO iDao = new ItemDAO();
    List<Cart> cItems = new ArrayList<>();
    List<Item> items = new ArrayList<>();

    /**
     * Default constructor for Sales DAO
     */
    public CartDAO() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        connection = database.getConnection();
    }

    /**
     * Method used to get all items
     *
     * @return list of all items
     */
    public List<Cart> load() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM cart");
            Boolean next = resultSet.first();
            while (next == true) {
                Cart cart = new Cart();
                cart.setCartID(resultSet.getInt("Cart_ID"));
                cart.setQuantity(resultSet.getInt("Quantity"));
                cart.setItemID(resultSet.getInt("item_ID"));
                cItems.add(cart);
                next = resultSet.next();
            }
            return cItems;
        } catch (SQLException ex) {

        }
        return null;
    }

    /**
     * Method used to retrieve specific Cart by ID
     *
     * @param id cart id
     * @return cart object
     */
    @Override
    public Cart get(Object id) {
        int idInt = (int) id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM cart WHERE Cart_ID = " + idInt);
            resultSet.first();
            Cart cart = new Cart();
            cart.setCartID(resultSet.getInt("Cart_ID"));
            cart.setQuantity(resultSet.getInt("Quantity"));
            cart.setItemID(resultSet.getInt("item_ID"));
            return cart;

        } catch (SQLException ex) {
            //    Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Item> getItems(Object id) {
        int idInt = (int) id;
        Item item;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Item_ID FROM cart WHERE Cart_ID = " + idInt);
            Boolean next = resultSet.first();
            while (next == true) {
                item=iDao.get(resultSet.getInt("Item_ID"));
                items.add(item);
                next = resultSet.next();
            }
            return items;

        } catch (SQLException ex) {
            //    Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * insert item object into DB
     *
     * @param object
     */
    @Override
    public void insert(Cart object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO cart VALUES (" + object.getCartID() + ", '" + object.getQuantity() + "', '" + object.getItemID() + '"');
        } catch (SQLException ex) {

        }
    }

    /**
     * update cart object in DB
     *
     * @param object contains updated values
     */
    @Override
    public void update(Cart object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE SET Quantity =" + object.getQuantity() + " WHERE Cart_ID =" + object.getCartID());
        } catch (SQLException ex) {

        }
    }

    /**
     * delete item object from DB
     *
     * @param object contains object to be deleted
     */
    @Override
    public void delete(Cart object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM cart WHERE Cart_ID = " + object.getCartID());
        } catch (SQLException ex) {
            // Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
