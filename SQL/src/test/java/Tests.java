import music.Playlist;
import music.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests{
    @Test
    public void isEmpty(){
        Playlist playlist = new Playlist();

        assertTrue(playlist.isEmpty());
    }

    @Test
    public void isPlaylistLengthUpdated(){
        Playlist playlist = new Playlist();
        playlist.add(new Song("Iron Maiden","Fear is the Key",534));

        assertEquals(1,playlist.size());
    }

    @Test
    public void isSongCorrect(){
        Playlist playlist = new Playlist();
        Song song = new Song("Iron Maiden","Be Quick or Be Dead", 323);
        playlist.add(song);

        assertEquals(song,playlist.getFirst());
    }

    @Test
    public void isSongTheSame(){
        Playlist playlist = new Playlist();
        Song song = new Song("Iron Maiden", "The Apparition", 354);
        playlist.add(new Song("Iron Maiden","The Apparition", 354));

        assertEquals(song,playlist.getFirst());
    }

    @Test
    public void isPlaylistAtSecond(){
        Playlist playlist = new Playlist();
        playlist.add(new Song("Iron Maiden","Fear is the Key",534));
        playlist.add(new Song("Iron Maiden","Be Quick or Be Dead", 323));
        playlist.add(new Song("Iron Maiden","The Apparition", 354));
        playlist.add(new Song("Iron Maiden", "Fear of the Dark", 718));

        assertEquals(new Song("Iron Maiden","The Apparition", 354), playlist.atSecond(534+323+353));
    }

    @Test
    public void isIndexOutOfBounds(){
        Playlist playlist = new Playlist();
        playlist.add(new Song("Iron Maiden","Fear is the Key",534));
        playlist.add(new Song("Iron Maiden","Be Quick or Be Dead", 323));
        playlist.add(new Song("Iron Maiden","The Apparition", 354));
        playlist.add(new Song("Iron Maiden", "Fear of the Dark", 718));

        assertThrows(IndexOutOfBoundsException.class, () -> playlist.atSecond(534+323+353+720));
    }
}
