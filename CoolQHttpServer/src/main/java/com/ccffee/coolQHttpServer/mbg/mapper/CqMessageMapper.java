package com.ccffee.coolQHttpServer.mbg.mapper;

import com.ccffee.coolQHttpServer.mbg.model.CqMessage;
import com.ccffee.coolQHttpServer.mbg.model.CqMessageExample;
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

public interface CqMessageMapper {
    @SelectProvider(type=CqMessageSqlProvider.class, method="countByExample")
    int countByExample(CqMessageExample example);

    @DeleteProvider(type=CqMessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(CqMessageExample example);

    @Delete({
        "delete from cq_message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cq_message (user_id, group_id, ",
        "time, message)",
        "values (#{userId,jdbcType=INTEGER}, #{groupId,jdbcType=INTEGER}, ",
        "#{time,jdbcType=TIMESTAMP}, #{message,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CqMessage record);

    @InsertProvider(type=CqMessageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(CqMessage record);

    @SelectProvider(type=CqMessageSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="message", property="message", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<CqMessage> selectByExampleWithBLOBs(CqMessageExample example);

    @SelectProvider(type=CqMessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CqMessage> selectByExample(CqMessageExample example);

    @Select({
        "select",
        "id, user_id, group_id, time, message",
        "from cq_message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="message", property="message", jdbcType=JdbcType.LONGVARCHAR)
    })
    CqMessage selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CqMessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CqMessage record, @Param("example") CqMessageExample example);

    @UpdateProvider(type=CqMessageSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") CqMessage record, @Param("example") CqMessageExample example);

    @UpdateProvider(type=CqMessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CqMessage record, @Param("example") CqMessageExample example);

    @UpdateProvider(type=CqMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CqMessage record);

    @Update({
        "update cq_message",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "group_id = #{groupId,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "message = #{message,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(CqMessage record);

    @Update({
        "update cq_message",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "group_id = #{groupId,jdbcType=INTEGER},",
          "time = #{time,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CqMessage record);
}