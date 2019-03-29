package com.caiyongji.bbp;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
import com.caiyongji.bbp.utils.Block;
import com.caiyongji.bbp.utils.Calculator;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class Perform {
	final static Integer ThreadNum = 100;
	final static Integer partitionSize = 1000000;

	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		Calculator calculator = new Calculator();
		calculator.calculate();
	}

	/**
	 * 
	 * !!!!!!!!!!!!!PERFORMANCE ISSUE
	 * List<List<Block>> fullPermutations =
	 * Collections2.permutations(blockList).stream().collect(Collectors.toList());
	 * 
	 * 
	 */
	@Deprecated
	public static void multipleThreadsRun() {
		/**
		 * 12 kinds of colors
		 */
		List<Block> blockList = Lists.newArrayList(new A_Brown(), new B_DarkBlue(), new C_DeepGreen(), new D_Gray(),
				new E_LightBlue(), new F_LightGreen(), new G_LightPink(), new H_Orange(), new I_Pink(), new J_Purple(),
				new K_Red(), new L_Yellow());
		/**
		 * 479,001,600 permutations
		 */
		List<List<Block>> fullPermutations = Collections2.permutations(blockList).stream().collect(Collectors.toList());
		System.out.println("fullPermutations size: " + fullPermutations.size());
		List<List<List<Block>>> lists = Lists.partition(fullPermutations, partitionSize);
		ExecutorService service = Executors.newFixedThreadPool(ThreadNum);
		for (List<List<Block>> blocklists : lists) {
			service.execute(new Calculator(blocklists));
		}
		service.shutdown();
	}

}
