package com.ruoyi.module.sysUser.mapper;

import com.ruoyi.module.sysUser.domain.SyUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SyUserMapper {
    /*
     * 根据分页查询用户列表
     * */
    public List<SyUser> getUserList(SyUser user);

    /*
     * 根据分页查询用户列表
     * */
    public int insertUser(SyUser user);

    /*
    * 教研用户名是否唯一
     * */
    public SyUser checkUserNameUnique(String userName);

    public SyUser checkPhoneUnique(String phonenumber);

    public SyUser checkEmailUnique(String email);


}
