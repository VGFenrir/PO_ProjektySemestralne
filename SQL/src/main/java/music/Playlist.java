package music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song>{
    public Song atSecond(int num){
        int sum = 0;
        for(Song song: this){
            sum += song.duration();
            if(sum > num) return song;
        }
        throw new IndexOutOfBoundsException();
    }
}
