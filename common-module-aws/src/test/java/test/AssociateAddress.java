package test;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AssociateAddressRequest;
import com.amazonaws.services.ec2.model.AssociateAddressResult;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.RevokeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.SecurityGroup;
import com.amazonaws.services.ec2.model.DisassociateAddressRequest; 

public class AssociateAddress {
	
	public static String endpoint ="ec2.cn-north-1.amazonaws.com.cn";
	    
    private static String accessKey = "AKIAOLE75G26VWQOUB2Q";
    
    private static String secretKey = "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";
    
    private static String allocationId = "eipalloc-fd930fc7";
    
    private BasicAWSCredentials awsCreds;
    
    private AmazonEC2Client amazonEC2Client;
    
	public AssociateAddress(){
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
		
	public AssociateAddressResult testAssociateAddress(String instanceId){
		AssociateAddressRequest associateAddressRequest  = new AssociateAddressRequest ();
		//associateAddressRequest.withAllocationId("eipalloc-fd930fc7").withInstanceId("i-52cf9c6a");
		associateAddressRequest.withAllocationId(allocationId).withInstanceId(instanceId);
		AssociateAddressResult associateAddressResult = amazonEC2Client.associateAddress(associateAddressRequest);
		return associateAddressResult;
	}
	
	
	public void testDisassociateAddress(String associationId){
		DisassociateAddressRequest disassociateAddressRequest = new DisassociateAddressRequest();
		disassociateAddressRequest.withAssociationId(associationId);
		amazonEC2Client.disassociateAddress(disassociateAddressRequest);
		
	}
	
	public void changeIP(){
		//String[] strs = {"i-53cf9c6b","i-54cf9c6c","i-55cf9c6d","i-dc5df5e4","i-db4fefe3","i-b54eee8d","i-c890c8f0","i-c990c8f1","i-ca90c8f2","i-cb90c8f3","i-ce90c8f6","i-cf90c8f7"};
		String[] strs = {"i-55cf9c6d"};
		for(String str:strs){
			 AssociateAddressResult associateAddressResult = testAssociateAddress(str);
	 		 String associationId = associateAddressResult.getAssociationId();
	 		 System.out.println(associationId); 
	 		 testDisassociateAddress(associationId);
		}
	}
	
	public static void main(String[] args) {
		AssociateAddress test = new AssociateAddress();
		test.changeIP();
	}
		 

	public static void main2(String[] args) {
		 AssociateAddress test = new AssociateAddress();
		 /*
		 AssociateAddressResult associateAddressResult = test.testAssociateAddress("i-52cf9c6a");
 		 String associationId = associateAddressResult.getAssociationId();
 		 System.out.println(associationId); 
 		 */
		 String associationId = "eipassoc-41dea67b";
	     test.testDisassociateAddress(associationId);
		
	}

}
