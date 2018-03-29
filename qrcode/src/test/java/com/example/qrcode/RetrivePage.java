package com.example.qrcode;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 网页抓取示例
 *
 * @author: gexupeng 2018/1/27 20:52
 **/
public class RetrivePage {
     private static HttpClient httpClient = new HttpClient();
     static{
         httpClient.getHostConfiguration().setProxy("192.168.0.1",9527);
     }
     public static boolean downloadPage(String path) throws IOException {
         InputStream inputStream = null;
         OutputStream outputStream = null;
         PostMethod postMethod = new PostMethod(path);
         NameValuePair[] postDate = new NameValuePair[2];
         postDate[0] = new NameValuePair("name","lietu");
         postDate[1] = new NameValuePair("password","*****");
         postMethod.addParameters(postDate);
         int stateCode = httpClient.executeMethod(postMethod);
         if(stateCode == HttpStatus.SC_OK){
             inputStream = postMethod.getResponseBodyAsStream();
             String fileName = path.substring(path.lastIndexOf('/')+1);
             outputStream = new FileOutputStream(fileName);
             int tempByte = -1;
             while((tempByte = inputStream.read())>0 ){
                outputStream.write(tempByte);
             }
             if(inputStream != null){
                 inputStream.close();
             }
             if(outputStream != null){
                 outputStream.close();
             }
             return true;
         }

         return false;
     }

     public static void main(String[] args){
         try {
             RetrivePage.downloadPage("http://www.lietu.com/");
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
