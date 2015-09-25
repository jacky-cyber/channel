package com.zjht.channel.service.bean;

import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKNode;
import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKPath;
import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKPathVariable;

@ZKPath("/service/dubbo/reference/{name}/{version}")
public class Reference{
	
    @ZKNode(required=true,name="服务引用bean id")
	private String id;
    
    @ZKNode(required=true,name="服务接口类的全限定名称")
	private String fullyQualifiedName;
    
    @ZKNode(required=true,name="服务名称")
    @ZKPathVariable("name")
	private String name;
    
    @ZKNode(required=true,name="服务版本")
    @ZKPathVariable("version")
	private String version;
    
    @ZKNode(name="负责人")
	private String owner;
    
    @ZKNode(name="组织(部门)名称")
	private String group;
    
    @ZKNode(name="动态代理实现策略",value="javassist",range={"javassist","jdk"})
    private String proxy;
    
    @ZKNode(name="是否饿初始化引用",value="true",range={"true","false"})
    private boolean init;
    
    @ZKNode(required=true,name="服务状态",value="01",range={"00","01"})
    private String status;
    
    /** 
     * Creates a new instance of Reference. 
     *  
     */  
    public Reference() {
        super();
    }

    public String getId() {
        return id;
    }

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getOwner() {
        return owner;
    }

    public String getGroup() {
        return group;
    }

    public String getProxy() {
        return proxy;
    }

    public boolean isInit() {
        return init;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullyQualifiedName(String fullyQualifiedName) {
        this.fullyQualifiedName = fullyQualifiedName;
    }

    public Reference setName(String name) {
        this.name = name;
        return this;
    }

    public Reference setVersion(String version) {
        this.version = version;
        return this;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /** 
     * Creates a new instance of Reference. 
     * 
     * @param name
     * @param version 
     */  
    public Reference(String name, String version) {
        super();
        this.name = name;
        this.version = version;
    }

    /** 
     * @see java.lang.Object#hashCode() 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    /** 
     * @see java.lang.Object#equals(java.lang.Object) 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reference other = (Reference) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

    /** 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return "Reference [id=" + id + ", fullyQualifiedName=" + fullyQualifiedName + ", name="
                + name + ", version=" + version + ", owner=" + owner + ", group=" + group
                + ", proxy=" + proxy + ", init=" + init + ", status=" + status + "]";
    }

  
    
}

