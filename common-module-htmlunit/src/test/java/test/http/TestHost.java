package test.http;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestHost {
	
	  public static void main(String[] args)  
      {  
              String IP = null;  
              String host = null;  

              try  
              {  
                      InetAddress ia = InetAddress.getLocalHost();   
                      host = ia.getHostName();//获取计算机主机名  
                      IP= ia.getHostAddress();//获取计算机IP  
              }  
              catch(UnknownHostException e)  
              {  
                      e.printStackTrace();  
              }  

              System.out.println(host);  
              System.out.println(IP);  
      }  

}
