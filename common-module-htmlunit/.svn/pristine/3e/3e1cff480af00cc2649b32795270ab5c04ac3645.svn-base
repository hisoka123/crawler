package test.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SelenuimTest { 
	
	public static String getCasperjs(String keyword) throws IOException {   
        Runtime rt = Runtime.getRuntime();   
        Process p = rt.exec(" casperjs --web-security=no --keyword="+keyword+"  neimenggu.js");//这里我的codes.js是保存在c盘下面的phantomjs目录   
        InputStream is = p.getInputStream();   
        BufferedReader br = new BufferedReader(new InputStreamReader(is));   
        StringBuffer sbf = new StringBuffer();   
        String tmp = "";   
        while((tmp = br.readLine())!=null){   
            sbf.append(tmp);   
        }   
        //System.out.println(sbf.toString());   
        return sbf.toString();   
    }   
   
    public static void main(String[] args) throws IOException {   
    	getCasperjs("伊利");   
    }   

}
