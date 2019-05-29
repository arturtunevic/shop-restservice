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
import lt.eif.viko.teamproject.Entities.Item;

/**
 * This class is used to manage Item's data in Database
 *
 * @author Tomas
 */
public class ItemDAO implements DAO<Item> {

    private final Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    List<Item> items = new ArrayList<>();

    /**
     * DAO item object constructor
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public ItemDAO() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        connection = database.getConnection();
    }

    /**
     * Method used to get all items
     * @return list of all items
     */
    @Override
    public List<Item> load() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM item");
            Boolean next = resultSet.first();
            while (next == true) {
                Item item = new Item();
                item.setItemID(resultSet.getInt("item_ID"));
                item.setItemName(resultSet.getString("item_name"));
                item.setItemPrice(resultSet.getDouble("item_price"));
                item.setItemQuantity(resultSet.getInt("item_quantity"));
                items.add(item);
                next = resultSet.next();
            }
            return items;
        } catch (SQLException ex) {

        }
        return null;
    }

    /**
     * Method used to retrieve specific item by ID
     * @param id item id
     * @return item
     */
    @Override
    public Item get(Object id) {
        int idInt = (int) id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM item WHERE Item_ID = " + idInt);
            resultSet.first();
            Item item = new Item();
            item.setItemID(resultSet.getInt("Item_ID"));
            item.setItemName(resultSet.getString("Item_name"));
            item.setItemPrice(resultSet.getDouble("item_price"));
            item.setItemQuantity(resultSet.getInt("Item_quantity"));
            return item;

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
    public void insert(Item object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Item VALUES (" + object.getItemID()+ ", '" + object.getItemName()+ "', '" + object.getItemPrice() + "', '" + object.getItemQuantity() + '"');
        } catch (SQLException ex) {

        }
    }

    /**
     * update item object into DB
     *
     * @param object
     */
    @Override
    public void update(Item object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE SET item_price =" + object.getItemPrice()+ ", Item_quantity=" + object.getItemQuantity() + " WHERE Item_ID =" + object.getItemID());
        } catch (SQLException ex) {

        }
    }

    /**
     * delete item object from DB
     *
     * @param object
     */
    @Override
    public void delete(Item object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM item WHERE Item_ID = " + object.getItemID());
        } catch (SQLException ex) {
            // Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
