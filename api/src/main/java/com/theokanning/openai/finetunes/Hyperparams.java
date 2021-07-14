package com.theokanning.openai.finetunes;

import lombok.Data;

@Data
class Hyperparams {
	
	public Integer batchSize;
	
	public Double leaderningRateMultiplier;
	
	public Integer nEpochs;
	
	public Double promptLossWeight;
	
	public Boolean usePacking;
	
}
