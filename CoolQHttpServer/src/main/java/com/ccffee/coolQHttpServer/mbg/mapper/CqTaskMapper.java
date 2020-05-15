package com.ccffee.coolQHttpServer.mbg.mapper;

import com.ccffee.coolQHttpServer.mbg.model.CqTask;
import com.ccffee.coolQHttpServer.mbg.model.CqTaskExample;
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

public interface CqTaskMapper {
    @SelectProvider(type=CqTaskSqlProvider.class, method="countByExample")
    int countByExample(CqTaskExample example);

    @DeleteProvider(type=CqTaskSqlProvider.class, method="deleteByExample")
    int deleteByExample(CqTaskExample example);

    @Delete({
        "delete from cq_task",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cq_task (targeId, targeType, ",
        "message)",
        "values (#{targeid,jdbcType=INTEGER}, #{targetype,jdbcType=VARCHAR}, ",
        "#{message,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CqTask record);

    @InsertProvider(type=CqTaskSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(CqTask record);

    @SelectProvider(type=CqTaskSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="targeId", property="targeid", jdbcType=JdbcType.INTEGER),
        @Result(column="targeType", property="targetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="message", property="message", jdbcType=JdbcType.VARCHAR)
    })
    List<CqTask> selectByExample(CqTaskExample example);

    @Select({
        "select",
        "id, targeId, targeType, message",
        "from cq_task",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="targeId", property="targeid", jdbcType=JdbcType.INTEGER),
        @Result(column="targeType", property="targetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="message", property="message", jdbcType=JdbcType.VARCHAR)
    })
    CqTask selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CqTaskSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CqTask record, @Param("example") CqTaskExample example);

    @UpdateProvider(type=CqTaskSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CqTask record, @Param("example") CqTaskExample example);

    @UpdateProvider(type=CqTaskSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CqTask record);

    @Update({
        "update cq_task",
        "set targeId = #{targeid,jdbcType=INTEGER},",
          "targeType = #{targetype,jdbcType=VARCHAR},",
          "message = #{message,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CqTask record);
}