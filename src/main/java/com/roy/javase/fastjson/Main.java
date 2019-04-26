package com.roy.javase.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roy.javase.fastjson.model.SuccessDataTip;
import com.roy.javase.fastjson.model.SuccessTip;

/**
 * @Author: Roy
 * @Date: 2019/4/26 11:05
 */
public class Main {

    public static void main(String[] args) {
        SuccessDataTip successTip = new SuccessDataTip("111");
        successTip.setCode(222);

        String json = JSON.toJSONString(successTip);
        System.out.println(json);

        SuccessDataTip tip = JSONObject.parseObject(json , SuccessDataTip.class);
        System.out.println(tip);
    }
}
