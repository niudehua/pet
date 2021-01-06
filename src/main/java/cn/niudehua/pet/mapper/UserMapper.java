package cn.niudehua.pet.mapper;

import cn.niudehua.pet.pojo.User;
import cn.niudehua.pet.pojo.UserCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int countByExample(UserCriteria example);

    int deleteByExample(UserCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserCriteria example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserCriteria example);

    int updateByExample(@Param("record") User record, @Param("example") UserCriteria example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //分页查找
    List<User> selectByPage(UserCriteria userCriteria);

    //批量删除用户
    void batchDelete(Long[] ids);
}