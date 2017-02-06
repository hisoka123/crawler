package com.module.aws;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.RevokeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.SecurityGroup;

@Component
public class AWSService {
	
	private static final Logger log = LoggerFactory.getLogger(AWSService.class);
	
	@Value("${aws.endpoint}")
	public String endpoint;
	
	@Value("${aws.accessKey}")
	public String accessKey;
	
	@Value("${aws.secretKey}")
	public String secretKey;
	
	@Value("${aws.securityGroup.name}")
	public String securityGroupName;
	
	private BasicAWSCredentials awsCreds;
	    
	private AmazonEC2Client amazonEC2Client;
	 
	/*
	 * 得到安全组下的所有IP段、端口、协议等信息
	 * 
	 * */ 
	public SecurityGroup findSecurityGroup() {
		log.info("accessKey--------"+accessKey);
		log.info("accessKey--------"+secretKey);
		log.info("securityGroupName--------"+securityGroupName);
	    DescribeSecurityGroupsRequest securityRequest = new DescribeSecurityGroupsRequest();
	    awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		amazonEC2Client = new AmazonEC2Client(awsCreds);
		amazonEC2Client.setEndpoint(endpoint); 
	    securityRequest.setGroupNames(Arrays.asList(securityGroupName));
	    DescribeSecurityGroupsResult securityDescription = amazonEC2Client.describeSecurityGroups(securityRequest);
	    return securityDescription.getSecurityGroups().get(0);
	}
	
	/*
	 * 添加一个IP段、端口、协议等信息
	 * ipPermission 的构造如下，例如：
	 * IpPermission ipPermission = new IpPermission();
		ipPermission.withIpRanges("113.112.112.112/32")
			            .withIpProtocol("tcp")
			            .withFromPort(22)
			            .withToPort(22);
	 * 
	 * */
	public IpPermission addIpPermission(IpPermission ipPermission){
		AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest = new AuthorizeSecurityGroupIngressRequest();
		authorizeSecurityGroupIngressRequest.withGroupName(securityGroupName).withIpPermissions(ipPermission);
		amazonEC2Client.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);
		return ipPermission;
	}
	
	/*
	 * 删除一个IP段、端口、协议等信息
	 */
	public IpPermission revokeIpPermission(IpPermission ipPermission){
		RevokeSecurityGroupIngressRequest revokeSecurityGroupIngressRequest = new RevokeSecurityGroupIngressRequest(); 
		revokeSecurityGroupIngressRequest.withGroupName(securityGroupName).withIpPermissions(ipPermission);
		amazonEC2Client.revokeSecurityGroupIngress(revokeSecurityGroupIngressRequest);
		return ipPermission;
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

	public String getSecurityGroupName() {
		return securityGroupName;
	}

	public void setSecurityGroupName(String securityGroupName) {
		this.securityGroupName = securityGroupName;
	}

	public BasicAWSCredentials getAwsCreds() {
		return awsCreds;
	}

	public void setAwsCreds(BasicAWSCredentials awsCreds) {
		this.awsCreds = awsCreds;
	}

	public AmazonEC2Client getAmazonEC2Client() {
		return amazonEC2Client;
	}

	public void setAmazonEC2Client(AmazonEC2Client amazonEC2Client) {
		this.amazonEC2Client = amazonEC2Client;
	}
	
	 
	
	

}
