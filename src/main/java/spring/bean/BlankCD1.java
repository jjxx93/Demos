package spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jjxx9 on 2017/4/13.
 */
public class BlankCD1 implements CompactDisc {
    private String title;
    private String artist;
    private List<String> tracks;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTracks(List<String> tracks) {
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
