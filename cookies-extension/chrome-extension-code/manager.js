if (!chrome.cookies) {
	  chrome.cookies = chrome.experimental.cookies;
}
	
// A simple Timer class.
function Timer() {
  this.start_ = new Date();

  this.elapsed = function() {
	return (new Date()) - this.start_;
  }

  this.reset = function() {
	this.start_ = new Date();
  }
}

// Compares cookies for "key" (name, domain, etc.) equality, but not "value"
// equality.
function cookieMatch(c1, c2) {
  return (c1.name == c2.name) && (c1.domain == c2.domain) &&
		 (c1.hostOnly == c2.hostOnly) && (c1.path == c2.path) &&
		 (c1.secure == c2.secure) && (c1.httpOnly == c2.httpOnly) &&
		 (c1.session == c2.session) && (c1.storeId == c2.storeId);
}

// Returns an array of sorted keys from an associative array.
function sortedKeys(array) {
  var keys = [];
  for (var i in array) {
	keys.push(i);
  }
  keys.sort();
  return keys;
}

// Shorthand for document.querySelector.
function select(selector) {
  return document.querySelector(selector);
}

// An object used for caching data about the browser's cookies, which we update
// as notifications come in.
function CookieCache() {
  this.cookies_ = {};

  this.reset = function() {
	this.cookies_ = {};
  }

  this.add = function(cookie) {
	var domain = cookie.domain;
	if (!this.cookies_[domain]) {
	  this.cookies_[domain] = [];
	}
	this.cookies_[domain].push(cookie);
  };

  this.remove = function(cookie) {
	var domain = cookie.domain;
	if (this.cookies_[domain]) {
	var i = 0;
	  while (i < this.cookies_[domain].length) {
		if (cookieMatch(this.cookies_[domain][i], cookie)) {
		  this.cookies_[domain].splice(i, 1);
		} else {
		  i++;
		}
	  }
	  if (this.cookies_[domain].length == 0) {
		delete this.cookies_[domain];
	  }
	}
  };

  // Returns a sorted list of cookie domains that match |filter|. If |filter| is
  //  null, returns all domains.
  this.getDomains = function(filter) {
	var result = [];
	sortedKeys(this.cookies_).forEach(function(domain) {
	  if (!filter || domain.indexOf(filter) != -1) {
		result.push(domain);
	  }
	});
	return result;
  }

  this.getCookies = function(domain) {
	return this.cookies_[domain];
  };
}


var cache = new CookieCache();

function sendAllForFilter() {
   var all_cookies = [];
   var table = select("#cookies");
   var cookiedomains = table.querySelectorAll("input[type=checkbox][domain]");
   for(var i in cookiedomains){ 
		if(cookiedomains[i].nodeType == 1&&cookiedomains[i].checked){ 
			var domain = cookiedomains[i].getAttribute("domain"); 
			if(domain){
				cache.getCookies(domain).forEach(function(cookie) {
					console.log(cookie);
					all_cookies.push(cookie);
				});
			} 	
		} 
   }
   console.log(all_cookies);
   $('span[id^=message_]').text('');
   $('#env input[type=checkbox]').each(function(){
	   if($(this).prop('checked')){
		   var id =  $(this).attr('id');
		   var host = $(this).attr('host');
		   console.log('host:'+host+'  id:'+id);  
		   $.post(host,{cookies:JSON.stringify(all_cookies)},function(data){  
				console.log('#message_'+id);  
				console.log(data);  
				$('#message_'+id).text('Send Success!');
		   });
	   } 
   });
}

function removeAllForFilter() {
  var filter = select("#filter").value;
  var timer = new Timer();
  cache.getDomains(filter).forEach(function(domain) {
	removeCookiesForDomain(domain);
  });
}

function removeAll() {
  var all_cookies = [];
  cache.getDomains().forEach(function(domain) {
	cache.getCookies(domain).forEach(function(cookie) {
	all_cookies.push(cookie);
	});
  });
  cache.reset();
  var count = all_cookies.length;
  var timer = new Timer();
  for (var i = 0; i < count; i++) {
	removeCookie(all_cookies[i]);
  }
  timer.reset();
  chrome.cookies.getAll({}, function(cookies) {
	for (var i in cookies) {
	  cache.add(cookies[i]);
	  removeCookie(cookies[i]);
	}
  });
}

function removeCookie(cookie) {
  var url = "http" + (cookie.secure ? "s" : "") + "://" + cookie.domain +
			cookie.path;
  chrome.cookies.remove({"url": url, "name": cookie.name});
}

function getCookiesForDomain(domain) {
  var timer = new Timer();
  cache.getCookies(domain);
}

function removeCookiesForDomain(domain) { 
  var timer = new Timer();
  cache.getCookies(domain).forEach(function(cookie) {
	removeCookie(cookie);
  });
}

function resetTable() {
  var table = select("#cookies");
  while (table.rows.length > 1) {
	table.deleteRow(table.rows.length - 1);
  }
}

var reload_scheduled = false;

function scheduleReloadCookieTable() {
  if (!reload_scheduled) {
	reload_scheduled = true;
	setTimeout(reloadCookieTable, 250);
  }
}

function reloadCookieTable() {
  reload_scheduled = false;

  var filter = select("#filter").value;

  var domains = cache.getDomains(filter);

  select("#filter_count").innerText = domains.length;
  select("#total_count").innerText = cache.getDomains().length;

  select("#delete_all_button").innerHTML = "";
  select("#send_all_button").innerHTML = "";
  
  if (domains.length) {
	//delete button
	var button = document.createElement("button");
	button.onclick = removeAllForFilter;
	button.innerText = "delete cookies (" + domains.length + ")";
	select("#delete_all_button").appendChild(button);
	
	//send button
	var sendbutton = document.createElement("button");
	sendbutton.onclick = sendAllForFilter;
	sendbutton.innerText = "send cookies (" + domains.length + ")";
	select("#send_all_button").appendChild(sendbutton);
  }

  resetTable();
  var table = select("#cookies");
  
  var selectall = select("#all");
  selectall.onclick = (function(){
	  var cookiedomains = table.querySelectorAll("input[type=checkbox]"); 
	  for(var i in cookiedomains){ 
        cookiedomains[i].checked=selectall.checked;
      }    
  });

  domains.forEach(function(domain) {
	var cookies = cache.getCookies(domain);
	//console.log(cookies[0]);
	var row = table.insertRow(-1);
	row.insertCell(-1).innerText = domain;
	var cell = row.insertCell(-1);
	cell.innerText = cookies.length;
	cell.setAttribute("class", "cookie_count");
	
	var input = document.createElement("input");
	input.setAttribute("type","checkbox"); 
    input.setAttribute("domain",domain);	
	input.onclick = (function(dom){
	  return function() {
		getCookiesForDomain(dom);
	  };
	}(domain));
	var cell = row.insertCell(-1);
	cell.appendChild(input);
	
	var button = document.createElement("button");
	button.innerText = "delete";
	button.onclick = (function(dom){ 
	  return function() { 
		removeCookiesForDomain(dom);
	  };
	}(domain));
	var cell = row.insertCell(-1);
	cell.appendChild(button);
	cell.setAttribute("class", "button");
  });
}

function focusFilter() {
  select("#filter").focus();
}

function resetFilter() {
  var filter = select("#filter");
  filter.focus();
  if (filter.value.length > 0) {
	filter.value = "";
	reloadCookieTable();
  }
}

var ESCAPE_KEY = 27;
window.onkeydown = function(event) {
  if (event.keyCode == ESCAPE_KEY) {
	resetFilter();
  }
}

function listener(info) {
  cache.remove(info.cookie);
  if (!info.removed) {
	cache.add(info.cookie);
  }
  scheduleReloadCookieTable();
}

function startListening() {
  chrome.cookies.onChanged.addListener(listener);
}

function stopListening() {
  chrome.cookies.onChanged.removeListener(listener);
}

function onload() {
  focusFilter();
  showEnv();
  var timer = new Timer();
  chrome.cookies.getAll({}, function(cookies) {
	startListening();
	start = new Date();
	for (var i in cookies) {
	  cache.add(cookies[i]);
	}
	timer.reset();
	reloadCookieTable();
  });
}

function showEnv(){
	var data = Config.env;
	$.each(data, function(i, item) {
		$('#env').append('<tr><td>'+data[i].name+'</td><td>'+data[i].host+'</td><td><input type="checkbox" id="env_'+data[i].name+'" host="'+data[i].host+'" ><span id="message_env_'+data[i].name+'" ></span></td><tr>');
	});
}


document.addEventListener('DOMContentLoaded', function() {
  onload();
  document.body.addEventListener('click', focusFilter);
  //document.querySelector('#remove_button').addEventListener('click', removeAll);
  document.querySelector('#filter_div input').addEventListener(
	  'input', reloadCookieTable);
  document.querySelector('#filter_div button').addEventListener(
	  'click', resetFilter);
});