package com.module.ocr.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.google.gson.JsonParser;

public class ChaoJiYingUtils {
	public static final Map<String, String> errMap;
	public static final String RESULT_SUCCESS = "0";
	
	static {
		errMap = new HashMap<String, String>();
		errMap.put("-1002", "[-1002]无此用户名	请发送正确的用户账号，注意可不能填开发者账号");
		errMap.put("-1003", "[-1003]用户名的密码出错	请发送正确的用户账号密码，点击这里找回密码");
		errMap.put("-10031", "[-10031]用户状态异常，请联系客服QQ或是客服主管");
		errMap.put("-1004", "[-1004]图片类型错误，请选用正确的码图类型，或联系软件开发商");
		errMap.put("-1005", "[-1005]无可用题分，请进入用户中心在线充值，或绑定微信账号送1000题分");
		errMap.put("-10061", "[-10061]不是有效的图片文件，请上传正确的图像文件，或联系软件开发商");
		errMap.put("-100612", "[-100612]base64字符解析异常，请上传正确的base64字符，或联系软件开发商");
		errMap.put("-10062", "[-10062]文件超大 1024k，请上传合适大小的图像文件，或联系软件开发商");
		errMap.put("-10064", "[-10064]图片无法被识别，请跳过，换下一张图像文件");
		errMap.put("-2001", "[-2001]上传图片出错，程序上传问题，请联系软件开发商");
		errMap.put("-2003", "[-2003]存储图片时出现异常，服务器端问题");
		errMap.put("-3001", "[-3001]系统异常系统问题，请跳过，换下一张图像文件");
		errMap.put("-3002", "[-3002]系统超时系统问题，请跳过，换下一张图像文件");
		errMap.put("-1011", "[-1011]图片ID异常，请发送正确的图像ID，或联系软件开发商");
		errMap.put("-1012", "[-1012]无此图片ID，请发送正确的图像ID，或联系软件开发商");
		errMap.put("-10124", "[-10124]图片ID的时效不符，请在三分钟内发送报错信息，或联系软件开发商");
		errMap.put("-1013", "[-1013]软件报错异常，请联系软件开发商");
		errMap.put("-1021", "[-1021]用户名的组成只能是字母和数字，请发送字母与数字的组合");
		errMap.put("-1022", "[-1022]用户名的字符长应该界于6至16之间，请把要注册的用户名长度限定在6至16位之间");
		errMap.put("-1023", "[-1023]密码的字符长应该界于6至20之间，请把密码长度限定在6至20位之间");
		errMap.put("-1024", "[-1024]账号不能重复注册	请不要重复注册同一个用户账号");
		errMap.put("-1032", "[-1032]用户名和充值卡不能为空，请发送完整的参数");
		errMap.put("-1033", "[-1033]无此充值卡号，请发送正确的充值卡号");
		errMap.put("-1034", "[-1034]充值卡已被使用，请发送正确的并且未被使用的充值卡号");
	}
	
	
	public static String MD5(String s) {
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String httpRequestData(String url, String param) throws IOException {
		URL u;
		HttpURLConnection con = null;
		OutputStreamWriter osw;
		StringBuffer buffer = new StringBuffer();

		u = new URL(url);
		con = (HttpURLConnection) u.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(param);
		osw.flush();
		osw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			buffer.append(temp);
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	public static String GetScore(String username, String password) {
		String param = String.format("user=%s&pass=%s", username, password);
		String result;
		try {
			result = ChaoJiYingUtils.httpRequestData("http://code.chaojiying.net/Upload/GetScore.php", param);
		} catch (IOException e) {
			result = "未知问题";
		}
		return result;
	}
	
	public static String UserReg(String username, String password) {
		String param = String.format("user=%s&pass=%s", username, password);
		String result;
		try {
			result = ChaoJiYingUtils.httpRequestData("http://code.chaojiying.net/Upload/UserReg.php", param);
		} catch (IOException e) {
			result = "未知问题";
		}
		return result;
	}
	
	public static String UserPay(String username, String card) {
		String param = String.format("user=%s&card=%s", username, card);
		String result;
		try {
			result = ChaoJiYingUtils.httpRequestData("http://code.chaojiying.net/Upload/UserPay.php", param);
		} catch (IOException e) {
			result = "未知问题";
		}
		return result;
	}
	
	public static String ReportError(String username, String password, String softid, String id) {
		String param = String.format("user=%s&pass=%s&softid=%s&id=%s", username, password, softid, id);
		String result;
		try {
			result = ChaoJiYingUtils.httpRequestData("http://code.chaojiying.net/Upload/ReportError.php", param);
		} catch (IOException e) {
			result = "未知问题";
		}
		return result;
	}
	
	public static String httpPostImage(String url, String param, byte[] data) throws IOException {
		long time = (new Date()).getTime();
		URL u = null;
		HttpURLConnection con = null;
		String boundary = "----------" + MD5(String.valueOf(time));
		String boundarybytesString = "\r\n--" + boundary + "\r\n";
		OutputStream out = null;
		
		u = new URL(url);
		
		con = (HttpURLConnection) u.openConnection();
		con.setRequestMethod("POST");
		//con.setReadTimeout(60000);   
		con.setConnectTimeout(60000);
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setUseCaches(true);
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		
		out = con.getOutputStream();
			
		for (String paramValue : param.split("[&]")) {
			out.write(boundarybytesString.getBytes("UTF-8"));
			String paramString = "Content-Disposition: form-data; name=\"" + paramValue.split("[=]")[0] + "\"\r\n\r\n" + paramValue.split("[=]")[1];
			out.write(paramString.getBytes("UTF-8"));
		}
		out.write(boundarybytesString.getBytes("UTF-8"));

		String paramString = "Content-Disposition: form-data; name=\"userfile\"; filename=\"" + "chaojiying_java.gif" + "\"\r\nContent-Type: application/octet-stream\r\n\r\n";
		out.write(paramString.getBytes("UTF-8"));
		
		out.write(data);
		
		String tailer = "\r\n--" + boundary + "--\r\n";
		out.write(tailer.getBytes("UTF-8"));

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			buffer.append(temp);
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
	public static String PostPic(String username, String password, String softid, String codetype, String len_min, String time_add, String str_debug, String filePath) {
		String result = "";
		String param = String.format("user=%s&pass=%s&softid=%s&codetype=%s&len_min=%s&time_add=%s&str_debug=%s", username, password, softid, codetype, len_min, time_add, str_debug);
		try {
			File f = new File(filePath);
			if (null != f) {
				int size = (int) f.length();
				byte[] data = new byte[size];
				FileInputStream fis = new FileInputStream(f);
				fis.read(data, 0, size);
				if(null != fis) fis.close();
				
				if (data.length > 0) {
					result = ChaoJiYingUtils.httpPostImage("http://upload.chaojiying.net/Upload/Processing.php", param, data);
				}
			}
		} catch(Exception e) {
			result = "未知问题";
		}
		return result;
	}
	
	public static String PostPic(String username, String password, String softid, String codetype, String len_min, String time_add, String str_debug, byte[] byteArr) {
		String result = "";
		String param = String.format("user=%s&pass=%s&softid=%s&codetype=%s&len_min=%s&time_add=%s&str_debug=%s", username, password, softid, codetype, len_min, time_add, str_debug);
		try {
			result = ChaoJiYingUtils.httpPostImage("http://upload.chaojiying.net/Upload/Processing.php", param, byteArr);
		} catch(Exception e) {
			result = "未知问题";
		}
		return result;
	}
	
	public static String getErrNo(String result) {
		if (StringUtils.isEmpty(result)) {
			return result;
		}
		return new JsonParser().parse(result).getAsJsonObject().get("err_no").getAsString();
	}
	
	public static String getErrMsg(String errNo) {
		return errMap.get(errNo);
	}
}
