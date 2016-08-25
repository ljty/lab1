package com.ifox.android.lab.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 使用String输出
 */
public class StreamUtils {

	public static String streamToString(InputStream in){
		String result ="";
		try{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while (  (length =  in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
				out.flush();
			}
			result = new String(out.toByteArray(),"utf-8");
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
