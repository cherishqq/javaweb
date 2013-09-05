package com.platform.sys.cache;

import java.io.InputStream;

import net.sf.ehcache.Cache;  
import net.sf.ehcache.CacheManager;  

public class CacheFactory {
    private CacheManager manager;
    private static  CacheFactory factory= null;
    private CacheFactory(){
        InputStream is = CacheFactory.class  
                .getResourceAsStream("/cache/ehcache-remote1.xml");  
        //读入配置  
        manager = new CacheManager(is);
    }
    public static CacheFactory getInstance(){
        if(factory==null){
            factory = new CacheFactory();
        }
        return factory;
    }
    public Cache getCache(){
        return manager.getCache("userIdcache");
    }
    public static void main(String[] args) {
        System.out.println(CacheFactory.getInstance().getCache());
    }
}
