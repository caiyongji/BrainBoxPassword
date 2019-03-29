package com.caiyongji.bbp.test;

import java.util.List;

import com.google.common.collect.Lists;

public class TestGuavaPartition {

	public static void main(String[] args) {
		List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
		List<List<Integer>> subSets = Lists.partition(intList, 3);
		for (List<Integer> list : subSets) {
			System.out.print(list);
		}
	}

}
