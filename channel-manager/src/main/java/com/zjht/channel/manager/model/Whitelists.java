package com.zjht.channel.manager.model;

import java.util.Date;

import com.zjht.channel.manager.common.annotation.FieldSpecification;

public class Whitelists {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_whitelists.wl_id
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_whitelists.wl_ip
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    @FieldSpecification(required=true,name="IP地址",maxLength=15)
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_whitelists.wl_hostname
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    private String hostname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_whitelists.wl_update_time
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column channel_whitelists.wl_remark
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_whitelists.wl_id
     *
     * @return the value of channel_whitelists.wl_id
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_whitelists.wl_id
     *
     * @param id the value for channel_whitelists.wl_id
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public Whitelists setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_whitelists.wl_ip
     *
     * @return the value of channel_whitelists.wl_ip
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_whitelists.wl_ip
     *
     * @param ip the value for channel_whitelists.wl_ip
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_whitelists.wl_hostname
     *
     * @return the value of channel_whitelists.wl_hostname
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_whitelists.wl_hostname
     *
     * @param hostname the value for channel_whitelists.wl_hostname
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_whitelists.wl_update_time
     *
     * @return the value of channel_whitelists.wl_update_time
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_whitelists.wl_update_time
     *
     * @param updateTime the value for channel_whitelists.wl_update_time
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public Whitelists setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column channel_whitelists.wl_remark
     *
     * @return the value of channel_whitelists.wl_remark
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column channel_whitelists.wl_remark
     *
     * @param remark the value for channel_whitelists.wl_remark
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}