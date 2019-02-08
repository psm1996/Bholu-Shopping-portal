/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author saurabh
 */
public class Electronis_DB {
    public static boolean flag = false;
     public static void insertIntoElectronicsDB(String brand, String model, int price, int qty,String Category) throws ClassNotFoundException{
        PreparedStatement ps;
        ResultSet rs;
        String query="INSERT INTO `product`( `productname`, `modelname`, `price`, `quantity`, `category`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5])";
        try {
             ps = My_connection.getConnection().prepareStatement(query);
                rs=ps.executeQuery();
           
            
            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setInt(3, price);
            ps.setInt(4, qty);
            ps.setString(5, Category);
            
            if(ps.executeUpdate()==1)
                JOptionPane.showMessageDialog(null, "Entry successful!");
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Electronis_DB.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
     public static ArrayList<Product_list> TableGenerator() throws SQLException, ClassNotFoundException
        {
           //  System.out.println("3");
            PreparedStatement ps;
            ResultSet rs;
          //   System.out.println("2");
            ArrayList<Product_list> list=new ArrayList<Product_list>();
          //  System.out.println("1");
            String query="SELECT * FROM `product` WHERE `category`=\"Electronics\" ";
        //    System.out.println(query);
            try{
                ps = My_connection.getConnection().prepareStatement(query);
                rs=ps.executeQuery();
                Product_list pl;
                 
            while(rs.next()){
                pl = new Product_list(rs.getString("productname"),rs.getString("modelname"),
                        rs.getInt("price"),rs.getInt("quantity"));
             //   System.out.println(rs.getString("productname")+" "+rs.getString("modelname")+" "+rs.getInt("price")+" "+rs.getInt("quantity"));
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Electronis_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
                
            }
        public static void updateElectronicsDB(String model, int qty) throws ClassNotFoundException{
            PreparedStatement ps;
            ResultSet rs;
            String query;
        query = "UPDATE Product SET quantity=? WHERE modelname=? AND  `category`=\"Electronics\" ";
         try {
             ps = My_connection.getConnection().prepareStatement(query);
            
          
            
            ps.setInt(1, qty);
            ps.setString(2, model);
            if(ps.executeUpdate()==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else if(ps.executeUpdate()==1 && flag){
                JOptionPane.showMessageDialog(null, "Stock updated successfully!");
                flag = false;
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Electronis_DB.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
          public static ArrayList<Product_list> checkStock() throws ClassNotFoundException{
        ArrayList<Product_list> list = new ArrayList<>();
        PreparedStatement ps;
            ResultSet rs;
            String query="SELECT Productname,modelname,qty,price FROM `product` WHERE `category`=\"Electronics\" ";
        try {
        ps = My_connection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            
            Product_list pl;
            
            while(rs.next()){
               pl = new Product_list(rs.getString("productname"),rs.getString("modelname"),
                        rs.getInt("price"),rs.getInt("quantity"));
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Electronis_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
           public static void delete(String model) throws ClassNotFoundException{
               PreparedStatement ps;
            ResultSet rs;
            String query="DELETE FROM Product WHERE `category`=\"Electronics\" AND modelname=? ";
        try { ps = My_connection.getConnection().prepareStatement(query);
            
            
            ps.setString(1, model);
            if(ps.executeUpdate()==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else
                JOptionPane.showMessageDialog(null, "Entry deleted successfully!");
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Electronis_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
   }
     

    
        }
    
        
