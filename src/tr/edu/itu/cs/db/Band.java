package tr.edu.itu.cs.db;

import java.io.Serializable;


public class Band implements Serializable {

    private static final long serialVersionUID = 1L;

    int ID;// grup id
    private String Name;// grup ismi
    private int Singer_ID;// grup uyesi
    private String Instrument;// calgisi

    // constructor
    public Band(int id, String name, int singer_id, String instrument) {
        ID = id;
        Name = name;
        Singer_ID = singer_id;
        Instrument = instrument;
    }

    void PrintBand() {
        System.out
                .println(ID + " " + Name + " " + Singer_ID + " " + Instrument);
    }

}
