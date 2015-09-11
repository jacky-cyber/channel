package com.zjht.channel.service.bean;

public class Reference{
	
	private String id;//服务引用BeanId 
	private String fullyQualifiedName;//服务接口类的全限定名称
	private String name;//服务名称
	private String version;//服务版本；
	private String owner;//负责人
	private String group;//组织名称

	public Reference(){
	    super();
	}
	
	public Reference(String name,String version){
	    this.name    = name;
	    this.version = version;
	}
	
	/** 
	 * id. 
	 * 
	 * @return  the id 
	 * @since   JDK 1.8
	 */
	public String getId() {
		return id;
	}

	/** 
	 * id. 
	 * 
	 * @param   id    the id to set 
	 * @since   JDK 1.8
	 */
	public void setId(String id) {
		this.id = id;
	}

	/** 
	 * fullyQualifiedName. 
	 * 
	 * @return  the fullyQualifiedName 
	 * @since   JDK 1.8
	 */
	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}

	/** 
	 * fullyQualifiedName. 
	 * 
	 * @param   fullyQualifiedName    the fullyQualifiedName to set 
	 * @since   JDK 1.8
	 */
	public void setFullyQualifiedName(String fullyQualifiedName) {
		this.fullyQualifiedName = fullyQualifiedName;
	}

	/** 
	 * name. 
	 * 
	 * @return  the name 
	 * @since   JDK 1.8
	 */
	public String getName() {
		return name;
	}

	/** 
	 * name. 
	 * 
	 * @param   name    the name to set 
	 * @since   JDK 1.8
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * version. 
	 * 
	 * @return  the version 
	 * @since   JDK 1.8
	 */
	public String getVersion() {
		return version;
	}

	/** 
	 * version. 
	 * 
	 * @param   version    the version to set 
	 * @since   JDK 1.8
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/** 
	 * owner. 
	 * 
	 * @return  the owner 
	 * @since   JDK 1.8
	 */
	public String getOwner() {
		return owner;
	}

	/** 
	 * owner. 
	 * 
	 * @param   owner    the owner to set 
	 * @since   JDK 1.8
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/** 
	 * group. 
	 * 
	 * @return  the group 
	 * @since   JDK 1.8
	 */
	public String getGroup() {
		return group;
	}

	/** 
	 * group. 
	 * 
	 * @param   group    the group to set 
	 * @since   JDK 1.8
	 */
	public void setGroup(String group) {
		this.group = group;
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
		return "Reference [id=" + id + ", fullyQualifiedName=" + fullyQualifiedName + ", name=" + name + ", version="
				+ version + ", owner=" + owner + ", group=" + group + "]";
	}

	
	

}
