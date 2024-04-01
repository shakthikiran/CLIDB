package org.example;

import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url,usename,password;
        url="jdbc:postgresql://localhost:5432/mydatabase";
        usename="shakthi";
        password="password";
        Main m=new Main();
        try {
            Connection conn = DriverManager.getConnection(url, usename, password);
            Scanner sc = new Scanner(System.in);
            Statement str = conn.createStatement();
            System.out.println("Connection established");

            int choice;
            while (true) {
                System.out.println(("Enter your choice: \n1. Insert\n2. Display\n3.Delete\n4.Exit"));
                choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("ID:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Name:");
                    String name = sc.nextLine();
                    String insert = "insert into employees values(?,?);";
                    PreparedStatement temp = conn.prepareStatement(insert);
                    temp.setInt(1, id);
                    temp.setString(2, name);
                    if (temp.executeUpdate() > 0) {
                        System.out.println("Value Inserted");
                    } else {
                        System.out.println("Failed");
                    }
                    temp.close();
                } else if (choice == 2) {
                    String display = "select * from employees";
                    ResultSet res = m.execute(str, display);
                    System.out.println("Table");
                    while (res.next()) {
                        System.out.println(res.getString(1) + " " + res.getString(2));
                    }
                } else if (choice == 3) {
                    System.out.println("ID:");
                    int value = sc.nextInt();
                    String delete = "delete from employees where id=" + value + ";";
                    m.run(str, delete);
                    System.out.println("Value Deleted");
                } else if (choice == 4) {
                    System.out.println("Bye");
                    break;
                } else {
                    System.out.println("Choose a valid choice");
                }
            }
            str.close();
            conn.close();
        }
        catch (SQLException e){System.out.println(e.getMessage());}



    }
    ResultSet execute(Statement str,String query) throws SQLException{
        return str.executeQuery(query);
    }
    void run(Statement str,String query) throws SQLException{
        str.executeUpdate(query);
    }
}