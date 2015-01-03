package tr.edu.itu.cs.db;

import java.io.Serializable;


public class Lyric implements Serializable {

    private static final long serialVersionUID = 1L;
    int ID;// soz idsi
    private String Lyrics;// sarki sozu
    private int Song_ID;// sozun sarkisi

    public Lyric(int id, String lyrics, int song_id) {
        ID = id;
        Lyrics = lyrics;
        Song_ID = song_id;
    }

    public void PrintLyric() {
        System.out.println(ID + " " + Lyrics + " " + Song_ID);
    }

}
