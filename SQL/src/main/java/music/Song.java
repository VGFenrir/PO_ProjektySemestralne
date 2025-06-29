package music;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public record Song(String artist, String title, int duration) {
    public static class Persistence{
        public static Optional<Song> read(int index){
            String path = "src/main/resources/songs.db";

            try {
                PreparedStatement sql = DatabaseConnection.getDb(path).prepareStatement("SELECT artist,title,length FROM song WHERE id = ?");
                sql.setInt(1, index);

                ResultSet query = sql.executeQuery();
                if (query.next()) {
                    String artist = query.getString(1);
                    String title = query.getString(2);
                    int length = query.getInt(3);

                    return Optional.of(new Song(artist,title,length));
                }
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
            return Optional.empty();
        }
    }
}