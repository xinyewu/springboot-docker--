package com.yc.dao;

import com.yc.ResApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ResApp.class})
@Slf4j
public class TestDb {
    //测试datasource 由springboot创建好了吗
    @Autowired
    private DataSource ds;//jdbc数据源
    @Autowired
    private ResFoodMapper resfoodMapper;

    @Test
    public void testDataSource() throws SQLException {
        log.info(ds.getConnection().toString());
    }

    @Test
    public void testResfood() {
        log.info(resfoodMapper.selectList(null).toString());
    }
}
