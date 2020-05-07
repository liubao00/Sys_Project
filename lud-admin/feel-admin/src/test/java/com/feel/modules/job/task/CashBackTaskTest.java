package com.feel.modules.job.task;

import com.feel.common.utils.TimeUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CashBackTaskTest {

    @Test
    public void run() {
        Date dataNow = TimeUtils.getDateNow();
        String data = TimeUtils.formatDate(dataNow,"yyyy-MM-dd");
        System.out.println(data);
    }
}