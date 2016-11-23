/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author lalitha
 */
@Named(value = "registration")
@RequestScoped
@ManagedBean
public class Registration {

    /**
     * Creates a new instance of Registration
     */
    private String username;
    private String id;
    private String password;
   
//    private String ssn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String register() {
        //load the driver
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            //error message
            return ("Internal Error! Please try again later.");
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/sudhabalasubv97";

            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL,
                    "sudhabalasubv97", "1378032");
            statement = connection.createStatement();
            //to search an onlineaccount based on id or ssn
            resultSet = statement.executeQuery("Select * from emailaccount "
                    + "where ID = '"
                    + id + "'");
            //either the id is used or the ssn is used
            if (resultSet.next()) {
                return ("Either you have an online account already "
                        + "or your online ID is not available to register");
            } else {
                //id good
                int r = statement.executeUpdate("insert into emailaccount "
                        + "values ('" + username + "', '" + id+ "', '" + password + "')"); //, '" 
                //+ ssn + "')");, 
                return ("Registration Successful! Please "
                        + "return to login your account.");

            }
        } catch (SQLException e) {
            //error message
            e.printStackTrace();
            return ("Internal Error! Please try again later.");

        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
