package cn.niudehua.pet.mapper;

import cn.niudehua.pet.pojo.Dog;
import cn.niudehua.pet.pojo.DogCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DogMapper {
    int countByExample(DogCriteria example);

    int deleteByExample(DogCriteria example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Dog record);

    int insertSelective(Dog record);

    List<Dog> selectByExample(DogCriteria example);

    Dog selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Dog record, @Param("example") DogCriteria example);

    int updateByExample(@Param("record") Dog record, @Param("example") DogCriteria example);

    int updateByPrimaryKeySelective(Dog record);

    int updateByPrimaryKey(Dog record);

    List<Dog> selectByUserId(Integer id);
}