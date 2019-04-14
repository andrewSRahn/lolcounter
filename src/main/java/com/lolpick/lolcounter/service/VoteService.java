package com.lolpick.lolcounter.service;

import java.util.List;

import com.lolpick.lolcounter.daoimpl.VoteDaoImpl;
import com.lolpick.lolcounter.entity.Vote;

public class VoteService {
	private static VoteDaoImpl daoimpl = new VoteDaoImpl();
	
	public static boolean createBlocks(List<Vote> blocks) {
		for (Vote block: blocks)
			if (!daoimpl.create(block))
				return false;
		
		return true;
	}

	public static boolean createBlocks(Vote vote) {
		return daoimpl.create(vote);
	}
}
