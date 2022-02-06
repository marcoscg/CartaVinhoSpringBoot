package com.mardeveloper.wine.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final int expires;
	private final String tokentype;
	private final String jwttoken;


	public JwtResponse(int expires, String tokentype, String jwttoken) {
		super();
		this.expires = expires;
		this.tokentype = tokentype;
		this.jwttoken = jwttoken;
	}

	public String getAccess_token() {
		return this.jwttoken;
	}

	public int getExpires_in() {
		return expires;
	}

	public String getToken_type() {
		return tokentype;
	}
	
	
}