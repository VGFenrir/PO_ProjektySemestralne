package auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.DatabaseConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {

    private Map<String,Account> accounts_map;
    private String dbPath = "src/main/resources/shop.db";

    public AccountManager(){
        this.accounts_map = new HashMap<>();
    }

    public int register(String username, String password){
        try {
            //AUTO INCREMENT
            PreparedStatement sql = DatabaseConnection.getDb(this.dbPath).prepareStatement("SELECT MAX(id) FROM auth_account");
            ResultSet query = sql.executeQuery();
            int id = 0;
            if(query.next()){
                id = query.getInt(1)+1;
            }

            //DISTINCT USERNAME
            sql = DatabaseConnection.getDb(this.dbPath).prepareStatement("SELECT name from auth_account WHERE name = ?");
            sql.setString(1,username);
            query = sql.executeQuery();
            if(query.next()){
                System.err.println("Cannot add user, username already taken");
                return -1;
            }

            sql = DatabaseConnection.getDb(this.dbPath).prepareStatement("INSERT INTO auth_account VALUES(?,?,?)");
            sql.setInt(1, id);
            sql.setString(2, username);
            sql.setString(3, BCrypt.withDefaults().hashToString(4, password.toCharArray()));

            sql.execute();
            System.out.println("Registered user: "+username);
            return id;
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return -1;
    }
    public boolean authenticate(String username, String password){
        try {
            PreparedStatement sql = DatabaseConnection.getDb(this.dbPath).prepareStatement("SELECT password FROM auth_account WHERE name = ?");
            sql.setString(1, username);

            ResultSet query = sql.executeQuery();
            if(query.next()){
                BCrypt.Result compResult = BCrypt.verifyer().verify(password.toCharArray(),query.getString(1));
                return compResult.verified;
            }
            return false;
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    public Account getAccount(String username){
        Account result;
        if((result = this.accounts_map.get(username)) != null){
            System.out.println("Retrieved account from the hash map");
            return result;
        }

        try {
            PreparedStatement sql = DatabaseConnection.getDb(this.dbPath).prepareStatement("SELECT id, name FROM auth_account WHERE name = ?");
            sql.setString(1, username);
            ResultSet query = sql.executeQuery();

            if (query.next()) {
                int id = query.getInt(1);
                String name = query.getString(2);
                result = new Account(id, name);
                System.out.println("Retrieved account from the database");
                this.accounts_map.put(username,result);
                return result;
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
