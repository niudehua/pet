package cn.niudehua.pet.service.impl;


import cn.niudehua.pet.mapper.DogMapper;
import cn.niudehua.pet.mapper.UserMapper;
import cn.niudehua.pet.pojo.Dog;
import cn.niudehua.pet.pojo.PageBean;
import cn.niudehua.pet.pojo.User;
import cn.niudehua.pet.pojo.UserCriteria;
import cn.niudehua.pet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    DogMapper dogMapper;

    @Override
    //获取分页
    public PageBean getAllUsers(UserCriteria userCriteria) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrPage(userCriteria.getPageNo());
        pageBean.setPageSize(userCriteria.getPageSize());
        int count = userMapper.countByExample(userCriteria);
        pageBean.setTotalCount(count);
        Double totalPage = Math.ceil((double) count / userCriteria.getPageSize());
        pageBean.setTotalPage(totalPage.intValue());
        List<User> list = userMapper.selectByPage(userCriteria);
        pageBean.setList(list);
        return pageBean;
    }


    @Override
    public void batchDelete(Long[] ids) {
        userMapper.batchDelete(ids);
    }

    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User login(String username, String password) {
        UserCriteria userCriteria = new UserCriteria();
        UserCriteria.Criteria criteria = userCriteria.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userCriteria);
        return users.get(0);
    }

    @Override
    public Dog getDog(Integer cid) {
        Dog dog = dogMapper.selectByPrimaryKey(cid);
        return dog;
    }
}
