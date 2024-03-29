package com.zjht.channel.manager.model;

import java.util.Date;

public class Permission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_permission.per_id
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_permission.per_appno
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    private String appno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_permission.per_service_name
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    private String serviceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_permission.per_service_version
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    private String serviceVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_permission.per_update_time
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_permission.per_remark
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_permission.per_id
     *
     * @return the value of channel_permission.per_id
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_permission.per_id
     *
     * @param id the value for channel_permission.per_id
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public Permission setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_permission.per_appno
     *
     * @return the value of channel_permission.per_appno
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public String getAppno() {
        return appno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_permission.per_appno
     *
     * @param appno the value for channel_permission.per_appno
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public void setAppno(String appno) {
        this.appno = appno == null ? null : appno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_permission.per_service_name
     *
     * @return the value of channel_permission.per_service_name
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_permission.per_service_name
     *
     * @param serviceName the value for channel_permission.per_service_name
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_permission.per_service_version
     *
     * @return the value of channel_permission.per_service_version
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public String getServiceVersion() {
        return serviceVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_permission.per_service_version
     *
     * @param serviceVersion the value for channel_permission.per_service_version
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion == null ? null : serviceVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_permission.per_update_time
     *
     * @return the value of channel_permission.per_update_time
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_permission.per_update_time
     *
     * @param updateTime the value for channel_permission.per_update_time
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_permission.per_remark
     *
     * @return the value of channel_permission.per_remark
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_permission.per_remark
     *
     * @param remark the value for channel_permission.per_remark
     *
     * @mbggenerated Thu Sep 17 21:08:20 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}