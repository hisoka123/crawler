package test;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.RevokeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.SecurityGroup;


public class IPTest { 
    
    public static String endpoint ="ec2.cn-north-1.amazonaws.com.cn";
    
    private static String accessKey = "AKIAOLE75G26VWQOUB2Q";
    
    private static String secretKey = "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";
    
    private BasicAWSCredentials awsCreds;
    
    private AmazonEC2Client amazonEC2Client;
    
	public IPTest(){
		System.out.println("accessKey:"+accessKey);
		System.out.println("secretKey:"+secretKey);
		this.awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		this.amazonEC2Client = new AmazonEC2Client(awsCreds);
		this.amazonEC2Client.setEndpoint(endpoint); 
	} 
	public BasicAWSCredentials getAwsCreds() {
		return awsCreds;
	} 
	public void setAwsCreds(BasicAWSCredentials awsCreds) {
		this.awsCreds = awsCreds;
	}
	
	public List<SecurityGroup> findAllSecurityGroups() {
	    DescribeSecurityGroupsRequest securityRequest = new DescribeSecurityGroupsRequest();
	    DescribeSecurityGroupsResult securityDescription = amazonEC2Client.describeSecurityGroups(securityRequest);
	    return securityDescription.getSecurityGroups();
	}
	
	public SecurityGroup findOneSecurityGroupByName(String securityGroupName) {
	    DescribeSecurityGroupsRequest securityRequest = new DescribeSecurityGroupsRequest();
	    securityRequest.setGroupNames(Arrays.asList(securityGroupName));
	    DescribeSecurityGroupsResult securityDescription = amazonEC2Client.describeSecurityGroups(securityRequest);
	    return securityDescription.getSecurityGroups().get(0);
	}
	
	public IpPermission addIpPermission(IpPermission ipPermission){
		AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest = new AuthorizeSecurityGroupIngressRequest();
		authorizeSecurityGroupIngressRequest.withGroupName("JavaSecurityGroup").withIpPermissions(ipPermission);
		amazonEC2Client.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);
		return ipPermission;
	}
	
	
	public IpPermission revokeIpPermission(IpPermission ipPermission){
		RevokeSecurityGroupIngressRequest revokeSecurityGroupIngressRequest = new RevokeSecurityGroupIngressRequest(); 
		revokeSecurityGroupIngressRequest.withGroupName("JavaSecurityGroup").withIpPermissions(ipPermission);
		amazonEC2Client.revokeSecurityGroupIngress(revokeSecurityGroupIngressRequest);
		return ipPermission;
	}
	



	public static void main(String[] args) { 
		IPTest test = new IPTest();
		
		IpPermission ipPermission = new IpPermission();
		ipPermission.withIpRanges("114.112.112.112/32","115.112.112.112/32")
			            .withIpProtocol("tcp")
			            .withFromPort(80)
			            .withToPort(80);
		
		 test.addIpPermission(ipPermission);
		
		//test.revokeIpPermission(ipPermission);
		
		/*
		SecurityGroup sg = test.findOneSecurityGroupByName("JavaSecurityGroup");
		System.out.println(sg.getGroupName()); 
		List<IpPermission> ipPermissions = sg.getIpPermissions();
		for(IpPermission ipp:ipPermissions){
			System.out.println(ipp.toString());
		}
		*/
		/*
		List<SecurityGroup> sgs = test.findAllSecurityGroups();
		for(SecurityGroup sg:sgs){
			System.out.println(sg.getGroupName()); 
			List<IpPermission> ipPermissions = sg.getIpPermissions();
			for(IpPermission ipp:ipPermissions){
				System.out.println(ipp.toString());
			}
			
		}*/
		/*
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		AmazonEC2Client amazonEC2Client = new AmazonEC2Client(awsCreds);
		amazonEC2Client.setEndpoint(endpoint); 
		CreateSecurityGroupRequest csgr = new CreateSecurityGroupRequest();
		csgr.withGroupName("JavaSecurityGroup2").withDescription("My security group");
		CreateSecurityGroupResult createSecurityGroupResult = amazonEC2Client.createSecurityGroup(csgr);
		*//*
		IpPermission ipPermission = new IpPermission();
		ipPermission.withIpRanges("112.112.112.112/32", "152.152.152.152/32")
			            .withIpProtocol("tcp")
			            .withFromPort(22)
			            .withToPort(22);
		
		AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest = new AuthorizeSecurityGroupIngressRequest();

		authorizeSecurityGroupIngressRequest.withGroupName("JavaSecurityGroup").withIpPermissions(ipPermission);
			
		amazonEC2Client.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);
		
		DescribeSecurityGroupsRequest securityRequest = new DescribeSecurityGroupsRequest();
		 */
		 
		 
		
	}

}
