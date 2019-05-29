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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lt.eif.viko.teamproject.Entities.Item;
import lt.eif.viko.teamproject.Entities.Sale;

/**
 * This class is used to manage Sales data in Database
 *
 * @author s028945
 */
public class SalesDAO implements DAO<Sale> {

    private final Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    List<Sale> sales = new ArrayList<>();
    CustomerDAO custDao = new CustomerDAO();
    CartDAO crtDao = new CartDAO();

    /**
     * Default constructor for Sales DAO
     */
    public SalesDAO() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        connection = database.getConnection();
    }

    /**
     * This method is used to retrieve sale records from database
     *
     * @return list of sales
     */
    public List<Sale> load() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sales");
            Boolean next = resultSet.first();
            while (next == true) {
                Sale sale = new Sale();
                sale.setSaleID(resultSet.getInt("Sale_ID"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(resultSet.getDate("Sales_Date"));
                sale.setSaleDate(strDate);
                sale.setCustomer(custDao.get(resultSet.getInt("Customer_ID")));
                sale.setItems(crtDao.getItems(resultSet.getInt("Cart_ID")));
                sales.add(sale);
                next = resultSet.next();
            }
            return sales;
        } catch (SQLException ex) {

        }
        return null;
    }

    /**
     * Method used to retrieve specific Sale by ID
     *
     * @param id sale id
     * @return sale object
     */
    @Override
    public Sale get(Object id) {
        int idInt = (int) id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM item WHERE Item_ID = " + idInt);
            resultSet.first();
            Sale sale = new Sale();
            sale.setSaleID(resultSet.getInt("Sale_ID"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(resultSet.getDate("Sales_Date"));
            sale.setSaleDate(strDate);
            sale.setCustomer(custDao.get(resultSet.getInt("Customer_ID")));
            sale.setItems(crtDao.getItems(resultSet.getInt("Cart_ID")));
            sale.setCartID(resultSet.getInt("Cart_ID"));

            return sale;

        } catch (SQLException ex) {
            //    Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * insert Sale object into DB
     *
     * @param object contains object that is going to be inserted
     */
    @Override
    public void insert(Sale object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO sale VALUES (" + object.getSaleID() + ", DATE('" + object.getSaleDate() + "'), '" + object.getCustomerID() + "', '" + object.getCartID() + '"');
        } catch (SQLException ex) {

        }
    }

    /**
     * update Sale object into DB
     *
     * @param object updated object
     */
    @Override
    public void update(Sale object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE SET Customer_ID=" + object.getCustomerID() + ", Sales_Date=DATE('" + object.getSaleDate() + "')  WHERE Item_ID =" + object.getSaleID());
        } catch (SQLException ex) {

        }
    }

    /**
     * delete Sale object from DB
     *
     * @param object object that we're deleting
     */
    @Override
    public void delete(Sale object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM sale WHERE Sale_ID = " + object.getSaleID());
        } catch (SQLException ex) {
            // Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
