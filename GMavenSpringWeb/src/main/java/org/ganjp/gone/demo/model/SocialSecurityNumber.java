package org.ganjp.gone.demo.model;

import org.ganjp.gone.common.util.MaskFormat;

public final class SocialSecurityNumber {

	private final String value;
	
	public SocialSecurityNumber(String value) {
		this.value = value;
	}
	
	@MaskFormat("###-##-####")
	public String getValue() {
		return value;
	}

	public static SocialSecurityNumber valueOf(@MaskFormat("###-##-####") String value) {
		return new SocialSecurityNumber(value);
	}
	
}