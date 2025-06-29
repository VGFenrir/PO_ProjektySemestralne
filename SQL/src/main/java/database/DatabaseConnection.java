package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {
    static Map<String, Connection> connections = new HashMap<>();

    public static Connection getDb(String path) {
        return connections.get(path);
    }

    public static void connect(String path){
        if(connections.get(path) != null){
            System.out.println("Connection already mapped");
            return;
        }

        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:"+path);
            System.out.println("Connected");
            connections.put(path,db);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public static void disconnect(String path){
        try {
            connections.get(path).close();
            connections.remove(path);
            System.out.println("Disconnected");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
