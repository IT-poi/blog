package com.cuit.boke.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {
	public static String getMd5(String data)
	{
	    return DigestUtils.md5Hex(data);
	}
}
