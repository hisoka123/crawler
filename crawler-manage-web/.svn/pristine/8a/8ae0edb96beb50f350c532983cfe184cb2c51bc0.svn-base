import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.*;

import java.net.URL;

/**
 * Created by zxz on 2015/10/30.
 */
public class APIAccessTest {

public static void main(String[] args){

    HttpClient httpclient = new HttpClient();
//    HttpMethod httpMethod = new GetMethod("http://localhost:8080/api/weibo/user?q=雷军");
    GetMethod getMethod = new GetMethod("http://news.baidu.com/ns?tn=newsrss&sr=0&cl=2&rn=20&ct=0&word=%E9%9B%B7%E5%86%9B+site:news.163.com");

    try {
        int statusCode = httpclient.executeMethod(getMethod);
        System.out.println("response=" + getMethod.getResponseBodyAsString());
    }catch(Exception e){
        e.printStackTrace();
    }
        getMethod.releaseConnection();






}
}
