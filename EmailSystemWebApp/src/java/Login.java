/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lalitha
 */
@ManagedBean(name = "loginValue")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    //attributes
    private String id;
    private String password;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   

   

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    /*    public OnlineAccount getTheLoginAccount() {
     return theLoginAccount;
     }
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String Logins() {
        //load the Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/sudhabalasubv97";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL,
                    "sudhabalasubv97", "1378032");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from emailaccount "
                    + "where ID = '"
                    + id + "'");

            if (resultSet.next()) {
                //id is found
                if (password.equals(resultSet.getString(3))) {
                   
                  name = resultSet.getString(1);
                    return "welcome";

                } else {
                    id = "";
                    password = "";
                    //display loginNotOK.xhtml
                    return "loginNotOk";
                }
            } else {
                id = "";
                password = "";
                //id is not found, display loginNotOK.xhtml
                return "loginNotOk1";

            }

        } catch (SQLException e) {
            //go to internalError.xhtml
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //return "login.xhtml";
    }

}
