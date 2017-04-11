package com.lohas.api.model;

import com.lohas.api.model.common.Account;
import com.lohas.api.model.common.AutoInstruction;
import com.lohas.api.model.common.Bank;
import com.lohas.api.model.common.Banker;
import com.lohas.api.model.common.Customer;

public class UpdateAutoInstructionResponse extends CommonResponse {

	/*
	 * AutoInstruction of given account Id
	 */
	private AutoInstruction autoInstrution;

	public AutoInstruction getAutoInstrution() {
		return autoInstrution;
	}

	public void setAutoInstrution(AutoInstruction autoInstrution) {
		this.autoInstrution = autoInstrution;
	}
	
	
}
