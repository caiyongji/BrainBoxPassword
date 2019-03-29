package com.caiyongji.bbp.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public abstract class Block implements BlockBase {
	public Table<Integer, Integer, String> randomPick() {
		return up();
	}

	private Table<Integer, Integer, String> block4x4() {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				table.put(i, j, "0");
			}
		}
		return table;
	}

	private Table<Integer, Integer, String> transpose(Table<Integer, Integer, String> old1) {
		Table<Integer, Integer, String> new1 = block4x4();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (old1.get(i, j) == "1") {
					if (j == 0) {
						new1.put(i, 3, "1");
					} else if (j == 1) {
						new1.put(i, 2, "1");
					} else if (j == 2) {
						new1.put(i, 1, "1");
					} else if (j == 3) {
						new1.put(i, 0, "1");
					}
				}
			}
		}
		return new1;

	}

	public Table<Integer, Integer, String> transpose(Direction d) {
		switch (d) {
		case UP:
			return transpose(up());
		case RIGHT:
			return transpose(right());
		case DOWN:
			return transpose(down());
		case LEFT:
			return transpose(left());
		}
		return null;
	}
}
