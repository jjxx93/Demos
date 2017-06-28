package cn.xjx.impl;

import cn.xjx.Timer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by xiongjiaxin on 2017/6/28.
 */
@Service("Timer")
public class TimerImpl implements Timer {
    private static Logger logger = LogManager.getLogger(TimerImpl.class);

    @Override
    public void periodicTask() {
        logger.info("Now is" + new Date());
    }
}
