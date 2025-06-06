package com.ruoyi.module.sysUser;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.module.sysUser.domain.SyUser;
import com.ruoyi.module.sysUser.mapper.SyUserMapper;
import com.ruoyi.module.sysUserRole.domain.SyUserRole;
import com.ruoyi.module.sysUserRole.mapper.SyUserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SyUserviceImpl implements SyUserService{
    private static final Logger log = LoggerFactory.getLogger(SyUserviceImpl.class);

    @Autowired
    private SyUserMapper syUserMapper;

    @Autowired
    private SyUserRoleMapper syUserRoleMapper;
    /*
    * 根据分页查询用户列表
    * */
    @Override
    public List<SyUser> getUserList(SyUser user) {
         return syUserMapper.getUserList(user);
    }

    /*
    * 新增用户
    * */
    @Override
    public int insertUser(SyUser user) {
        int row = syUserMapper.insertUser(user);
        insertUserRole(user);
        return row;
    }

    /*
    * 新增用户管理
    * */
    public void insertUserRole(SyUser user) {
        Long userId = user.getUserId();
        Long[] roleIds= user.getRoleIds();
        if(roleIds != null && roleIds.length > 0){
            List<SyUserRole> list = new ArrayList<>(roleIds.length);
            for(Long roleId : roleIds){
                SyUserRole ur = new SyUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            syUserRoleMapper.batchUserRole(list);
        }
    }
    /*
    * 校验用户名唯一性
    * */
    @Override
    public boolean checkUserNameUnique(SyUser user) {
        Long userId = user.getUserId()!=null?user.getUserId():-1L;
        SyUser info = syUserMapper.checkUserNameUnique(user.getUserName());
        if(info != null && info.getUserId().longValue()!=userId.longValue()){
            return false;
        }
        return  true;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public boolean checkPhoneUnique(SyUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SyUser info = syUserMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return false;
        }
        return true;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public boolean checkEmailUnique(SyUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SyUser info = syUserMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
