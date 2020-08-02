package com.ccffee.NotifyRobot.mbg.mapper;

import com.ccffee.NotifyRobot.mbg.model.CqUser;
import com.ccffee.NotifyRobot.mbg.model.CqUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CqUserMapper {
    @SelectProvider(type=CqUserSqlProvider.class, method="countByExample")
    int countByExample(CqUserExample example);

    @DeleteProvider(type=CqUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(CqUserExample example);

    @Delete({
        "delete from cq_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cq_user (qqNum, name, ",
        "saveImageStatus)",
        "values (#{qqnum,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{saveimagestatus,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CqUser record);

    @InsertProvider(type=CqUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(CqUser record);

    @SelectProvider(type=CqUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="qqNum", property="qqnum", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="saveImageStatus", property="saveimagestatus", jdbcType=JdbcType.INTEGER)
    })
    List<CqUser> selectByExample(CqUserExample example);

    @Select({
        "select",
        "id, qqNum, name, saveImageStatus",
        "from cq_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="qqNum", property="qqnum", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="saveImageStatus", property="saveimagestatus", jdbcType=JdbcType.INTEGER)
    })
    CqUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CqUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CqUser record, @Param("example") CqUserExample example);

    @UpdateProvider(type=CqUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CqUser record, @Param("example") CqUserExample example);

    @UpdateProvider(type=CqUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CqUser record);

    @Update({
        "update cq_user",
        "set qqNum = #{qqnum,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "saveImageStatus = #{saveimagestatus,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CqUser record);
}