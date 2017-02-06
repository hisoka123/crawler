package com.crawlermanage.doc.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/** 
 * @author hmy
 * @date：Jan 13, 2016 
 * 
 */
public class DocTest {
    
	
	@Test
	public void testpath(){
		
               String cssFilePath1="D:\\eclipse-workspaces\\Strom_crawler_manager\\scm_19\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\crawler-manage-web\\static\\css\\bootstrap.min.css"; 
		   
               
               File file=new File(cssFilePath1);
               
               System.out.println(file.getAbsolutePath());
               System.out.println(file.getParent());
               System.out.println(file.exists());
		
		
	}
    
    
    @Test
    public void mergeFile() throws Exception{
    	   String saveDirPath="d:/SRDP_SITE_API_PDF/新浪微博";
    	   String filename="d:/text.pdf";
    	   
    	   File files=new File(saveDirPath);
    	   String[] filesPath=files.list();
    	   
    	   
    	   Document document=new Document();
    	   PdfCopy copy=new PdfCopy(document, new FileOutputStream(filename));
		
		
    	   document.open();
    	   
    	   int page=1;
    	   
    	   ArrayList<HashMap<String, Object>> outlines = new ArrayList<HashMap<String, Object>>();
    	   
    	   //章书签
    	   HashMap<String, Object> helloworld = new HashMap<String, Object>();
    	   ArrayList<HashMap<String, Object>> kids = new ArrayList<HashMap<String, Object>>();
    	   for(int i=0;i<filesPath.length;i++){
    		       
    		         PdfReader reader=null;
						System.out.println(saveDirPath+"/"+filesPath[i]+"****"+filesPath[i].split("\\.")[0].split("_")[1]);
						reader = new PdfReader(saveDirPath+"/"+filesPath[i]);
						copy.addDocument(reader);
    		         
    		         if(i==0){
    		             helloworld.put("Title", "新浪微博");
    		            // helloworld.put("Action", "GoTo");
    		            // helloworld.put("Page", String.format("%d Fit", page));
    		   
    		             outlines.add(helloworld);
    		         }
    		         
    		         
    		        	 HashMap<String, Object> kid = new HashMap<String, Object>();
    		             kid.put("Title",filesPath[i].split("\\.")[0].split("_")[1]);
    		             kid.put("Action", "GoTo");
    		             kid.put("Page", String.format("%d Fit", page));
    		        	 kids.add(kid);
    		        	 
    		         
    		        
    		         if(i==(filesPath.length-1)){
    		        	    helloworld.put("Kids",kids);
    		         }
    		         page += reader.getNumberOfPages();
    		   
    		   
    		   
    	   }//for files.list().length
    	   
    	   copy.setOutlines(outlines);
    	   document.close();
    	   
    	   
    }
    
    
   
    
    
    
	
}
