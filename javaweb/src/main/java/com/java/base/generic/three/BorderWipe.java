package com.java.base.generic.three;

import java.util.Date;

//有限界的擦拭
public class BorderWipe<T extends Date> {
	// 下面所有的T将被String所代替
	T date;

	BorderWipe(T date) {
		this.date = date;
	}

	T getOb() {
		return date;
	}

}
