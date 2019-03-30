package com.caiyongji.bbp.utils;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Table;

public class Tools {
	public static void printShape(Table<Integer, Integer, String> table) {
		if (table == null) {
			return;
		}
		System.out.println("/**");
		for (Integer row : table.rowKeySet()) {
			System.out.println(" * " + table.row(row).values());
		}
		System.out.print("**/");
	}

	public static void shape(Table<Integer, Integer, String> table) {
		if (table == null) {
			return;
		}
		for (Integer row : table.rowKeySet()) {
			System.out.println(table.row(row).values());
		}
	}

	public static void printShape(Block block) {
		System.out.println("\nUP:");
		printShape(block.up());
		System.out.println("\nRIGHT:");
		printShape(block.right());
		System.out.println("\nDOWN:");
		printShape(block.down());
		System.out.println("\nLEFT:");
		printShape(block.left());

		System.out.println("\nMIRROR UP:");
		printShape(block.transpose(Direction.UP));
		System.out.println("\nMIRROR RIGHT:");
		printShape(block.transpose(Direction.RIGHT));
		System.out.println("\nMIRROR DOWN:");
		printShape(block.transpose(Direction.DOWN));
		System.out.println("\nMIRROR LEFT:");
		printShape(block.transpose(Direction.LEFT));
	}

	public static void printDistinctShape(Block block) {
		List<Table<Integer, Integer, String>> distinctShapes = block.distinctShapes();
		for (Table<Integer, Integer, String> shape : distinctShapes) {
			shape(shape);
			System.out.println();
		}
	}

	public static Integer min(Set<Integer> set) {
		if (set.isEmpty()) {
			return 0;
		}
		Integer m = Integer.MAX_VALUE;
		for (Integer i : set) {
			if (m >= i) {
				m = i;
			}
		}
		return m;
	}

	public static Integer max(Set<Integer> set) {
		if (set.isEmpty()) {
			return 0;
		}
		Integer m = 0;
		for (Integer i : set) {
			if (m <= i) {
				m = i;
			}
		}
		return m;
	}
}
