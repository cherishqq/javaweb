package com.platform.sys.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class TestCache2 {

	public static void main(String[] args) {
        Cache cache = CacheFactory2.getInstance().getCache();
/*        try {
            Thread.sleep(10000l);//为了等待缓存同步
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        Element element = cache.get("key1");
        System.out.println("server2："+element.getValue());
               
    }
}
