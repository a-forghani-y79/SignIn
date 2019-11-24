package sample;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    Connection c;
    Statement st;
    public DataBase(){
        connect();
        createTable();
       // newPearson(null,null,null,null,null);

       // close();


    }

    public void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:signinDB.db");
             st = c.createStatement();
            System.out.println("Connection Created!!");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createTable(){
        String tableSQL="  CREATE TABLE IF NOT EXISTS RegisteryTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,firstname TEXT,lastname TEXT ,username TEXT ,email TEXT,password TEXT )";
        try{
            st.executeUpdate(tableSQL);
            System.out.println("RegistaerTable created!");
        }catch (Exception e){
            System.out.println("create error");
        }
    }
    public void close(){

        try {
            c.close();
            System.out.println("closesd!!");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void newPearson(String firstname,String lastname,String username,String email,String password){
        String tableSQL="INSERT INTO RegisteryTable (firstname,lastname,username,email,password) VALUES ('"+firstname+"','"+lastname+"','"+username+"','"+email+"','"+password+"')";
        try{
            st.executeUpdate(tableSQL);
            System.out.println("new pearson Iserted!!");

        }catch (SQLException e){
            System.out.println(e.getMessage());

        }
    }
    public boolean UserCheck(String username){
        boolean flag=false;
        String SQL=" SELECT username FROM RegisteryTable WHERE username='"+username+"'";
        try {
            ResultSet resultSet = st.executeQuery(SQL);
            flag=resultSet.next();

        }catch (SQLException e){
            System.out.println("USER CHECK:");
            System.out.println(e.getMessage());

        }

        return flag;


    }
    public String getPassword(String username){
        String SQL="SELECT password FROM RegisteryTable WHERE username='"+username+"'";
        String pass="";
        try {
            ResultSet resultSet=st.executeQuery(SQL);
             pass=resultSet.getString(1);
        }catch (SQLException e){
            System.out.println("GET PASSWORD:");
            System.out.println(e.getMessage());
        }
        return pass;
    }
    public String getName(String username){
        String name="";
        String SQL="SELECT firstname,lastname FROM RegisteryTable WHERE username='"+username+"'";
        try{
            ResultSet resultSet=st.executeQuery(SQL);
            name=resultSet.getString(1)+" "+resultSet.getString(2);
            }catch (SQLException e){
            System.out.println("GET NAME:");
            System.out.println(e.getMessage());
        }

        return name;
    }
    public String getEmail(String username){
        String email="";
        String SQL="SELECT email FROM RegisteryTable WHERE username='"+username+"'";
        try {
            ResultSet resultSet=st.executeQuery(SQL);
            email=resultSet.getString(1);

        }catch (SQLException e){
            System.out.println("GET EMAIL");
            System.out.println(e.getMessage());
        }

    return email;
    }














}
