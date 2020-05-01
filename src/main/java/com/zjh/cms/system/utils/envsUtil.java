package com.zjh.cms.system.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReflectUtil;
import com.zjh.cms.evns.dev.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class envsUtil {
    public static Object call(String className,String methodName,Object... args){
        String aaa = "F:\\learning\\spring boot\\workspace\\cms\\src\\main\\java\\com\\zjh\\cms\\evns\\dev\\";
        aaa+=className+".java";
        boolean fileExist = FileUtil.exist(aaa);
        if(fileExist){
            try {
                className = "com.zjh.cms.evns.dev."+className;
                Class clz = Class.forName(className);
                //
                Object obj = clz.newInstance();
                //获取方法
//                Method m = ReflectUtil.getMethod(obj,methodName,args);
                //获取方法
                Method m = obj.getClass().getDeclaredMethod(methodName);
                //调用方法
                Object result = m.invoke(obj,args);

                return result;
            }catch (Exception e){
                return null;
            }
        }else{
            return null;
        }
//        try {
//            className = "com.zjh.cms.evns.dev.config";
//            methodName = "printMethod";
//            Class clz = Class.forName(className);
//            //
//            Object obj = clz.newInstance();
//            //获取方法
//            Method m = obj.getClass().getDeclaredMethod(methodName);
//            //调用方法
//            String  result = (String) m.invoke(obj,args);
//            System.out.println(result);
//        }catch (Exception e){
//
//        }
    }
}
