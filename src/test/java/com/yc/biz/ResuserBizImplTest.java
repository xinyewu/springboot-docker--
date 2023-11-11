package com.yc.biz;

import com.yc.ResApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ResApp.class})
@Slf4j
class ResuserBizImplTest {
    @Autowired
    private ResuserBizImpl resuserBiz;

    @Test
    public void findByName() {
        Assert.notNull(resuserBiz.findByName("a"));
    }

    @Test
    public void testFindByName() {
        Assert.notNull(resuserBiz.findByName("a","0cc175b9c0f1b6a831c399e269772661"));
    }

    @Test
    public void findById() {
        Assert.notNull(resuserBiz.findById(1));
    }
    @Test
    public void testMd5(){
        String md5Pass= DigestUtils.md5DigestAsHex("a".getBytes());
        System.out.println(md5Pass);
    }
}