/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Void
 */
public class BillingDB {
    
    public static void insertIntoBillingDB(String username, int price, String date) throws ClassNotFoundException{
        PreparedStatement ps;
            ResultSet rs;
            String query="INSERT INTO billing(uname, bill, date) VALUES(?,?,?)";
        try { ps = My_connection.getConnection().prepareStatement(query);
                rs=ps.executeQuery();
         
         
            
            
            ps.setString(1, username);
            ps.setInt(2, price);
            ps.setString(3, date);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Mobile_DB.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    public static ArrayList<Bill_object> billlings() throws ClassNotFoundException{
         PreparedStatement ps;
            ResultSet rs;
        ArrayList<Bill_object> customers = new ArrayList<>();
      String query="SELECT id, uname, bill, date FROM billing";
        try {ps = My_connection.getConnection().prepareStatement(query);
            
             rs = ps.executeQuery();
            
            Bill_object pl;
            
            while(rs.next()){
                pl = new Bill_object(rs.getInt("id"),rs.getString("uname"),
                        rs.getInt("bill"),rs.getString("date"));
                customers.add(pl);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Mobile_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    //Deleting billing log
    public static void deleteBillings(){
         try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/billingDB.db");
            PreparedStatement ps = con.prepareStatement("DELETE FROM billing");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "All entries have been deleted!");
            
        } catch (SQLException ex) {
            Logger.getLogger(Electronis_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
