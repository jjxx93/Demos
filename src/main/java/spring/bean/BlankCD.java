package spring.bean;

import java.util.List;

/**
 * Created by jjxx9 on 2017/4/13.
 */
public class BlankCD implements CompactDisc {
    private String title;
    private String artist;
    private List<String> tracks;

    public BlankCD(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
        for (String track : tracks) {
            System.out.println("Track-" + track);
        }
    }
}
