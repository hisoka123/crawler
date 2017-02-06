package com.module.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AssociateAddressRequest;
import com.amazonaws.services.ec2.model.AssociateAddressResult;
import com.amazonaws.services.ec2.model.DisassociateAddressRequest; 

@Component
public class AssociateIP { 
	
	private static final Logger log = LoggerFactory.getLogger(AssociateIP.class);
	
	@Value("${aws.endpoint}")
	public String endpoint;
	
	@Value("${aws.accessKey}")
	public String accessKey;
	
	@Value("${aws.secretKey}")
	public String secretKey;
	
	@Value("${aws.eipalloc}")
	public String eipalloc;
	
	@Value("${aws.crawlers}")
	public String crawlers;
	
    
    private BasicAWSCredentials awsCreds;
    
    private AmazonEC2Client amazonEC2Client;
    
	public BasicAWSCredentials getAwsCreds() {
		return awsCreds;
	} 
	public void setAwsCreds(BasicAWSCredentials awsCreds) {
		this.awsCreds = awsCreds;
	}
		
	public AssociateAddressResult testAssociateAddress(String instanceId){ 
		awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		amazonEC2Client = new AmazonEC2Client(awsCreds);
		amazonEC2Client.setEndpoint(endpoint); 
		AssociateAddressRequest associateAddressRequest  = new AssociateAddressRequest (); 
		associateAddressRequest.withAllocationId(eipalloc).withInstanceId(instanceId);
		AssociateAddressResult associateAddressResult = amazonEC2Client.associateAddress(associateAddressRequest);
		return associateAddressResult;
	}
	
	
	public void testDisassociateAddress(String associationId){ 
		awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		amazonEC2Client = new AmazonEC2Client(awsCreds);
		amazonEC2Client.setEndpoint(endpoint); 
		DisassociateAddressRequest disassociateAddressRequest = new DisassociateAddressRequest();
		disassociateAddressRequest.withAssociationId(associationId);
		amazonEC2Client.disassociateAddress(disassociateAddressRequest); 
	}
	
	public void changeAllAddress(){
		log.info("accessKey--------"+accessKey);
		log.info("secretKey--------"+secretKey);
		log.info("endpoint--------"+endpoint);
		log.info("eipalloc--------"+eipalloc); 
		log.info("crawlers--------"+crawlers);
		String[] crawlerset = crawlers.split(",");
		int index = 0;
		if(crawlerset!=null&&crawlerset.length>0){
			for(String c : crawlerset){
				index ++;
				try{
					AssociateAddressResult associateAddressResult = testAssociateAddress(c);
					String associationId = associateAddressResult.getAssociationId();
					log.info("index:"+index+"  ,"+c+"is success associate associationId:"+associationId);
					
					testDisassociateAddress(associationId);
					log.info("index:"+index+"  ,"+associationId+"is success disassociateAddress");
				}catch(Exception e){
					log.info("aws -------ipswitch---------"+e.getMessage());
					
				}
				
			}
		}
		
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getEipalloc() {
		return eipalloc;
	}
	public void setEipalloc(String eipalloc) {
		this.eipalloc = eipalloc;
	}
	public String getCrawlers() {
		return crawlers;
	}
	public void setCrawlers(String crawlers) {
		this.crawlers = crawlers;
	}
	
	
	
	
	

}
