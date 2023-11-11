package com.yc.biz;

import com.yc.ResApp;
import com.yc.bean.Resfood;
import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.web.model.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ResApp.class})
@Slf4j
class ResorderBizImplTest {
    @Autowired
    private ResorderBiz resorderBiz;

//    @Test
//    public void order() {
//        Resorder resorder=new Resorder();
//        resorder.setAddress("湖工");
//        resorder.setDeliverytime(new Date());
//        resorder.setOrdertime(new Date());
//        resorder.setPs("好吃");
//        resorder.setTel("q111111");
//        resorder.setStatus(0);
//
//        Set<CartItem> cartItemSet=new HashSet<>();
//
//        Resfood resfood=new Resfood();
//        resfood.setFid(2);
//        resfood.setFname("蛋炒饭");
//        resfood.setRealprice(22.2);
//        CartItem cartItem=new CartItem(resfood,1);
//        cartItemSet.add(cartItem);
//
//        Resfood resfood1=new Resfood();
//        resfood1.setFid(3);
//        resfood1.setFname("酸辣鱼");
//        resfood1.setRealprice(11.1);
//        CartItem cartItem1=new CartItem(resfood1,2);
//        cartItemSet.add(cartItem1);
//
//        Resuser resuser=new Resuser();
//        resuser.setUserid(1);
//        resorderBiz.order(resorder,cartItemSet,resuser);
//    }
}