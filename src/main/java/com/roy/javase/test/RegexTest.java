package com.roy.javase.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        String s = "有域名的http://www.baidu.com&a=1\n" +
                "后面有斜杠http://www.baidu.com/rpc/\n" +
                "没有斜杠http://www.baidu.com/spaddf\n" +
                "短域名http://3.cn/Red3sdk\n" +
                "直接IP地址链接http://123.123.123.123/test.html\n" +
                "前面ftp://123.123.123.123/tet.html和后面有汉字";
//        //区分大小写
//        Pattern p1 = Pattern.compile("the");
//        Matcher m1 = p1.matcher(s);
//        while(m1.find()){
//            String sub = s.substring(m1.start(),m1.end());
//            System.out.print(sub+",start="+m1.start()+" end="+m1.end()+"\t");
//        }
//        System.out.println();
//        //不区分大小写
//        Pattern p2 = Pattern.compile("the",Pattern.CASE_INSENSITIVE);
//        Matcher m2 = p2.matcher(s);
//        while(m2.find()){
//            String sub = s.substring(m2.start(),m2.end());
//            System.out.print(sub+",start="+m2.start()+" end="+m2.end()+"\t");
//        }
//        System.out.println();
//        //匹配每个单词
//        Pattern p3 = Pattern.compile("\\w+",Pattern.CASE_INSENSITIVE);
//        Matcher m3 = p3.matcher(s);
//        while(m3.find()){
//            String sub = s.substring(m3.start(),m3.end());
//            System.out.print(sub+",start="+m3.start()+" end="+m3.end()+"\t");
//        }
//        System.out.println();
//        //匹配带有o的单词
//        Pattern p4 = Pattern.compile("\\w*o\\w*",Pattern.CASE_INSENSITIVE);
//        Matcher m4 = p4.matcher(s);
//        while(m4.find()){
//            String sub = s.substring(m4.start(),m4.end());
//            System.out.print(sub+",start="+m4.start()+" end="+m4.end()+"\t");
//        }

        s = "http://3.cn/123abc Hello World!https://3.cn/abc123,http://3.cn/aaa111你好";

        System.out.println();
        //匹配url
        Pattern p5 = Pattern.compile("(https?)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]",Pattern.CASE_INSENSITIVE);
        Matcher m5 = p5.matcher(s);
        while(m5.find()){
            String sub = s.substring(m5.start(),m5.end());
            System.out.print(sub+",start="+m5.start()+" end="+m5.end()+"\t");
        }

        //区分大小写
        String r = s.replaceAll("(https?)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]"," $0 ");//参数依次为正则表达式，用于替换的字符
        System.out.println(r);

    }
}
