package tr.edu.itu.cs.db;

import java.io.Serializable;


public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    int ID;// album id
    private String Name;// album ismi
    private int Singer_ID;// album sahibi
    private int Year;// album yili

    // constructor
    public Album(int id, String name, int singer_id, int year) {
        ID = id;
        Name = name;
        Singer_ID = singer_id;
        Year = year;
    }

    public void PrintAlbum() {
        System.out.println(ID + " " + Name + " " + Singer_ID + " " + Year);
    }

}
