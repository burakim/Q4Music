package tr.edu.itu.cs.db;

import java.io.Serializable;
import java.util.Vector;


public class BandList implements Serializable {

    private static final long serialVersionUID = 1L;
    Vector<Band> list = new Vector<Band>();
    int size;

    BandList() {

        // singer id lere -1 verildi bilinmiyor
        list.addElement(new Band(1, "Beatles", -1, "Guitar,Vocal"));// John
                                                                    // Lennon
        list.addElement(new Band(2, "Beatles", -1, "Guitar"));// Paul McCartney
        list.addElement(new Band(3, "Beatles", -1, "Guitar,Vocal"));// George
                                                                    // Harrison
        list.addElement(new Band(4, "Beatles", -1, "Drums"));// Ringo Starr
        list.addElement(new Band(5, "Abba", -1, "Vocals"));// Agnetha Faltskog
        list.addElement(new Band(6, "Abba", -1, "Guitar"));// Bjorn Ulvanues
        list.addElement(new Band(7, "Abba", -1, "Accordion"));// Benny Anderson
        list.addElement(new Band(8, "Abba", -1, "Vocals"));// Anni-Fid Lyngstad
        list.addElement(new Band(9, "Quenn", -1, "Vocals"));// Brian May
        list.addElement(new Band(10, "Quenn", -1, "Vocals"));// Freddie Mercury

        size = list.size();
    }

    public void addElement(Band yeni) {
        yeni.ID = list.size() + 1;// liste sonuna ekleme
        list.addElement(yeni);
        size = list.size();
    }

    public void deleteElement(int id) {
        list.remove(id - 1);

        for (int i = id - 1; i < list.size(); i++)
            list.elementAt(i).ID--;
        size = list.size();

    }

    public static void main(String[] args) {
        BandList bandlistesi = new BandList();

        for (int i = 0; i < bandlistesi.size; i++)
            bandlistesi.list.elementAt(i).PrintBand();
    }
}
