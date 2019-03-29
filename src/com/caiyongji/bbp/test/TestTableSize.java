package com.caiyongji.bbp.test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TestTableSize {

	public static void main(String[] args) {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				table.put(i, j, "0");
			}
		}
		System.out.println(table.rowKeySet().size());
	}
	
}
