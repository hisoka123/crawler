package com.module.timetask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.module.aws.AssociateIP;


@Component("iPSwitch")
public class IPSwitch {
	
	private static final Logger log = LoggerFactory.getLogger(IPSwitch.class);
	
	@Autowired
	private AssociateIP associateIP;
	
	public void ipswitch(String jobName, String jobGroup){
		
		log.info("job:"+jobName+" is started ! Group:"+jobGroup); 
		associateIP.changeAllAddress();
	
	}

}
