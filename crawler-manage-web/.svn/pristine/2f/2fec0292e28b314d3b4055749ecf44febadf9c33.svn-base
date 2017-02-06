package com.crawlermanage.controller;

import com.amazonaws.services.ec2.model.IpPermission;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.module.aws.IpPermissionVO;
import com.module.aws.Permission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.amazonaws.services.ec2.model.SecurityGroup;
import com.module.aws.AWSService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/admin/aws")
public class AWSController {
	
	public static final Logger log = LoggerFactory.getLogger(AWSController.class);

	@Autowired
	private AWSService awsService; 
	
	@Value("${aws.securityGroup.name}")
	public String securityGroupName;
	
	
	@RequestMapping("/securitygroup")
    public String home(ModelMap modelMap){

		SecurityGroup securityGroup = awsService.findSecurityGroup();

		log.info(securityGroup.toString());
		List<IpPermission> ipPermissions = securityGroup.getIpPermissions();

		List<IpPermissionVO> ipPermissionVOs = new ArrayList<>();
		for(int i = 0 ; i < ipPermissions.size() ; i++){
			IpPermission ipPermission = ipPermissions.get(i);



			String portRange = "";
			log.info("ipPermission.getFromPort()--------"+ipPermission.getFromPort());
			log.info("ipPermission.getToPort()-------"+ipPermission.getToPort());
			if(ipPermission.getFromPort()==null&&ipPermission.getToPort()==null){
				portRange = "0 - 65535";
			}else if(ipPermission.getFromPort().equals(ipPermission.getToPort())){
				portRange = ipPermission.getFromPort().toString();
			}else{
				portRange = ipPermission.getFromPort().toString() + " - " + ipPermission.getToPort().toString();
			}



			StringBuffer  ipRanges = new StringBuffer();
			if(ipPermission.getIpRanges().size() > 0){

				for(int j = 0 ; j < ipPermission.getIpRanges().size() ; j++){

					IpPermissionVO ipPermissionVO = new IpPermissionVO();

					ipPermissionVO.setIpProtocol(ipPermission.getIpProtocol());
					ipPermissionVO.setPortRange(portRange);
					ipPermissionVO.setIpRange(ipPermission.getIpRanges().get(j));

					ipPermissionVOs.add(ipPermissionVO);
				}
			}



		}

		modelMap.put("ipPermissionVOs",ipPermissionVOs);
		modelMap.put("securityGroupName",securityGroupName);
		

   	 	return "aws/index";
    }



	@RequestMapping(value = "/save",method = {RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	String save(@RequestBody String jsonData){

		SecurityGroup securityGroup = awsService.findSecurityGroup();

		log.info(securityGroup.toString());
		List<IpPermission> ipPermissions = securityGroup.getIpPermissions();

		log.info(jsonData);

		Gson gson = new Gson();
		Permission permissionVO = gson.fromJson(jsonData, Permission.class);

		List<IpPermissionVO> saveRules = permissionVO.getSaveRules();


		for(IpPermission ipPermission : ipPermissions){
			awsService.revokeIpPermission(ipPermission);
		}

		for(IpPermissionVO ipPermissionVO : saveRules){
			IpPermission ipPermission = new IpPermission();

			ipPermission.withIpRanges(ipPermissionVO.getIpRange().split(","))
					.withIpProtocol(ipPermissionVO.getIpProtocol())
					.withFromPort(Integer.parseInt(ipPermissionVO.getPortRange()))
					.withToPort(Integer.parseInt(ipPermissionVO.getPortRange()));

			awsService.addIpPermission(ipPermission);
		}



		return "success";
	}
	
	
}
