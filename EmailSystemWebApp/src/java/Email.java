/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author venkatesh
 */
@ManagedBean
@SessionScoped
public class Email {

   private int id;
   private String topic;
   private String content;
   private String date;
   private String sid;
   private String rid;
   private String status;
   private String type;
ArrayList<Email> inbox=new ArrayList<Email>();
ArrayList<Email> viewcontent=new ArrayList<Email>();
ArrayList<Email> sent=new ArrayList<Email>();

    public ArrayList<Email> getSent() {
        return sent;
    }

    public void setSent(ArrayList<Email> sent) {
        this.sent = sent;
    }

    public ArrayList<Email> getViewcontent() {
        return viewcontent;
    }

    public void setViewcontent(ArrayList<Email> viewcontent) {
        this.viewcontent = viewcontent;
    }

    public ArrayList<Email> getInbox() {
        return inbox;
    }

    public void setInbox(ArrayList<Email> inbox) {
        this.inbox = inbox;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Email() {
    }

    public Email(String topic, String content, String date, String sid,String rid, String status, String type) {
        this.topic = topic;
        this.content = content;
        this.date = date;
        this.sid = sid;
        this.rid=rid;
        this.status = status;
        this.type = type;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
 
        public String add_object(String rid, String topic) 
        {viewcontent.clear();
              try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();

        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/sudhabalasubv97";
        Connection connection = null;
        Statement statement = null;
       ResultSet resultSet=null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "sudhabalasubv97", "1378032");
            statement = connection.createStatement();
           resultSet = statement.executeQuery("select * from email " +"where rid ='"+rid +"'");
           while(resultSet.next())
           {
               viewcontent.add(new Email(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8)));
           }
       if(viewcontent.isEmpty())
       {
           return "internalError";
       }
          else
       {
           return "viewcontent";
       }
                

            

        } catch (SQLException e) {
            e.printStackTrace();
            return "InternalError";
        } finally {
            try {
                connection.close();
                statement.close();
              

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     
        }

     public String sent(String id)
    {
        rid =id;
          try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();

        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/sudhabalasubv97";
        Connection connection = null;
        Statement statement = null;
       ResultSet resultSet=null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "sudhabalasubv97", "1378032");
            statement = connection.createStatement();
           resultSet = statement.executeQuery("select * from email " +"where sid ='"+id +"'");
           while(resultSet.next())
           {
               sent.add(new Email(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8)));
           }
         
                return "sentbox";
                

            

        } catch (SQLException e) {
            e.printStackTrace();
            return "InternalError";
        } finally {
            try {
                connection.close();
                statement.close();
              

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     
    }
    public String inbox(String id)
    {
        rid =id;
          try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();

        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/sudhabalasubv97";
        Connection connection = null;
        Statement statement = null;
       ResultSet resultSet=null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "sudhabalasubv97", "1378032");
            statement = connection.createStatement();
           resultSet = statement.executeQuery("select * from email " +"where rid ='"+id +"'");
           while(resultSet.next())
           {
               inbox.add(new Email(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8)));
           }
         
                return "inbox";
                

            

        } catch (SQLException e) {
            e.printStackTrace();
            return "InternalError";
        } finally {
            try {
                connection.close();
                statement.close();
              

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     
    }
    public String compose(String id)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();

        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/sudhabalasubv97";
        Connection connection = null;
        Statement statement = null;
      PreparedStatement preparedStatement = null;


        try {
            connection = DriverManager.getConnection(DATABASE_URL, "sudhabalasubv97", "1378032");
            statement = connection.createStatement();
           
          status = "Unread";
          type = "New";
          sid =id;
             //   int i = statement.executeUpdate("insert into job_detail(Job_Title,Job_Description,No_Positions) values('" +getJob_Title()+"','"+getJob_Description()+"','"+getNo_positions()+"'" );
                
            String insertTableSQL = "INSERT INTO email"
		+ "(topic, content, sid,rid,status,type) VALUES"
		+ "(?,?,?,?,?,?)";
 preparedStatement = connection.prepareStatement(insertTableSQL);

preparedStatement.setString(1, topic);
preparedStatement.setString(2, content);
preparedStatement.setString(3, sid);
preparedStatement.setString(4, rid);
preparedStatement.setString(5, status);
preparedStatement.setString(6, type);
preparedStatement.executeUpdate();
                return "emailsent";
                

            

        } catch (SQLException e) {
            e.printStackTrace();
            return "JobNotOK";
        } finally {
            try {
                connection.close();
                statement.close();
              

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     
    }
}
