package org.example;

import auth.AccountManager;
import database.DatabaseConnection;
import music.Song;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.connect("src/main/resources/shop.db");
        DatabaseConnection.connect("src/main/resources/songs.db");
//        DatabaseConnection db = new DatabaseConnection();
//        db.connect("src/main/resources/shop.db");
//
//        try {
//            PreparedStatement sql = db.getDb().prepareStatement("SELECT * FROM auth_account");
//            ResultSet query = sql.executeQuery();
//            while(query.next()){
//                System.out.println(query.getString(2));
//            }
//        }
//        catch(SQLException e){
//            System.err.println(e.getErrorCode());
//            System.err.println(e.getMessage());
//        }
//
//        try {
//            PreparedStatement sql = db.getDb().prepareStatement("SELECT * FROM test");
//            ResultSet query = sql.executeQuery();
//            while(query.next());
//
//
//            sql = db.getDb().prepareStatement("DROP TABLE test");
//            sql.execute();
//            System.out.println("Dropped table test");
//        }
//        catch(SQLException e){
//            if(e.getErrorCode() != 1) {
//                System.err.println(e.getErrorCode());
//                System.err.println(e.getMessage());
//            }
//        }
//
//        try {
//            PreparedStatement sql = db.getDb().prepareStatement("CREATE TABLE test(id NUMBER, name VARCHAR(15))");
//            sql.execute();
//            System.out.println("Created table test");
//        }
//        catch(SQLException e){
//            System.err.println(e.getErrorCode());
//            System.err.println(e.getMessage());
//        }
//
//        try {
//            PreparedStatement sql = db.getDb().prepareStatement("INSERT INTO test VALUES(1,'Ozjasz')");
//            sql.execute();
//            System.out.println("Inserted user Ozjasz");
//        }
//        catch(SQLException e){
//            System.err.println(e.getErrorCode());
//            System.err.println(e.getMessage());
//        }
//
//
//        db.disconnect();

//        AccountManager manager = new AccountManager();
//        manager.register("Ozjasz","password");
//        System.out.println(manager.authenticate("Ozjasz","password"));
//        manager.getAccount("Ozjasz");
//        manager.getAccount("Ozjasz");

        System.out.println(Song.Persistence.read(14));
        System.out.println(Song.Persistence.read(13));
    }
}