package com.caiyongji.bbp.utils;

import java.util.List;

import com.google.common.collect.Table;

public interface BlockBase {
	Table<Integer, Integer, String> up();

	Table<Integer, Integer, String> right();

	Table<Integer, Integer, String> down();

	Table<Integer, Integer, String> left();
	
	String identification();
	
	List<Table<Integer, Integer, String>> distinctShapes();
}
