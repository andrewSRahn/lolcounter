package com.lolpick.lolcounter.service;

import java.util.List;

import com.lolpick.lolcounter.daoimpl.BlockDaoImpl;
import com.lolpick.lolcounter.entity.Vote;

public class BlockService {
	private static BlockDaoImpl daoimpl = new BlockDaoImpl();
	
	public static boolean createBlocks(List<Vote> blocks) {
		for (Vote block: blocks)
			if (!daoimpl.create(block))
				return false;
		
		return true;
	}
}
