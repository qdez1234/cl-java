package com.ruoyi.module.sysUser;

import com.ruoyi.module.sysUser.domain.SyUser;

import java.util.List;

public interface SyUserService {

    /*
    * 查询用户列表
    * */
    public List<SyUser> getUserList(SyUser user);


    /*
    * 新增用户数据
    * */
    public int insertUser(SyUser user);

    /*
    *  教研用户名是否唯一
    * */
    public boolean checkUserNameUnique(SyUser user);

    public boolean checkPhoneUnique(SyUser user);

    public boolean checkEmailUnique(SyUser user);



}
