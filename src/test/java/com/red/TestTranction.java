package com.red;

import com.red.service.RedDetailService;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Created by huichao on 2015/12/30.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestTranction extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private RedDetailService redDetailService;
    @org.junit.Test
    @Rollback(false)
    public void testInsert() throws Exception{
        redDetailService.saveTest();
    }

}
