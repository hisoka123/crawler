package com.crawler.htmlparser;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES加密解密
 */
public class EncryptAndDecryptUtil {
	private static Base64 base64 = new Base64();
	public static final String KEY = "秘钥就是不告诉你，自己猜去吧哈哈";
	public static final String SEARATOR = "---";
	
	/**
	 * 加密--把加密后的byte数组先进行二进制转16进制在进行base64编码
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			throw new IllegalArgumentException("Argument sKey is null.");
		}
		if (sKey.length() != 16) {
			throw new IllegalArgumentException(
					"Argument sKey'length is not 16.");
		}
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
		String tempStr = parseByte2HexStr(encrypted);
		
		return new String(base64.encode(tempStr.getBytes("UTF-8")), "UTF-8");
	}

	/**
	 * 解密--先 进行base64解码，在进行16进制转为2进制然后再解码
	 * 
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String sSrc, String sKey) throws Exception {

		if (sKey == null) {
			throw new IllegalArgumentException("499");
		}
		if (sKey.length() != 16) {
			throw new IllegalArgumentException("498");
		}

		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);

		byte[] encrypted1 = base64.decode(sSrc.getBytes("utf-8"));

		String tempStr = new String(encrypted1, "utf-8");
		encrypted1 = parseHexStr2Byte(tempStr);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original, "utf-8");
		return originalString;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	/**
	 * 随机生成n位字母数字
	 * @param length
	 * @return
	 */
	public static String getStringRandom(int length) {  
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if("char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }

	public static void main(String[] args) throws Exception {
		// 需要加密的字串
		String plaintext = "这是明文";
		// 加密
		String enString = encrypt(plaintext, KEY);
		System.out.println("加密后的字串是：" + enString);
		// 解密
		String DeString = decrypt(enString, KEY);
		System.out.println("解密后的字串是：" + DeString);
	}
}
