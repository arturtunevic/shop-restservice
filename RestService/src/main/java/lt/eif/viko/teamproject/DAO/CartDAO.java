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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lt.eif.viko.teamproject.Entities.Cart;

/**
 * This class is used to manage Cart data in database
 *
 * @author s028945
 * @author Tomas
 * @author Gintas
 */
public class CartDAO implements DAO<Cart> {

    private final Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    List<Cart> cItems = new ArrayList<>();

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
    @Override
    public List<Cart> load() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM cart");
            Boolean next = resultSet.first();
            while (next == true) {
                Cart cart = new Cart();
                cart.setCartID(resultSet.getInt("Cart_ID"));
                cart.setQuantity(resultSet.getInt("Quantity"));
                cart.setItemID(resultSet.getInt("Item_ID"));
                cart.setSaleID(resultSet.getInt("Sale_ID"));
                cItems.add(cart);
                next = resultSet.next();
            }
            return cItems;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            cart.setItemID(resultSet.getInt("Item_ID"));
            cart.setSaleID(resultSet.getInt("Sale_ID"));

            return cart;

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            statement.executeUpdate("INSERT INTO cart VALUES (" + object.getCartID() + ", '" + object.getQuantity() + "', " + object.getItemID() + ", '" + object.getSaleID() + '"');
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
