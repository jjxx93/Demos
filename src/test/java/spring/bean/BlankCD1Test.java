package spring.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by jjxx9 on 2017/4/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/CD-bean.xml"})
public class BlankCD1Test {
    @Autowired
    private BlankCD cd;

    @Test
    public void play() throws Exception {
        cd.play();
    }

}