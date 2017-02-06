package com.crawlermanage.service.doc;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.module.dao.entity.doc.Site;
import com.module.dao.entity.doc.WikiDocBase;
import com.module.dao.repository.doc.ExportPDFResult;
import com.module.dao.repository.doc.SiteRepository;
import com.module.dao.repository.doc.WikiDocBaseRepository;
import com.module.dao.repository.doc.WikiDocContentRepository;


/**
 * 
 * @author hmy
 *
 */
@Component("exportPDFService")
public class ExportPDFService {

	   private static final Logger log=LoggerFactory.getLogger(ExportPDFService.class);
	   
	   private static final String runPath=Thread.currentThread().getContextClassLoader().getResource("").toString();
	  
	   
	   //本地
	  /* private static final String path=runPath.split("file:/")[1];
	   private static final String cssFilePath=new File(new File(new File(runPath).getParent()).getParent())
	                                               .getAbsolutePath().split("file:")[1].substring(1)
	                                            +"/static/css/bootstrap.min.css";
	   */
	   
	   //aws
	   private static final String path=runPath.split("file:")[1];//aws
       private static final String cssFilePath="/"
	                                            +new File(new File(new File(runPath).getParent()).getParent())
                                                     .getAbsolutePath().split("file:")[1].substring(1)
    		                                    +"/static/css/bootstrap.min.css";
             
	   
	   @Autowired
	   private SiteRepository siteRepository;
	   
	   @Autowired
	   private WikiDocBaseRepository wikiDocBaseRepository;
	   
	   @Autowired
	   private WikiDocContentRepository wikiDocContentRepository;
	   
	   
	   //导出单个pdf文件
	   public ExportPDFResult exportSinglePDF(Long wikiDocBase_id){
		   
		   
		      WikiDocBase wikiDocBase=wikiDocBaseRepository.findById(wikiDocBase_id);
	          Site site=siteRepository.findById(wikiDocBase.getSite_id());
	       
	          String timeStamp=new Date().getTime()+"";
	          String pdfName=site.getName()+"_"+wikiDocBase.getTitle()+"_"+timeStamp+".pdf";
	          String saveFilePath=path+"/"+pdfName;
	          log.info("导出当前文档saveFilePath:"+saveFilePath);
	          String content=wikiDocContentRepository.findByWdb_id(wikiDocBase_id).getContent();
	          
	          ExportPDFResult result=new ExportPDFResult();
	          if(createSinglePDF(content,saveFilePath)){
	        	       result.setExportResult(1);
	        	       result.setExportMessage(saveFilePath);
	        	       result.setExportPDFName(pdfName);
	          }else{
	        	       result.setExportResult(0);
	        	       result.setExportMessage("生成pdf文件失败! \n ***"+runPath+" *** \n"+path+" *** \n"+saveFilePath);
	          }

	          
	          return result;
		     
	   }
	   
	   
	   public ExportPDFResult bulkExportPDF(String content){
		   
		    
		      String tempDirName=new Date().getTime()+"";
		      String pdfName="上容数据平台接口文档_"+tempDirName+".pdf";
		      String destFilePath=path+"/"+pdfName;
		   
		      
		      log.info("批量导出destFilePath:"+destFilePath);
		      
		      ExportPDFResult result=new ExportPDFResult();
	          if(createSinglePDF(content,destFilePath)){
	        	       result.setExportResult(1);
	        	       result.setExportMessage(destFilePath);
	        	       result.setExportPDFName(pdfName);
	          }else{
	        	       result.setExportResult(0);
	        	       result.setExportMessage("批量导出pdf文件失败!");
	          }
		   
		      return result;
	   }
	   
	   
	   /**
	    * 生成一个pdf文件
	    * 
	    * content    打印内容
	    * saveFilePath   打印出文件的位置，包括文件名
	    * 
	    * */
	   private boolean createSinglePDF(String content,String saveFilePath){
			
           Document document = new Document();
          
	       try {
	    	   
		             PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(saveFilePath));
                     document.open();
                     
                     String str="<div style='font-family:simsun'>"
	                                  +content.replaceAll("<hr>","<hr />").replaceAll("<br>","<br />")
	        	                +"</div>";
                     
                     XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
                     ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
                     
                     log.info("cssFilePath:"+cssFilePath);
                     File cssFile=new File(cssFilePath);
                     
                     InputStream cssInputStream=new FileInputStream(cssFile); //加css文件，pdf中表格才可以显示正确格式
                     worker.parseXHtml(writer, document,is,cssInputStream,Charset.forName("UTF-8"));
	                 
	                 log.info("生成pdf文件:"+saveFilePath);
	                 return true;
	       } catch (DocumentException |IOException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		             return false;
	       }finally{
	    	        document.close();
	       }
    }
	   
	   
	   /*
		  * 删除目录及其下全部文件
		  * 
		  * */
		 private static boolean deleteDir(File dir) {
			 
		        if (dir.isDirectory()) {
		              String[] children = dir.list();
		              
		              //递归删除目录中的子目录下
		              for (int i=0; i<children.length; i++) {
		                     boolean success = deleteDir(new File(dir, children[i]));
		                     if (!success) {
		                        return false;
		                     }
		             }
		        }
		     // 目录此时为空，可以删除
		     return dir.delete();
		 }
    
	
}//
