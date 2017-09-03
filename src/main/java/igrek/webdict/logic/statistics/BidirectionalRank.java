package igrek.webdict.logic.statistics;

import igrek.webdict.model.entity.Rank;

public class BidirectionalRank {
	
	private Rank simpleRank;
	
	private Rank reversedRank;
	
	public BidirectionalRank(Rank simpleRank, Rank reversedRank) {
		this.simpleRank = simpleRank;
		this.reversedRank = reversedRank;
	}
	
	public Rank getSimpleRank() {
		return simpleRank;
	}
	
	public Rank getReversedRank() {
		return reversedRank;
	}
}
