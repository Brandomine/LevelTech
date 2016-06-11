package com.brandomine.tech.common.utils;

import java.text.DecimalFormat;

public class DoubleConverter {
	public String doubleToString(double value){
		DecimalFormat dec = new DecimalFormat("################################");
		dec.setMaximumFractionDigits(-1);
		String output = dec.format(value).toString();
		return output;
	}
}
