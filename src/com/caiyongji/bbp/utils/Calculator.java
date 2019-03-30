package com.caiyongji.bbp.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import com.caiyongji.bbp.blocks.A_Brown;
import com.caiyongji.bbp.blocks.B_DarkBlue;
import com.caiyongji.bbp.blocks.C_DeepGreen;
import com.caiyongji.bbp.blocks.E_LightBlue;
import com.caiyongji.bbp.blocks.F_LightGreen;
import com.caiyongji.bbp.blocks.G_LightPink;
import com.caiyongji.bbp.blocks.H_Orange;
import com.caiyongji.bbp.blocks.I_Pink;
import com.caiyongji.bbp.blocks.J_Purple;
import com.caiyongji.bbp.blocks.K_Red;
import com.caiyongji.bbp.blocks.L_Yellow;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public class Calculator extends Thread {
	private Long countTime = 0l;
	private DateTime start;
	private DateTime time;
	private Integer solutionCount = 0;
	private List<List<Block>> blocklists;

	public Calculator(List<List<Block>> blocklists) {
		this.blocklists = blocklists;
	}

	public Calculator() {
	}

	@Override
	public void run() {
		calculate(blocklists);
	}

	public void calculate() {

		/**
		 * 12 kinds of colors
		 */
		List<Block> blockList = Lists.newArrayList(new A_Brown(), new B_DarkBlue(), new C_DeepGreen(), /*
																										 * new D_Gray(),
																										 */
				new E_LightBlue(), new F_LightGreen(), new G_LightPink(), new H_Orange(), new I_Pink(), new J_Purple(),
				new K_Red(), new L_Yellow());
		/**
		 * 479,001,600 permutations * 66 shapes
		 */
		// 39916800 permutations * 62 shapes
		Collection<List<Block>> fullPermutations = Collections2.permutations(blockList);
		System.out.println("fullPermutations size: " + fullPermutations.size());
		/**
		 * count time
		 */
		start = new DateTime();
		System.out.println("start time: " + start.toString());
		int temp = 0;
		for (List<Block> list : fullPermutations) {
			temp++;
			if (temp > 3) {
				break;
			}
			Backplane backplane = new Backplane();
			Table<Integer, Integer, String> baseBackplane = backplane.create();
			Table<Integer, Integer, String> sacledTable = scaleBackplane(baseBackplane);
			for (Block block : list) {
				sacledTable = scaleBackplane(sacledTable);
				Tools.shape(sacledTable);
				match(baseBackplane, sacledTable, block);
//				Tools.shape(baseBackplane);
			}
			if (!baseBackplane.containsValue("1")) {
				System.out.println("-------------------- the number of solutions have found: " + solutionCount);
			}
		}
	}

	public Table<Integer, Integer, String> scaleBackplane(Table<Integer, Integer, String> baseBackplane) {
		Set<Integer> iSet = new HashSet<>();
		Set<Integer> jSet = new HashSet<>();
		for (int i = 0; i < baseBackplane.rowKeySet().size(); i++) {
			for (int j = 0; j < baseBackplane.columnKeySet().size(); j++) {
				if ("1".equals(baseBackplane.get(i, j))) {
					iSet.add(i);
					jSet.add(j);
				}
			}
		}
		Integer iMin = Tools.min(iSet);
		Integer iMax = Tools.max(iSet);
		Integer jMin = Tools.min(jSet);
		Integer jMax = Tools.max(jSet);
		Integer row = iMax - iMin + 1;
		Integer column = jMax - jMin + 1;
		Table<Integer, Integer, String> scaledBackplane = HashBasedTable.create();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				scaledBackplane.put(i, j, "0");
			}
		}
		for (int i = 0; i < baseBackplane.rowKeySet().size(); i++) {
			for (int j = 0; j < baseBackplane.columnKeySet().size(); j++) {
				if ("1".equals(baseBackplane.get(i, j))) {
					scaledBackplane.put(i - iMin, j - jMin, "1");
				}
			}
		}
		// System.out.println(iSet);
		// System.out.println("min: " + Tools.min(iSet));
		// System.out.println("max: " + Tools.max(iSet));
		// System.out.println(jSet);
		// System.out.println("min: " + Tools.min(jSet));
		// System.out.println("max: " + Tools.max(jSet));
		return scaledBackplane;
	}

	public void calculate(List<List<Block>> blocklists) {

		/**
		 * count time
		 */
		start = new DateTime();
		System.out.println("start time: " + start.toString());
		for (List<Block> list : blocklists) {
			/**
			 * every block try up to 225*8 times in backplane
			 */
			Backplane backplane = new Backplane();
			Table<Integer, Integer, String> baseBackplane = backplane.create();
			for (Block block : list) {
				Table<Integer, Integer, String> scaledBackplane = scaleBackplane(baseBackplane);
				match(baseBackplane, scaledBackplane, block);
			}
			if (!baseBackplane.containsValue("1")) {
				Tools.shape(baseBackplane);
				System.out.println("-------------------- the number of solutions have found: " + solutionCount);
			}
		}
	}

	private Boolean match(Table<Integer, Integer, String> backplane, Table<Integer, Integer, String> scaledBackplane,
			Block block) {
		String identification = block.identification();
		List<Table<Integer, Integer, String>> list = block.distinctShapes();
		for (Table<Integer, Integer, String> shape : list) {
			System.out.println("try shape: ");
			Tools.shape(shape);
//			if (containsAndReplace(backplane, shape, identification)) {
			//TODO replace back
			if (containsAndReplace(scaledBackplane, shape, identification)) {
//				Tools.shape(scaledBackplane);
				return true;
			}
		}
		return false;
	}

	private Boolean containsAndReplace(Table<Integer, Integer, String> backplane, Table<Integer, Integer, String> shape,
			String identification) {
		// Tools.shape(backplane);
		// Tools.printShape(shape);
		countTime++;
		if (countTime % 100000 == 0) {
			time = new DateTime();
			int cost = Minutes.minutesBetween(start, time).getMinutes();
			System.out.println("have tried to match " + countTime + " times, time now " + time + " and " + cost
					+ " minutes passed.");
		}
		int shapeRowSize = shape.rowKeySet().size();
		int shapeColumnSize = shape.columnKeySet().size();
		for (int i = 0; i < backplane.rowKeySet().size() - shapeRowSize + 1; i++) {
			for (int j = 0; j < backplane.columnKeySet().size() - shapeColumnSize + 1; j++) {
				Table<Integer, Integer, String> subBackplane = partTableBackplane(backplane, shape, i, j);
				// System.out.println("------------------------------------------------------------");
				// Tools.shape(subBackplane);
				if (matchSubBackplane(subBackplane, shape)) {
					markBackplane(backplane, shape, i, j, identification);
					return true;
				}
			}
		}
		return false;
	}

	private Boolean matchSubBackplane(Table<Integer, Integer, String> subBackplane,
			Table<Integer, Integer, String> shape) {
		int shapeRowSize = shape.rowKeySet().size();
		int shapeColumnSize = shape.columnKeySet().size();

		for (int k = 0; k < shapeRowSize; k++) {
			for (int l = 0; l < shapeColumnSize; l++) {

				if ("1".equals(shape.get(k, l)) && !shape.get(k, l).equals(subBackplane.get(k, l))) {
					return false;
				}
			}
		}
		return true;
	}

	private void markBackplane(Table<Integer, Integer, String> backplane, Table<Integer, Integer, String> shape,
			Integer row, Integer column, String identification) {
		for (int i = 0; i < shape.rowKeySet().size(); i++) {
			for (int j = 0; j < shape.columnKeySet().size(); j++) {
				if ("1".equals(shape.get(i, j))) {
					backplane.put(row + i, column + j, identification);
				}
			}
		}
	}

	private Table<Integer, Integer, String> partTableBackplane(Table<Integer, Integer, String> backplane,
			Table<Integer, Integer, String> shape, int row, int column) {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < shape.rowKeySet().size(); i++) {
			for (int j = 0; j < shape.columnKeySet().size(); j++) {
				table.put(i, j, backplane.get(row + i, column + j));
			}
		}
		return table;
	}
}
