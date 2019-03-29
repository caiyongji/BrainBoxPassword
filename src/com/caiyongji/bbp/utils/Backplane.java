package com.caiyongji.bbp.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Backplane {
	private final Integer EXPAND = 4;

	/**
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	**/
	public Table<Integer, Integer, String> create() {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < 10 + 2 * EXPAND; i++) {
			for (int j = 0; j < 10 + 2 * EXPAND; j++) {
				if (i >= EXPAND && i < 5 + EXPAND) {
					if (i + j >= 5 - 1 + 2 * EXPAND && i + j < 5 - 1 + 2 * i + 1) {
						table.put(i, j, "1");
					} else {
						table.put(i, j, "0");
					}
				} else if (i >= 5 + EXPAND && i < 10 + EXPAND) {
					if (i + j <= 10 + (5 - 1 + 2 * EXPAND) && i + j >= 2 * i - 4) {
						table.put(i, j, "1");
					} else {
						table.put(i, j, "0");
					}
				} else {
					table.put(i, j, "0");
				}
			}
		}
		return table;
	}
}
