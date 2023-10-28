package com.iacsd.core;

public enum Color {
	WHITE(5000), BLACK(10000), BLUE(7500), SILVER(8000), RED(15000);
	private double additionalCost;
	private Color(double additionalCost)
	{
		//super(name,ordinal);
		this.additionalCost=additionalCost;
	}
	public double getAdditionalCost() {
		return additionalCost;
	}
	public void setAdditionalCost(double additionalCost) {
		this.additionalCost = additionalCost;
	}
	
}
