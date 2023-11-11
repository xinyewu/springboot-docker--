package com.yc.web.controller;

import com.yc.bean.Resfood;
import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.biz.ResfoodBiz;
import com.yc.biz.ResorderBiz;
import com.yc.web.model.CartItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


@RestController
@RequestMapping("resorder")
@Slf4j
@Api(tags = "购物车管理")
public class ResorderController {
    @Autowired
    private ResfoodBiz resfoodBiz;
    @Autowired
    private ResorderBiz resorderBiz;

    @RequestMapping(value = "confirmOrder", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "提交订单")
    public Map<String, Object> confirmOrder(Resorder order, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (session.getAttribute("cart") == null || ((Map<Integer, CartItem>) session.getAttribute("cart")).size() <= 0) {
            map.put("code", -1);
            map.put("msg", "暂无任何商品");
            return map;
        }
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (session.getAttribute("resuser") == null) {
            map.put("code", -2);
            map.put("msg", "非登录用户不能下单");
            return map;
        }
        Resuser resuser = (Resuser) session.getAttribute("resuser");
        order.setUserid(resuser.getUserid());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        order.setOrdertime(formatter.format(now));
        if (order.getDeliverytime() == null || "".equals(order.getDeliverytime())) {
            LocalDateTime deliverytime = now.plusHours(1);
            order.setOrdertime(formatter.format(deliverytime));
        }
        order.setStatus(0);
        try {
            resorderBiz.order(order, new HashSet(cart.values()), resuser);
        } catch (Exception e) {
            map.put("code", -3);
            map.put("msg", e.getMessage());
            e.printStackTrace();
            return map;
        }
        session.removeAttribute("cart");
        map.put("code", 1);
        return map;
    }

    @RequestMapping(value = "getCartInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "保存购物车")
    public Map<String, Object> getCartInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (session.getAttribute("cart") == null || ((Map<Integer, CartItem>) session.getAttribute("cart")).size() <= 0) {
            map.put("code", 0);
            return map;
        }
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        map.put("code", 1);
        map.put("obj", cart.values());//返回的是map的值的set
        return map;
    }

    @RequestMapping(value = "clearAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "清空购物车")
    public Map<String, Object> clearAll(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        session.removeAttribute("cart");
        map.put("code", 1);
        return map;
    }

    @RequestMapping(value = "addCart", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "添加购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fid", value = "菜品号", required = true),
            @ApiImplicitParam(name = "num", value = "数量", required = true),
    })
    public Map<String, Object> addCart(Integer fid, Integer num, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        //根据fid取出商品信息
        Resfood food = resfoodBiz.findById(fid);
        if (food == null) {
            map.put("code", -1);
            map.put("msg", "查无此商品");
        }
        //从session取出Cart(map)
        Map<Integer, CartItem> cart = new HashMap<Integer, CartItem>();
        if (session.getAttribute("cart") != null) {
            cart = (Map<Integer, CartItem>) session.getAttribute("cart");//引用对象
        } else {
            session.setAttribute("cart", cart);
        }
        CartItem ci;
        //  判断这个商品在map中是否有
        if (cart.containsKey(fid)) {
            //有，加数量，
            ci = cart.get(fid);
            ci.setNum(ci.getNum() + num);
            cart.put(fid, ci);
        } else {
            //没有，则创建一个CartItem,存到map中
            ci = new CartItem();
            ci.setNum(num);
            ci.setFood(food);
            cart.put(fid, ci);
        }
        //处理数量
        if (ci.getNum() <= 0) {
            cart.remove(fid);
        }
        session.setAttribute("cart", cart);//->spring httpSession redis->  监听器监听值的变化
        map.put("code", 1);
        map.put("obj", cart.values());
        return map;
    }
}
