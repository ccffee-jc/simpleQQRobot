package com.ccffee.NotifyRobot.mbg.mapper;

import com.ccffee.NotifyRobot.mbg.model.CqImage;
import com.ccffee.NotifyRobot.mbg.model.CqImageExample;
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

public interface CqImageMapper {
    @SelectProvider(type=CqImageSqlProvider.class, method="countByExample")
    int countByExample(CqImageExample example);

    @DeleteProvider(type=CqImageSqlProvider.class, method="deleteByExample")
    int deleteByExample(CqImageExample example);

    @Delete({
        "delete from cq_image",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cq_image (url)",
        "values (#{url,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CqImage record);

    @InsertProvider(type=CqImageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(CqImage record);

    @SelectProvider(type=CqImageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR)
    })
    List<CqImage> selectByExample(CqImageExample example);

    @Select({
        "select",
        "id, url",
        "from cq_image",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR)
    })
    CqImage selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CqImageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CqImage record, @Param("example") CqImageExample example);

    @UpdateProvider(type=CqImageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CqImage record, @Param("example") CqImageExample example);

    @UpdateProvider(type=CqImageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CqImage record);

    @Update({
        "update cq_image",
        "set url = #{url,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CqImage record);
}