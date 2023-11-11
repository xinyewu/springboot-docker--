package com.yc.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.ResApp;

import com.yc.bean.Resfood;
import com.yc.web.model.MyPageBean;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ResApp.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@AutoConfigureMockMvc(addFilters = false)
class ResfoodBizImplTest {
//    @Autowired
//    private ResfoodBizImpl resfoodBiz;
//
//    @Autowired
//    private MockMvc mockMvc;
//    Resfood resfood = new Resfood(1, "素炒莴笋丝", 22.00, 20.00, "营养丰富", "500008.jpg");
//
//    @Test
//    @DisplayName("mock测试")
//    public void testController() {
//        try {
//            mockMvc.perform(MockMvcRequestBuilders.get("/resfood/findById/1")
//                    .contentType(MediaType.APPLICATION_JSON_VALUE)
//            )
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.fname", Matchers.equalToIgnoringCase(resfood.getFname())))
//                    .andReturn();
//
//        } catch (Exception e) {
//            log.error("当前方法：{}，发生了异常", "findById");
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    @DisplayName("mock测试postJson")
//    public void testPostJson() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/resfood/findAll")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n}" +
//                        "\"fid\":\"1\",\n" +
//                        "\"fname\":\"素炒莴笋丝\"" +
//                        "}")
//                .accept(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    @DisplayName("mock测试postForm")
//    public void testPostForm() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/resfood/findAll")
//        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//        .accept(MediaType.APPLICATION_JSON));
//    }
//
//
//    @Test
//    public void findAll() {
//        Assert.notNull(resfoodBiz.findAll());
//    }
//
//    @Test
//    public void findById() {
//        Assert.notNull(resfoodBiz.findById(1));
//    }
//
//    @Test
//    public void findByPage() {
//        MyPageBean page = resfoodBiz.findByPage(3, 2, "fid", "desc");
//        Assert.notNull(page);
//        log.info("总记录数 = " + page.getTotal());
//        log.info("总页数 = " + page.getTotalpages());
//        log.info("当前页码 = " + page.getPageno());
//        log.info("每页多少="+page.getPagesize());
//    }
//
//    @Test
//    public void addResfood() {
//        Resfood resfood = new Resfood();
//        resfood.setFname("牛乳");
//        resfood.setDetail("好吃");
//        resfood.setRealprice(11.1);
//        resfood.setFphoto("1111.png");
//        resfood.setNormprice(11.2);
//        this.resfoodBiz.addResfood(resfood);
//
//    }
}