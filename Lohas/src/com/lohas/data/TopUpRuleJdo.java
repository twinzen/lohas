package com.lohas.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class TopUpRuleJdo {

	/*
	 * Top Up Rule ID
	 * Primary key
	 */
	@Id
	@Index
	private Long topUpRuleId;
	
	/*
	 * Frequency
	 * How frequency to top up? Weekly? Monthly?
	 */
	private String frequency;
	
	/*
	 * Top Up Day
	 * Which day top up?
	 * Weekly=Mon, Tue, Wed...
	 * Monthly=1st, 2nd, 3rd.... 30th?
	 */
	@Index
	private String topUpDay;
	
	/*
	 * Account ID
	 * This rule is belong to which account?
	 */
	@Index
	private Long accountId;
	
}
