package com.lohas.api.model;

import com.lohas.api.model.common.AutoInstruction;


public class GetAutoInstructionResponse extends CommonResponse {

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
