/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crescentMain;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;

public class DbConnection{
    
    public static Connection connection = null;
    PreparedStatement pst = null;
    public ResultSet rst = null;
    Statement st = null;
    
    
    public Connection OpenConnection ()   throws SQLException{
       
        
            String dataSourceName = "crescent.accdb";
            String dir = System.getProperty("user.dir");
            String url = "jdbc:ucanaccess://" + dir + "/" + dataSourceName;
     
     connection = null;
      try{
          
          connection = DriverManager.getConnection(url);
          
          
      }catch(Exception e){
          System.out.println(e);
      }
      return connection;
    }
    
    public  ResultSet GetData(String Sql)
    {
        try {
            pst = connection.prepareStatement(Sql);
            rst = pst.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\nDbConnection GetData Error");
        }
        return rst;
    }
    
    public int InsertUpdateDelete(String Sql)
    {
        int flag=0;
        try {
            pst = connection.prepareStatement(Sql);
            flag = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return flag ;
    }
    
    public void CloseConnection(){
        
    if (rst != null) {
        try {
            rst.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (pst != null) {
        try {
            pst.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (connection != null) {
        try {
            connection.close();
        } catch (SQLException e) { /* ignored */}
    }

    }
}


