package com.zjht.channel.service.bean;


public class ServiceInfo {
    private String serviceName;//服务名称
	private String serviceVersion;//服务版本
	private String packageName;//服务接口的包名
	private String className;//服务接口的类名
	private String methodName;//服务接口的实现方法名
	private String status;//服务状态
	
	public ServiceInfo(){
	    super();
	}
	
	public ServiceInfo(String serviceName,String serviceVersion){
	    this.serviceName    = serviceName;
	    this.serviceVersion = serviceVersion;
	}

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((serviceName == null) ? 0 : serviceName.hashCode());
        result = prime * result
                + ((serviceVersion == null) ? 0 : serviceVersion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceInfo other = (ServiceInfo) obj;
        if (serviceName == null) {
            if (other.serviceName != null)
                return false;
        } else if (!serviceName.equals(other.serviceName))
            return false;
        if (serviceVersion == null) {
            if (other.serviceVersion != null)
                return false;
        } else if (!serviceVersion.equals(other.serviceVersion))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServiceInfo [serviceName=" + serviceName + ", serviceVersion="
                + serviceVersion + ", packageName=" + packageName
                + ", className=" + className + ", methodName=" + methodName
                + ", status=" + status + "]";
    }

}
