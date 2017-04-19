package spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jjxx9 on 2017/4/13.
 */
//@Component("CDPlayer")
public class CDPlayer implements MediaPlayer {
    private CompactDisc cd;

//    @Autowired()
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }
}
