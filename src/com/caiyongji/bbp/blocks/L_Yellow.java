package com.caiyongji.bbp.blocks;

import java.util.List;

import com.caiyongji.bbp.utils.Block;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public class L_Yellow extends Block {

	@Override
	public Table<Integer, Integer, String> up() {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				if (i==0&&j==0) {
					table.put(i, j, "1");
				}else if (i==0&&j==2) {
					table.put(i, j, "1");
				}else if (i==1&&j>=0&&j<=2) {
					table.put(i, j, "1");
				}else {
					table.put(i, j, "0");
				}
			}
		}
		return table;
	}

	@Override
	public Table<Integer, Integer, String> right() {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if (i==0&&j>=0&&j<=1) {
					table.put(i, j, "1");
				}else if (i==1&&j==0) {
					table.put(i, j, "1");
				}else if (i==2&&j>=0&&j<=1) {
					table.put(i, j, "1");
				}else {
					table.put(i, j, "0");
				}
			}
		}
		return table;
	}

	@Override
	public Table<Integer, Integer, String> down() {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				if (i==0&&j>=0&&j<=2) {
					table.put(i, j, "1");
				}else if (i==1&&j==0) {
					table.put(i, j, "1");
				}else if (i==1&&j==2) {
					table.put(i, j, "1");
				}else {
					table.put(i, j, "0");
				}
			}
		}
		return table;
	}

	@Override
	public Table<Integer, Integer, String> left() {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if (i==0&&j>=0&&j<=1) {
					table.put(i, j, "1");
				}else if (i==1&&j==1) {
					table.put(i, j, "1");
				}else if (i==2&&j>=0&&j<=1) {
					table.put(i, j, "1");
				}else {
					table.put(i, j, "0");
				}
			}
		}
		return table;
	}

	@Override
	public String identification() {
		return "L";
	}

	@Override
	public List<Table<Integer, Integer, String>> distinctShapes() {
		return Lists.newArrayList(up(),right(),down(),left());
	}

}
