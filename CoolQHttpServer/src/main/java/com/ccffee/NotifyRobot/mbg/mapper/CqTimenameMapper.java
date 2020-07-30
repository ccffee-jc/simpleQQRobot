package com.ccffee.NotifyRobot.mbg.mapper;

import com.ccffee.NotifyRobot.mbg.model.CqTimename;
import com.ccffee.NotifyRobot.mbg.model.CqTimenameExample;
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

public interface CqTimenameMapper {
    @SelectProvider(type=CqTimenameSqlProvider.class, method="countByExample")
    int countByExample(CqTimenameExample example);

    @DeleteProvider(type=CqTimenameSqlProvider.class, method="deleteByExample")
    int deleteByExample(CqTimenameExample example);

    @Delete({
        "delete from cq_timeName",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cq_timeName (timeName)",
        "values (#{timename,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CqTimename record);

    @InsertProvider(type=CqTimenameSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(CqTimename record);

    @SelectProvider(type=CqTimenameSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="timeName", property="timename", jdbcType=JdbcType.VARCHAR)
    })
    List<CqTimename> selectByExample(CqTimenameExample example);

    @Select({
        "select",
        "id, timeName",
        "from cq_timeName",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="timeName", property="timename", jdbcType=JdbcType.VARCHAR)
    })
    CqTimename selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CqTimenameSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CqTimename record, @Param("example") CqTimenameExample example);

    @UpdateProvider(type=CqTimenameSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CqTimename record, @Param("example") CqTimenameExample example);

    @UpdateProvider(type=CqTimenameSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CqTimename record);

    @Update({
        "update cq_timeName",
        "set timeName = #{timename,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CqTimename record);
}