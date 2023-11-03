package com.ssafy.enjoytrip.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class PwdHash {
	public String getHasing(String id, String password) {
		String tmp = password + id;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(tmp.getBytes());
			return BytetoString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "0";
	}

	private String BytetoString(byte[] tmp) {
		StringBuilder sb = new StringBuilder();
		for (byte a : tmp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
}
