/*
 * (1)indexedDB存储内容
 * sina,zhihu,weixin,linkedIn,baidu的搜索结果
 * 
 * (2)localStorage存储内容
 * 上次登录时间               lastTime
 * indexedDB版本号       indexedDB_version
 * 搜索结果页面                encodeURI(encodeURI(searchKey))
 *加入“综合查阅详情”人物在indexedDB中索引     joinDetailed_indexedDB_index 
 *
 * */

function  connectIndexedDB(){
	   
	  window.indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
      window.IDBTransaction = window.IDBTransaction || window.webkitIDBTransaction || window.msIDBTransaction;
      window.IDBKeyRange = window.IDBKeyRange || window.webkitIDBKeyRange || window.msIDBKeyRange;
	  
      if(!window.indexedDB){
    	   alert("您的浏览器版本过低，请升级您的浏览器以保证系统能正常运行！\n IE:10+、Chrome: 43+、Firefox:40+、Opera:32+");
    	   return;
      }
      
      
      var dbConnect=window.indexedDB.open(SEARCHRESULTSDB.NAME,SEARCHRESULTSDB.VERSION);
	   
      dbConnect.onerror=function(e){
		    console.log("Connect IndexedDB Database Failed! "+e.currentTarget.error.message);
	   }
	  
      dbConnect.onsuccess=function(e){
		    console.log("Connect IndexedDB Database(v"+SEARCHRESULTSDB.VERSION+") Success!");
		    SEARCHRESULTSDB.IDB=e.target.result;
	   }
	  
      dbConnect.onupgradeneeded=function(e){
		      var db=e.target.result;
              createIndexedDB(db);	
	   }
}

/*create indexedDB*/
function createIndexedDB(db){
	
	//sina
    if(!db.objectStoreNames.contains("sinaSearchResultsTab")){
  	       var sina_store=db.createObjectStore("sinaSearchResultsTab",{autoIncrement:true});
  	       sina_store.createIndex("sinaIndex","personIndex",{unique:true});
    }
    if(!db.objectStoreNames.contains("sinaDetailsTab")){
	       var sina_detail=db.createObjectStore("sinaDetailsTab",{autoIncrement:true});
	       sina_detail.createIndex("sinaIndex","personIndex",{unique:true});
    }
    
    
    //zhihu
    if(!db.objectStoreNames.contains("zhihuSearchResultsTab")){
  	       var zhihu_store=db.createObjectStore("zhihuSearchResultsTab",{autoIncrement:true});
  	       zhihu_store.createIndex("zhihuIndex","personIndex",{unique:true});
    }
    if(!db.objectStoreNames.contains("zhihuDetailsTab")){
	       var zhihu_detail=db.createObjectStore("zhihuDetailsTab",{autoIncrement:true});
	       zhihu_detail.createIndex("zhihuIndex","personIndex",{unique:true});
    }
    
    
    //weixin
    if(!db.objectStoreNames.contains("weixinSearchResultsAccountTab")){
  	       var weixin_store=db.createObjectStore("weixinSearchResultsAccountTab",{autoIncrement:true});
  	       weixin_store.createIndex("weixinAccountIndex","personIndex",{unique:true});
    }
    
    if(!db.objectStoreNames.contains("weixinSearchResultsArticleTab")){
  	       var weixin_store=db.createObjectStore("weixinSearchResultsArticleTab",{autoIncrement:true});
  	       weixin_store.createIndex("weixinArticleIndex","personIndex",{unique:true});
    }
    
    
    //linkedIn
    if(!db.objectStoreNames.contains("linkedInSearchResultsTab")){
  	       var linkedIn_store=db.createObjectStore("linkedInSearchResultsTab",{autoIncrement:true});
  	       linkedIn_store.createIndex("linkedInIndex","personIndex",{unique:true});
    }
    if(!db.objectStoreNames.contains("linkedInDetailsTab")){
	       var linkedIn_detail=db.createObjectStore("linkedInDetailsTab",{autoIncrement:true});
	       linkedIn_detail.createIndex("linkedInIndex","personIndex",{unique:true});
    }
    
    
    //baidu
    if(!db.objectStoreNames.contains("baiduSearchResultsTab")){
  	       var baidu_store=db.createObjectStore("baiduSearchResultsTab",{autoIncrement:true});
  	       baidu_store.createIndex("baiduIndex","personIndex",{unique:true});
    }
    if(!db.objectStoreNames.contains("baiduDetailsTab")){
	       var baidu_detail=db.createObjectStore("baiduDetailsTab",{autoIncrement:true});
	       baidu_detail.createIndex("baiduIndex","personIndex",{unique:true});
    }
    
}

/*
 * 各模块搜索结果保存
 * objectStoreName   模块数据仓库
 * length            搜索结果长度
 * */
function indexedDB_saveSearchResults(objectStoreName,searchResults){
	
	
	 var transaction=SEARCHRESULTSDB.IDB.transaction(objectStoreName,"readwrite");
     var store=transaction.objectStore(objectStoreName);
     
     for(var i=0;i<searchResults.length;i++){
         
    	 store.put(searchResults[i]);
     }
}
/*获取数据库的版本号
 * 
*(1)当数据库不存在时，数据库版本为1
*(2)当数据库存在时，且历史更新数据库版本的页面中      有      “综合人物搜索”页时，直接返回存在localStorage(key=indexedDB_version)中的版本号
*(3)当数据库存在时，且历史更新数据库版本的页面中     没有      “综合人物搜索”页时,
*   历史更新数据库版本的页面是否有当前页面:
*   如果有，直接返回localStorage(key=indexedDB_version)中的版本号；
*     没有，加1后再返回。
*
*/
function indexedDB_getVersion(){
	  
	  var version=localStorage.getItem("indexedDB_version");
	  
	  if(version==null){ //如果数据库不存在，版本号为1
		    localStorage.setItem("indexedDB_version","1");
	  }    	 
     
	  return localStorage.getItem("indexedDB_version");
	  
}
//返回模块名
function indexedDB_getNowModuleName(){
	
	 var url=document.location.toString().split("?")[0];
	 
	 if(url.indexOf("/modules/personSearch")!=-1){
		   return "personSearch";
	 }else if(url.indexOf("/modules/sinaweibo")!=-1){
		   return "sina";
	 }else if(url.indexOf("/modules/baidubaike")!=-1){
		   return "baidu";
	 }else if(url.indexOf("/modules/zhihu")!=-1){
		   return "zhihu"
	 }else if(url.indexOf("/modules/weixin")!=-1){
		   return "weixin";
	 }else if(url.indexOf("/modules/linkedIn")!=-1){
		   return "linkedIn";
	 }
	
}
function  indexedDB_clear(){
	
	  var dateObj=new Date();
	  var date=dateObj.getFullYear()+"-"+(dateObj.getMonth()+1)+"-"+dateObj.getDate();
	  var time=dateObj.getHours()+":"+dateObj.getMinutes()+":"+dateObj.getSeconds();
	  var nowTime=date+" "+time;
	 
	  var lastTime=localStorage.getItem("lastTime");
	  var version=localStorage.getItem("indexedDB_version");
	  
	  if(version==null){
		    localStorage.setItem("indexedDB_version","1");
	   }
	  
	  if(lastTime==null){
		    localStorage.setItem("lastTime",nowTime);
		    lastTime=localStorage.getItem("lastTime");
	  }
	  
	  lastTime_date=lastTime.split(" ")[0];
	  
	  if(date!=lastTime_date){//隔天
		    localStorage.setItem("lastTime",nowTime);
		    localStorage.setItem("indexedDB_version",++version);
	   }else{
		    lastTime_hour=lastTime.split(" ")[1].split(":")[0];
			d_value_hours=parseInt(lastTime_hour)-dateObj.getHours();
			
			if(d_value_hours>6){   
			      localStorage.setItem("lastTime",nowTime);
			      localStorage.setItem("indexedDB_version",++version);
			}
		}
	  
	  return parseInt(localStorage.getItem("indexedDB_version"));
}
/*
 * 获取搜索地果数据
 * idb    数据库idb
 * storeName  数据仓库名
 * indexName  索引名
 * keyIndex   具体索引,结果为列表
 */
/*function indexedDB_getData(idb,storeName,indexName,keyIndex){
	
	   console.log("idb:"+idb+"  storeName:"+storeName+"  indexName:"+indexName+"  keyIndex:"+keyIndex);
	  
	   var index=idb.transaction(storeName).objectStore(storeName).index(indexName);
	   
	   index.get(keyIndex).onsuccess=function(e){
		       return e.target.result;
		       
	   }
	   
	   index.get(keyIndex).onerror=function(){
		       return "sinaGetDataError";
	   }
}*/

