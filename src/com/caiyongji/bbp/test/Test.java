package com.caiyongji.bbp.test;

import com.caiyongji.bbp.blocks.A_Brown;
import com.caiyongji.bbp.utils.Block;
import com.caiyongji.bbp.utils.Tools;

public class Test {

	public static void main(String[] args) {
		Block b = new A_Brown();
		Tools.printShape(b);
//		Tools.printShape(new Backplane().create());
	}

}
