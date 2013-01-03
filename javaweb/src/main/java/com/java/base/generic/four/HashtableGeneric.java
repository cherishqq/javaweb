package com.java.base.generic.four;

import java.util.Hashtable;

//Hashtable的泛型化
public class HashtableGeneric<K, V> {
	// 创建Hashtable的泛型类对象
	public Hashtable<K, V> hashTable = new Hashtable<K, V>();

	// 创建put方法为key和value赋值
	public void put(K k, V v) {
		hashTable.put(k, v);
	}

	// 创建get方法可以根据key值获取value的值
	public V get(K k) {
		return hashTable.get(k);
	}

	public static void main(String args[]) {
		HashtableGeneric<String, String> t = new HashtableGeneric<String, String>();
		t.put("key", "Java语言");
		String s = t.get("key");
		System.out.println("根据key值获取的value的内容：\n\t" + s);
	}

}
