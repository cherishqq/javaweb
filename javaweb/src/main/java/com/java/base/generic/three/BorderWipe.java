package com.java.base.generic.three;

import java.util.Date;

//���޽�Ĳ���
public class BorderWipe<T extends Date> {
	// �������е�T����String������
	T date;

	BorderWipe(T date) {
		this.date = date;
	}

	T getOb() {
		return date;
	}

}
