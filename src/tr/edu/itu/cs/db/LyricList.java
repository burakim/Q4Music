package tr.edu.itu.cs.db;

import java.io.Serializable;
import java.util.Vector;


public class LyricList implements Serializable {

    private static final long serialVersionUID = 1L;

    Vector<Lyric> list = new Vector<Lyric>();
    int size;

    LyricList() {

        // song idler bilinmiyor -1 atandi
        list.addElement(new Lyric(1, "I'm friends with the monster ", -1));// eminem-the
                                                                           // monster
        list.addElement(new Lyric(2, "Feels like a close, it's coming to ", -1));// eminem-Guts
                                                                                 // over
                                                                                 // fear
        list.addElement(new Lyric(3,
                "Look, if you had one shot, one opportunity", -1));// eminem
                                                                   // -lose
                                                                   // yourself
        list.addElement(new Lyric(4,
                "Sometimes you just feel tired, feel week. ", -1));// eminem-till
                                                                   // i collapse
        list.addElement(new Lyric(
                5,
                "I can't tell you what it really is, I can only tell you what it feels like",
                -1));// eminem-love the way you lie
        list.addElement(new Lyric(6,
                "Midnight, you come and pick me up, no headlights", -1));// taylor
                                                                         // swift-style
        list.addElement(new Lyric(7, "He said let's get out of this town", -1));// taylor
                                                                                // swift-wildest
                                                                                // dream
        list.addElement(new Lyric(8, "Cause baby now we got bad blood ", -1));// taylor
                                                                              // swift-bad
                                                                              // blood
        list.addElement(new Lyric(9, "It's 2 am, in your car ", -1));// taylor
                                                                     // swift-i
                                                                     // wish you
                                                                     // would
        list.addElement(new Lyric(10,
                "Clear blue water, high tides came and brought you in", -1));// taylor
                                                                             // swift-this
                                                                             // love
        size = list.size();
    }

    public void addElement(Lyric yeni) {
        yeni.ID = list.size() + 1;
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
        LyricList lyriclistesi = new LyricList();

        for (int i = 0; i < lyriclistesi.size; i++)
            lyriclistesi.list.elementAt(i).PrintLyric();
    }
}
