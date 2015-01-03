package tr.edu.itu.cs.db;

import java.io.Serializable;


public class SongTypeModel implements Serializable {

    private int ID;
    private String Name;

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }

    public String getName() {
        return Name;
    }

    public SongTypeModel() {
        super();
    }

    public void setName(String name) {
        Name = name;
    }

    public SongTypeModel(int id, String name) {
        setID(id);
        setName(name);
    }

}
