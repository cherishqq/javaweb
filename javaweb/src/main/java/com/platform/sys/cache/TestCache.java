package com.platform.sys.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class TestCache {
	public static void main(String[] args) {
        Cache cache = CacheFactory.getInstance().getCache();
        Element element = new Element("key1", "value1");
        cache.put(element);
        System.out.println("server1: put cache :"+element.getValue());
/*        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }

}
