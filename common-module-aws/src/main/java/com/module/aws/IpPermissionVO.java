package com.module.aws;



public class IpPermissionVO {

    private String ipProtocol;
    private String portRange;
    private String ipRange;

    public IpPermissionVO() {    }

    public IpPermissionVO(String ipProtocol, String portRange, String ipRange) {
        this.ipProtocol = ipProtocol;
        this.portRange = portRange;
        this.ipRange = ipRange;
    }

    public String getIpProtocol() {
        return ipProtocol;
    }

    public void setIpProtocol(String ipProtocol) {
        this.ipProtocol = ipProtocol;
    }

    public String getPortRange() {
        return portRange;
    }

    public void setPortRange(String portRange) {
        this.portRange = portRange;
    }

    public String getIpRange() {
        return ipRange;
    }

    public void setIpRange(String ipRange) {
        this.ipRange = ipRange;
    }

    @Override
    public String toString() {
        return "IpPermissionVO{" +
                "ipProtocol='" + ipProtocol + '\'' +
                ", portRange='" + portRange + '\'' +
                ", ipRange='" + ipRange + '\'' +
                '}';
    }
}
