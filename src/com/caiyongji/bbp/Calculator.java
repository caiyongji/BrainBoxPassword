package com.caiyongji.bbp;

import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import com.caiyongji.bbp.blocks.A_Brown;
import com.caiyongji.bbp.blocks.B_DarkBlue;
import com.caiyongji.bbp.blocks.C_DeepGreen;
import com.caiyongji.bbp.blocks.D_Gray;
import com.caiyongji.bbp.blocks.E_LightBlue;
import com.caiyongji.bbp.blocks.F_LightGreen;
import com.caiyongji.bbp.blocks.G_LightPink;
import com.caiyongji.bbp.blocks.H_Orange;
import com.caiyongji.bbp.blocks.I_Pink;
import com.caiyongji.bbp.blocks.J_Purple;
import com.caiyongji.bbp.blocks.K_Red;
import com.caiyongji.bbp.blocks.L_Yellow;
import com.caiyongji.bbp.utils.Backplane;
import com.caiyongji.bbp.utils.Block;
import com.caiyongji.bbp.utils.Direction;
import com.caiyongji.bbp.utils.Tools;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public class Calculator {
	private Long countTime = 0l;
	private DateTime start;
	private DateTime time;
	private Integer solutionCount = 0;

	public static void main(String[] args) {
		Calculator c = new Calculator();
		c.calculate();
	}

	public void calculate() {
		/**
		 * 12 kinds of colors
		 */
		List<Block> blockList = Lists.newArrayList(new A_Brown(), new B_DarkBlue(), new C_DeepGreen(), new D_Gray(),
				new E_LightBlue(), new F_LightGreen(), new G_LightPink(), new H_Orange(), new I_Pink(), new J_Purple(),
				new K_Red(), new L_Yellow());
		/**
		 * 479,001,600 permutations
		 */
		Collection<List<Block>> fullPermutations = Collections2.permutations(blockList);
		System.out.println("fullPermutations size: " + fullPermutations.size());

		/**
		 * count time
		 */
		start = new DateTime();
		System.out.println("start time: "+start.toString());
		for (List<Block> list : fullPermutations) {
			/**
			 * every block try up to 225*8 times in backplane
			 */
			Backplane backplane = new Backplane();
			Table<Integer, Integer, String> backTable = backplane.create();
			Boolean mismatchBlock = false;
			for (Block block : list) {
				if (!match(backTable, block)) {
					mismatchBlock = true;
				}
			}
			if (mismatchBlock) {
				continue;
			}else {
				Tools.shape(backTable);
				System.out.println("-------------------- the number of solutions have found: "+ solutionCount);
			}
		}
	}

	private Boolean match(Table<Integer, Integer, String> backplane, Block block) {
		String identification = block.identification();
		List<Table<Integer, Integer, String>> list = Lists.newArrayList(block.up(), block.right(), block.down(),
				block.left(), block.transpose(Direction.UP), block.transpose(Direction.RIGHT),
				block.transpose(Direction.DOWN), block.transpose(Direction.LEFT));
		for (Table<Integer, Integer, String> shape : list) {
			if (place(backplane, shape, identification)) {
				return true;
			}
		}
		return false;
	}

	private Boolean place(Table<Integer, Integer, String> backplane, Table<Integer, Integer, String> shape,
			String identification) {
		// Tools.shape(backplane);
		// Tools.printShape(shape);
		countTime++;
		if (countTime % 10000 == 0) {
			time = new DateTime();
			int cost = Minutes.minutesBetween(start, time).getMinutes();
			System.out.println("have tried to match " + countTime + " times, time now "+ time +" and "+cost+" minutes passed.");
		}
		int shapeRowSize = shape.rowKeySet().size();
		int shapeColumnSize = shape.columnKeySet().size();
		for (int i = 0; i < backplane.rowKeySet().size() - shapeRowSize + 1; i++) {
			for (int j = 0; j < backplane.columnKeySet().size() - shapeColumnSize + 1; j++) {
				Table<Integer, Integer, String> subBackplane = table4x4(backplane, i, j);
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

	private Table<Integer, Integer, String> table4x4(Table<Integer, Integer, String> backplane, int row, int column) {
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				table.put(i, j, backplane.get(row + i, column + j));
			}
		}
		return table;
	}
}
