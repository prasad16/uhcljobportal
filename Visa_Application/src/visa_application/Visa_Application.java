/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visa_application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Prejan
 */
public class Visa_Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
       
        System.out.println("Welcome To Visa Processing Center");
        System.out.println("Please Enter Your Nation ");
        String nation=input.next();
        System.out.println("Please Enter Your Passport ID");
        String passport=input.next();
        System.out.println("Please Enter The Visa Type");
        String type=input.next();
        System.out.println("Please Enter the Field of Study like SCI, BUS");
        String study=input.next();
        System.out.println("Please Enter The Month ");
        String month=input.next();
        System.out.println("Please Enter Your Spouse ID ");
        String spouse=input.next();
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/venkatehsarp93";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset=null;
        
        try{
            connection = DriverManager.getConnection(DATABASE_URL,"venkateshsarp93","1348303");
            statement = connection.createStatement();
            resultset = statement.executeQuery("select * from  visa where PassportID = '" +
                    passport +"' and Type = '" +type + "'");
            ArrayList<Visa>checking = new ArrayList<Visa>();
            
            
            while(resultset.next())
            {
                checking.add(new Visa (resultset.getString("PassportID"),
                resultset.getString("Time"),resultset.getString("Status")));
            }
            if(checking.size()==0)
            {
                if(study.equals("SCI")&& nation.equals("CHN") || nation.equals("VEN"))
                {
                    String status = "Admin";
                    
                    int r = statement.executeUpdate("insert into visa values('"+
                            nation + "' ,'"+passport +"','" + type + "','"+
                            study +"','" + month + "','" + spouse + "','" + status +"')");
                    System.out.println("Your Visa is for Admin Processing");
                
                }
                else
                {
                    String status = "Approved";
                    int r = statement.executeUpdate("insert into visa values('"+
                            nation + "' ,'"+passport +"','" + type + "','"+
                            study +"','" + month + "','" + spouse + "','" + status +"')");
                    System.out.println("Your Visa is Approved");
                    
                    
                }
            }
                else
                {
                    int lastindex = checking.size()-1;
                    if(checking.get(lastindex).getMonth().equals(month))
                    {
                        System.out.println("You cannot Apply For The Same Type in Same Month");
                    }
                    else 
                    {
                        if(checking.get(lastindex).getStatus().equals("Approved"))
                        {
                             String status = "Approved";
                    int r = statement.executeUpdate("insert into visa values('"+
                            nation + "' ,'"+passport +"','" + type + "','"+
                            study +"','" + month + "','" + spouse + "','" + status +"')");
                    System.out.println("Your Visa is Approved");
                        }
                        else if(checking.get(lastindex).getStatus().equals("Rejected"))
                        {
                            String status = "Rejected";
                            int r = statement.executeUpdate("insert into visa values('"+
                            nation + "' ,'"+passport +"','" + type + "','"+
                            study +"','" + month + "','" + spouse + "','" + status +"')");
                            System.out.println("Your Visa is Rejected");
                            
                        }
                        else if(checking.get(lastindex).getStatus().equals("Admin"))
                        {
                            System.out.println("You have One Pending Application");
                        }
                        else
                        {
                            System.out.println("No Records Found in our database");
                        }
                    }
                }
             if(type.equals("F2"))
    {
        if(resultset.getString("PassportID").equals(spouse))
        {
            if(resultset.getString("Status").equals("Approved"))
            {
                String status = "Approved";
                    int r = statement.executeUpdate("insert into visa values('"+
                            nation + "' ,'"+passport +"','" + type + "','"+
                            study +"','" + month + "','" + spouse + "','" + status +"')");
                    System.out.println("Your Visa is Approved");
            }
            else if(resultset.getString("Status").equals("Rejected"))
            {
                  String status = "Rejected";
                            int r = statement.executeUpdate("insert into visa values('"+
                            nation + "' ,'"+passport +"','" + type + "','"+
                            study +"','" + month + "','" + spouse + "','" + status +"')");
                            System.out.println("Your Visa is Rejected");
            }
            else if(resultset.getString("Status").equals("Admin"))
            {
                String status = "Admin";
                 int r = statement.executeUpdate("insert into visa values('"+
                            nation + "' ,'"+passport +"','" + type + "','"+
                            study +"','" + month + "','" + spouse + "','" + status +"')");
                 System.out.println("Your Visa is in Admin status");
            }
        }
        else
        {
            System.out.println("No Id Matches");
        }
        
    }
            
           
                        
                        }
            
            
        

        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                connection.close();
                statement.close();
                resultset.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
    }
   
}
}

