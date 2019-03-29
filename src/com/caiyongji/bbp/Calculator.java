package com.caiyongji.bbp;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

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
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public class Calculator {

	public static void main(String[] args) {
		ConcurrentLinkedDeque<Block> queue = new ConcurrentLinkedDeque<>();
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
		
		/**
		 * every block try up to 225*8 times in backplane
		 */
		Backplane backplane = new Backplane();
		Table<Integer, Integer, String> backTable = backplane.create();
		
		for (List<Block> list : fullPermutations) {
			for (Block block : list) {
				System.out.print(block.identification() + ",");
			}
			System.out.println();
		}
	}
}
