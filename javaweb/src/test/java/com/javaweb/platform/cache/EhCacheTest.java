package com.javaweb.platform.cache;

import static org.junit.Assert.assertEquals;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ContextConfiguration(locations = { "/cache/applicationContext-ehcache.xml" })
public class EhCacheTest extends AbstractJUnit4SpringContextTests{
	

	/**
	 * 为何这里使用CacheManager ehcacheManager 可以正确射入,不是 EhCacheManagerFactoryBean 类型吗？
	 * 然道在这边决定了？？？
	 */
	
	@Autowired
	private CacheManager ehcacheManager;
	
	private static final String CACHE_NAME = "contentInfoCache";
	
	private Cache cache;
	
	@Test
	public void normal() {

		cache = ehcacheManager.getCache(CACHE_NAME);

		String key = "foo";
		String value = "boo";

		put(key, value);
		Object result = get(key);
		System.out.println(result.toString());
		assertEquals(value, result);
	}
	

	public Object get(String key) {
		Element element = cache.get(key);
		return element.getObjectValue();
	}
	
	public void put(String key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}
	

}
