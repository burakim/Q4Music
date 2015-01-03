package tr.edu.itu.cs.db;

import java.io.Serializable;


public class SingerModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int ID;
    private String NameSurname;
    private String BirthDate;
    private String Type;

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }

    public String getNameSurname() {
        return NameSurname;
    }

    public void setNameSurname(String nameSurname) {
        NameSurname = nameSurname;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public SingerModel() {

    }

    public SingerModel(String nameSurname, String birthDate, String type) {
        setNameSurname(nameSurname);
        setBirthDate(birthDate);
        setType(type);
    }

    public SingerModel(int id, String nameSurname, String birthDate, String type) {
        setID(id);
        setNameSurname(nameSurname);
        setBirthDate(birthDate);
        setType(type);
    }

}
