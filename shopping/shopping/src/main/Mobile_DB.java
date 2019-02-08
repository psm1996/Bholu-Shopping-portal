/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author saurabh
 */
public class Mobile_DB {
    public static boolean flag = false;
     public static ArrayList<Product_list> TableGenerator() throws SQLException, ClassNotFoundException
        {
           //  System.out.println("3");
            PreparedStatement ps;
            ResultSet rs;
          //   System.out.println("2");
            ArrayList<Product_list> list=new ArrayList<Product_list>();
          //  System.out.println("1");
            String query="SELECT * FROM `product` WHERE `category`=\"Mobile\" ";
        //    System.out.println(query);
            try{
                ps = My_connection.getConnection().prepareStatement(query);
                rs=ps.executeQuery();
                Product_list pl;
                 
            while(rs.next()){
                pl = new Product_list(rs.getString("productname"),rs.getString("modelname"),
                        rs.getInt("price"),rs.getInt("quantity"));
               // System.out.println(rs.getString("productname")+" "+rs.getString("modelname")+" "+rs.getInt("price")+" "+rs.getInt("quantity"));
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Mobile_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
                
            }
        }
