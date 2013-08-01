package com.java.base.generic.three;

//无限界的擦拭
public class UnBorderWipe<T> {
	// 下面所有的T将被Object所代替
	T ob;

	UnBorderWipe(T ob) {
		this.ob = ob;
	}

	T getOb() {
		return ob;
	}

}
