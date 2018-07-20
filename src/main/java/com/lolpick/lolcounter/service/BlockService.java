package com.lolpick.lolcounter.service;

import java.util.List;

import com.lolpick.lolcounter.daoimpl.BlockDaoImpl;
import com.lolpick.lolcounter.entity.Block;

public class BlockService {
	private static BlockDaoImpl daoimpl = new BlockDaoImpl();
	
	public static boolean createBlocks(List<Block> blocks) {
		for (Block block: blocks)
			if (!daoimpl.create(block))
				return false;
		
		return true;
	}
}
