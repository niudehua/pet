package cn.niudehua.pet.service;


import cn.niudehua.pet.pojo.Dog;
import cn.niudehua.pet.pojo.PageBean;
import cn.niudehua.pet.pojo.User;
import cn.niudehua.pet.pojo.UserCriteria;

public interface UserService {
    /**
     * 分页查询
     *
     * @param userCriteria userCriteria
     * @return PageBean
     */
    PageBean getAllUsers(UserCriteria userCriteria);

    void batchDelete(Long[] ids);

    void addUser(User user);

    User getUserById(Integer id);

    void updateUser(User user);

    User login(String username, String password);

    Dog getDog(Integer cid);
}
