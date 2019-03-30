package com.caiyongji.bbp.test;

import com.caiyongji.bbp.utils.Backplane;
import com.caiyongji.bbp.utils.Calculator;
import com.caiyongji.bbp.utils.Tools;
import com.google.common.collect.Table;

public class TestScaling {

	public static void main(String[] args) {
		Calculator c = new Calculator();
		Backplane backplane = new Backplane();
		Table<Integer, Integer, String> baseBackplane = backplane.create();
		baseBackplane.put(3, 2, "G");
		baseBackplane.put(4, 8, "G");
		Tools.shape(baseBackplane);
		Table<Integer, Integer, String> sacledTable = c.scaleBackplane(baseBackplane);
		Tools.printShape(sacledTable);
	}

}
