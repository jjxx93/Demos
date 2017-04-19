package spring.bean;

import org.springframework.stereotype.Component;

/**
 * Created by jjxx9 on 2017/4/13.
 */
//@Component("Fantastic")
public class Fantastic implements CompactDisc {
    private String title = "Fantastic";
    private String artist = "Jay";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
