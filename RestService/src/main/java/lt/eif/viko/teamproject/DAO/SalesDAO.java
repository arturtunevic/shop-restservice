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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Override
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
                sale.setCustomer(resultSet.getInt("Customer_ID"));
                //sale.setItems(crtDao.getItems(resultSet.getInt("Sale_ID")));
                sales.add(sale);
                next = resultSet.next();
            }
            return sales;
        } catch (SQLException ex) {
            Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            resultSet = statement.executeQuery("SELECT * FROM sales WHERE Sale_ID = " + idInt);
            resultSet.first();
            Sale sale = new Sale();
            sale.setSaleID(resultSet.getInt("Sale_ID"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(resultSet.getDate("Sales_Date"));
            sale.setSaleDate(strDate);
            sale.setCustomer(resultSet.getInt("Customer_ID"));
            return sale;

        } catch (SQLException ex) {
            Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            statement.executeUpdate("INSERT INTO sales VALUES (" + object.getSaleID() + ", DATE('" + object.getSaleDate() + "'), " + object.getCustomer() + ")");
        } catch (SQLException ex) {
            Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            statement.executeUpdate("UPDATE sales SET Customer_ID=" + object.getCustomer() + ", Sales_Date=DATE('" + object.getSaleDate() + "')  WHERE Item_ID =" + object.getSaleID());
        } catch (SQLException ex) {
            Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            statement.executeUpdate("DELETE FROM sales WHERE Sale_ID = " + object.getSaleID());
        } catch (SQLException ex) {
            Logger.getLogger(SalesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
