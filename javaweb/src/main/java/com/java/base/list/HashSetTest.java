package com.java.base.list;

import java.util.HashSet;

/**
 *
 * @author derek.pan
 * 这个例子说明了：
 *   1：加入 HashSet 的除了 equals 要相等, hashcode 也要相等。。 否则。。
 *   2：加入相同的，不会被覆盖，会用原先的那个
 *
 */

public class HashSetTest {
	
	
	public static void main(String [] args){
		
		Obj o1 = new Obj(1,"haijin",18);
		Obj o2 = new Obj(1,"haijin",22);
		
		System.out.println( o1.equals(o2));
		
		HashSet<Obj> d = new HashSet<HashSetTest.Obj>();
		d.add(o1);
		d.add(o2);
		System.out.println(d.size());
		Obj b = d.iterator().next();
		System.out.println(b.toString());
	}
	
	/**
	 *  这里要加 static , 否则会报错
	 * @author derek.pan
	 *
	 */
	public static class Obj {
		
		private int id;
		private String name;
		private int age;
		
		
		public Obj(int id, String name, int age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return this.name.hashCode();
//			return super.hashCode();
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.id + ";" + "name:" + this.name + ";" + "age:" + this.age;
//			return super.toString();
		}
		@Override
		public boolean equals(Object obj) {
			
			if( this == obj ) return true;
			if ( obj == null) return false;
			
			if( getClass() != obj.getClass() ) return false;
			Obj o = (Obj)obj;
			if(this.id == o.id && this.name.equals(o.name) ) return true;
			
			return super.equals(obj);
		}
		
	}

}
