package com.zjht.channel.manager.dao;

import com.zjht.channel.manager.model.Whitelists;
import com.zjht.channel.manager.model.WhitelistsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhitelistsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int countByExample(WhitelistsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int deleteByExample(WhitelistsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int insert(Whitelists record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int insertSelective(Whitelists record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    List<Whitelists> selectByExample(WhitelistsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    Whitelists selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int updateByExampleSelective(@Param("record") Whitelists record, @Param("example") WhitelistsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int updateByExample(@Param("record") Whitelists record, @Param("example") WhitelistsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int updateByPrimaryKeySelective(Whitelists record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_whitelists
     *
     * @mbggenerated Thu Sep 17 16:16:46 CST 2015
     */
    int updateByPrimaryKey(Whitelists record);
}