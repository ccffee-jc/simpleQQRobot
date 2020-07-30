package com.ccffee.NotifyRobot.mbg.mapper;

import com.ccffee.NotifyRobot.mbg.model.CqGroupmessage;
import com.ccffee.NotifyRobot.mbg.model.CqGroupmessageExample;
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

public interface CqGroupmessageMapper {
    @SelectProvider(type=CqGroupmessageSqlProvider.class, method="countByExample")
    int countByExample(CqGroupmessageExample example);

    @DeleteProvider(type=CqGroupmessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(CqGroupmessageExample example);

    @Delete({
        "delete from cq_GroupMessage",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cq_GroupMessage (user_id, group_id, ",
        "time, message)",
        "values (#{userId,jdbcType=INTEGER}, #{groupId,jdbcType=INTEGER}, ",
        "#{time,jdbcType=TIMESTAMP}, #{message,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CqGroupmessage record);

    @InsertProvider(type=CqGroupmessageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(CqGroupmessage record);

    @SelectProvider(type=CqGroupmessageSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="message", property="message", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<CqGroupmessage> selectByExampleWithBLOBs(CqGroupmessageExample example);

    @SelectProvider(type=CqGroupmessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CqGroupmessage> selectByExample(CqGroupmessageExample example);

    @Select({
        "select",
        "id, user_id, group_id, time, message",
        "from cq_GroupMessage",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="message", property="message", jdbcType=JdbcType.LONGVARCHAR)
    })
    CqGroupmessage selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CqGroupmessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CqGroupmessage record, @Param("example") CqGroupmessageExample example);

    @UpdateProvider(type=CqGroupmessageSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") CqGroupmessage record, @Param("example") CqGroupmessageExample example);

    @UpdateProvider(type=CqGroupmessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CqGroupmessage record, @Param("example") CqGroupmessageExample example);

    @UpdateProvider(type=CqGroupmessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CqGroupmessage record);

    @Update({
        "update cq_GroupMessage",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "group_id = #{groupId,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "message = #{message,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(CqGroupmessage record);

    @Update({
        "update cq_GroupMessage",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "group_id = #{groupId,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CqGroupmessage record);
}