package com.ccffee.coolQHttpServer.mbg.mapper;

import com.ccffee.coolQHttpServer.mbg.model.CqTime;
import com.ccffee.coolQHttpServer.mbg.model.CqTimeExample;
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

public interface CqTimeMapper {
    @SelectProvider(type=CqTimeSqlProvider.class, method="countByExample")
    int countByExample(CqTimeExample example);

    @DeleteProvider(type=CqTimeSqlProvider.class, method="deleteByExample")
    int deleteByExample(CqTimeExample example);

    @Delete({
        "delete from cq_time",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cq_time (timeDay, timeHour, ",
        "taskId, status)",
        "values (#{timeday,jdbcType=VARCHAR}, #{timehour,jdbcType=VARCHAR}, ",
        "#{taskid,jdbcType=INTEGER}, #{status,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CqTime record);

    @InsertProvider(type=CqTimeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(CqTime record);

    @SelectProvider(type=CqTimeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="timeDay", property="timeday", jdbcType=JdbcType.VARCHAR),
        @Result(column="timeHour", property="timehour", jdbcType=JdbcType.VARCHAR),
        @Result(column="taskId", property="taskid", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<CqTime> selectByExample(CqTimeExample example);

    @Select({
        "select",
        "id, timeDay, timeHour, taskId, status",
        "from cq_time",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="timeDay", property="timeday", jdbcType=JdbcType.VARCHAR),
        @Result(column="timeHour", property="timehour", jdbcType=JdbcType.VARCHAR),
        @Result(column="taskId", property="taskid", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    CqTime selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CqTimeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CqTime record, @Param("example") CqTimeExample example);

    @UpdateProvider(type=CqTimeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CqTime record, @Param("example") CqTimeExample example);

    @UpdateProvider(type=CqTimeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CqTime record);

    @Update({
        "update cq_time",
        "set timeDay = #{timeday,jdbcType=VARCHAR},",
          "timeHour = #{timehour,jdbcType=VARCHAR},",
          "taskId = #{taskid,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CqTime record);
}