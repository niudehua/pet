package cn.niudehua.pet.controller;

import cn.niudehua.pet.pojo.Dog;
import cn.niudehua.pet.pojo.PageBean;
import com.alibaba.fastjson.JSONObject;
import cn.niudehua.pet.pojo.User;
import cn.niudehua.pet.pojo.UserCriteria;
import cn.niudehua.pet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@MultipartConfig
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    /**
     * 获取所有用户
     *
     * @param model
     * @param user
     * @param currPage
     * @return
     */
    @RequestMapping("/getAllUsers")
    public String getAllUsers(Model model, User user, Integer currPage) {
        UserCriteria userCriteria = new UserCriteria();
        UserCriteria.Criteria criteria = userCriteria.createCriteria();
        if (null != user.getNickname() && "" != user.getNickname()) {
            criteria.andNicknameLike("%" + user.getNickname() + "%");
        }
        userCriteria.setPageNo(null == currPage ? 1 : currPage);
        PageBean pageBean = userService.getAllUsers(userCriteria);
        model.addAttribute("pageBean", pageBean);
        return "userList";
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public String toAdd() {
        return "addUser";
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public String batchDeleteSave(Long[] ids) {
        userService.batchDelete(ids);
        return "redirect:/getAllUsers.do";
    }

    /**
     * 上传图片
     *
     * @param picfile  文件
     * @param response
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("/uploadImg")
    public void uploadImg(MultipartFile picfile, HttpServletRequest request, HttpServletResponse response)
            throws IllegalStateException, IOException {
        response.setCharacterEncoding("utf-8");
        // 1. 获得图片的名称
        String filename = picfile.getOriginalFilename();
        // 拿到上传图片扩展名
        String ext = filename.substring(filename.lastIndexOf("."));
        String newName = UUID.randomUUID().toString().replace("-", "") + ext;
        // 2. 把图片上传到服务器F:\IdeaProject\exam\web\WEB-INF\imgs
        String url = request.getSession().getServletContext().getContextPath()+("/upload/");
        picfile.transferTo(new File(url + newName));
        // 3.把图片的路径使用json的格式进行返回
        JSONObject jo = new JSONObject();
        jo.put("path", "/upload/" + newName);
        response.getWriter().write(jo.toString());
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/getAllUsers.do";
    }

    /**
     * 跳转修改页面并回显数据
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/toUpdate")
    public String userEdit(Model model, @RequestParam(value = "id", defaultValue = "1") Integer id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/getAllUsers.do";
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        session.setAttribute("user", user);

        return "index";
    }

    /**
     * 查看宠物
     *
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping("/getDog")
    public String getCat(Integer cid, Model model) {
        Dog dog = userService.getDog(cid);
        model.addAttribute("dog", dog);
        return "showCat";
    }
}