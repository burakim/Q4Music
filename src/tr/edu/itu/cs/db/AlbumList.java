package tr.edu.itu.cs.db;

import java.io.Serializable;
import java.util.Vector;


public class AlbumList implements Serializable {

    private static final long serialVersionUID = 1L;
    Vector<Album> list = new Vector<Album>();
    int size;

    AlbumList() {
        list.addElement(new Album(1, "1989", -1, 2014));// Taylor Swift
        list.addElement(new Album(2, "X", -1, 2014));// Ed Sheeran
        list.addElement(new Album(3, "The Eminem Show", -1, 2002));// Eminem
        list.addElement(new Album(4, "Red", -1, 2012));// Taylor Swift
        list.addElement(new Album(5, "My Everything", -1, 2014));// Ariana
                                                                 // Grande
        list.addElement(new Album(6, "In The Lonely Hour", -1, 2014));// Sam
                                                                      // Smith
        list.addElement(new Album(7, "The Marshall Mathers LP 2", -1, 2013));// Eminem
        list.addElement(new Album(8, "X(Deluxe)", -1, 2014));// Chris Brown
        list.addElement(new Album(9, "Sweet Talker", -1, 2013));// Jessie J
        list.addElement(new Album(10, "1000 Forms of Fear", -1, 2014));// Sia

        size = list.size();

    }

    public void addElement(Album yeni) {
        yeni.ID = list.size() + 1;// liste sonuna ekleme
        list.addElement(yeni);
        size = list.size();

    }

    public void deleteElement(int id) {
        list.remove(id - 1);
        // degisiklikten dolayi idleri ayarlama
        for (int i = id - 1; i < list.size(); i++)
            list.elementAt(i).ID--;
        size = list.size();
    }

    public static void main(String[] args) {
        AlbumList albumListesi = new AlbumList();

        for (int i = 0; i < albumListesi.size; i++)
            albumListesi.list.elementAt(i).PrintAlbum();
    }
}
