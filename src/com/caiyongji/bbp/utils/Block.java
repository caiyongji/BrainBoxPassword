package com.caiyongji.bbp.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public abstract class Block implements BlockBase {
	public Table<Integer, Integer, String> randomPick() {
		return up();
	}

	private Table<Integer, Integer, String> transpose(Table<Integer, Integer, String> old1) {
		Table<Integer, Integer, String> new1 = HashBasedTable.create();
		int row = old1.rowKeySet().size();
		int column = old1.columnKeySet().size();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				new1.put(i, j, "0");
			}
		}
		transpose(old1, new1, row, column);
		return new1;

	}

	private void transpose(Table<Integer, Integer, String> old1, Table<Integer, Integer, String> new1, int row,
			int column) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (old1.get(i, j) == "1") {
					if (column == 4) {
						if (j == 0) {
							new1.put(i, 3, "1");
						} else if (j == 1) {
							new1.put(i, 2, "1");
						} else if (j == 2) {
							new1.put(i, 1, "1");
						} else if (j == 3) {
							new1.put(i, 0, "1");
						}
					} else if (column == 3) {
						if (j == 0) {
							new1.put(i, 2, "1");
						} else if (j == 1) {
							new1.put(i, 1, "1");
						} else if (j == 2) {
							new1.put(i, 0, "1");
						}
					} else if (column == 2) {
						if (j == 0) {
							new1.put(i, 1, "1");
						} else if (j == 1) {
							new1.put(i, 0, "1");
						}
					}else if (column == 1) {
							if (j == 0) {
								new1.put(i, 0, "1");
							}
						}
				}
			}
		}

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
