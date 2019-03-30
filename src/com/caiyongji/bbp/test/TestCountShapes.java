package com.caiyongji.bbp.test;

import java.util.List;

import com.caiyongji.bbp.blocks.*;
import com.caiyongji.bbp.utils.Block;
import com.google.common.collect.Lists;

public class TestCountShapes {

	public static void main(String[] args) {
		List<Block> blockList = Lists.newArrayList(new A_Brown(), new B_DarkBlue(), new C_DeepGreen(), new D_Gray(),
				new E_LightBlue(), new F_LightGreen(), new G_LightPink(), new H_Orange(), new I_Pink(), new J_Purple(),
				new K_Red(), new L_Yellow());
		int count =0;
		for (Block block : blockList) {
			count+=block.distinctShapes().size();
		}
		System.out.println(count);
	}

}
