package com.ccffee.coolQHttpServer.mbg.mapper;

import com.ccffee.coolQHttpServer.mbg.model.CqGroup;
import com.ccffee.coolQHttpServer.mbg.model.CqGroupExample;
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

public interface CqGroupMapper {
    @SelectProvider(type=CqGroupSqlProvider.class, method="countByExample")
    int countByExample(CqGroupExample example);

    @DeleteProvider(type=CqGroupSqlProvider.class, method="deleteByExample")
    int deleteByExample(CqGroupExample example);

    @Delete({
        "delete from cq_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cq_group (num, connect)",
        "values (#{num,jdbcType=VARCHAR}, #{connect,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CqGroup record);

    @InsertProvider(type=CqGroupSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(CqGroup record);

    @SelectProvider(type=CqGroupSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="num", property="num", jdbcType=JdbcType.VARCHAR),
        @Result(column="connect", property="connect", jdbcType=JdbcType.INTEGER)
    })
    List<CqGroup> selectByExample(CqGroupExample example);

    @Select({
        "select",
        "id, num, connect",
        "from cq_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="num", property="num", jdbcType=JdbcType.VARCHAR),
        @Result(column="connect", property="connect", jdbcType=JdbcType.INTEGER)
    })
    CqGroup selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CqGroupSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CqGroup record, @Param("example") CqGroupExample example);

    @UpdateProvider(type=CqGroupSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CqGroup record, @Param("example") CqGroupExample example);

    @UpdateProvider(type=CqGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CqGroup record);

    @Update({
        "update cq_group",
        "set num = #{num,jdbcType=VARCHAR},",
          "connect = #{connect,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CqGroup record);
}