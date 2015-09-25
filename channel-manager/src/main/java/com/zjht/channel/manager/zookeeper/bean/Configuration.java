package com.zjht.channel.manager.zookeeper.bean;

import java.util.List;

/**
 * 
 * 渠道系统元配置类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 11:25:40 PM
 */
public class Configuration {
	private String node;
	private List<Configuration> children;
	private String originalData;//原值
	private String data;//现值
	private String status;
	
	/** 
	 * Creates a new instance of ChannelConfiguration. 
	 * 
	 * @param node
	 * @param children
	 * @param data 
	 */  
	public Configuration(String node, List<Configuration> children, String data) {
		super();
		this.node = node;
		this.children = children;
		this.data = data;
	}
	/** 
	 * Creates a new instance of ChannelConfiguration. 
	 *  
	 */  
	public Configuration() {
	}
	/** 
	 * node. 
	 * 
	 * @return  the node 
	 * @since   JDK 1.8
	 */
	public String getNode() {
		return node;
	}
	/** 
	 * children. 
	 * 
	 * @return  the children 
	 * @since   JDK 1.8
	 */
	public List<Configuration> getChildren() {
		return children;
	}
	
	
	/** 
     * originalData. 
     * 
     * @return  the originalData 
     * @since   JDK 1.8
     */
    public String getOriginalData() {
        return originalData;
    }
    /** 
     * originalData. 
     * 
     * @param   originalData    the originalData to set 
     * @since   JDK 1.8
     */
    public Configuration setOriginalData(String originalData) {
        this.originalData = originalData;
        return this;
    }
    /** 
	 * data. 
	 * 
	 * @return  the data 
	 * @since   JDK 1.8
	 */
	public String getData() {
		return data;
	}
	/** 
	 * node. 
	 * 
	 * @param   node    the node to set 
	 * @since   JDK 1.8
	 */
	public Configuration setNode(String node) {
		this.node = node;
		return this;
	}
	/** 
	 * children. 
	 * 
	 * @param   children    the children to set 
	 * @since   JDK 1.8
	 */
	public Configuration setChildren(List<Configuration> children) {
		this.children = children;
		return this;
	}
	/** 
	 * data. 
	 * 
	 * @param   data    the data to set 
	 * @since   JDK 1.8
	 */
	public Configuration setData(String data) {
		this.data = data;
		return this;
	}
    /** 
     * status. 
     * 
     * @return  the status 
     * @since   JDK 1.8
     */
    public String getStatus() {
        return status;
    }
    /** 
     * status. 
     * 
     * @param   status    the status to set 
     * @since   JDK 1.8
     */
    public Configuration setStatus(String status) {
        this.status = status;
        return this;
    }
    /** 
     * @see java.lang.Object#hashCode() 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((node == null) ? 0 : node.hashCode());
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
        Configuration other = (Configuration) obj;
        if (node == null) {
            if (other.node != null)
                return false;
        } else if (!node.equals(other.node))
            return false;
        return true;
    }
    /** 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return "ZNode [node=" + node + ", children=" + children + ", originalData=" + originalData
                + ", data=" + data + ", status=" + status + "]";
    }

    
}
