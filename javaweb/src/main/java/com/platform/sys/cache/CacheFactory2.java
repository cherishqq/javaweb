package com.platform.sys.cache;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

	
public class CacheFactory2 {
	    private CacheManager manager;
	    private static  CacheFactory2 factory= null;
	    private CacheFactory2(){
	        InputStream is = CacheFactory2.class  
	                .getResourceAsStream("/cache/ehcache-remote2.xml");  
	        //读入配置  
	        manager = new CacheManager(is);
	    }
	    public static CacheFactory2 getInstance(){
	        if(factory==null){
	            factory = new CacheFactory2();
	        }
	        return factory;
	    }
	    public Cache getCache(){
	        return manager.getCache("userIdcache");
	    }
	    public static void main(String[] args) {
	        System.out.println(CacheFactory2.getInstance().getCache());
	    }

}
