package tr.edu.itu.cs.db;

import java.io.Serializable;


public class SongModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int ID;
    private String Name;
    private int TypeId;
    private int SingerId;

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public int getSingerId() {
        return SingerId;
    }

    public void setSingerId(int singerId) {
        SingerId = singerId;
    }

    public SongModel() {
        super();
    }

    public SongModel(String name, int typeId, int singerId) {
        super();
        setName(name);
        setTypeId(typeId);
        setSingerId(singerId);
    }

    public SongModel(int id, String name, int typeId, int singerId) {
        super();
        setID(id);
        setName(name);
        setTypeId(typeId);
        setSingerId(singerId);
    }

}
