package com.ruoyi.module.sysUser;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.module.sysUser.domain.SyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/user")
public class SyUserController extends BaseController {

    @Autowired
    private SyUserService syUserService;
    @GetMapping("/list")
    public TableDataInfo list(SyUser syUser) {
        startPage();
        List<SyUser> list = syUserService.getUserList(syUser);
        return getDataTable(list);
    }

    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SyUser user) {
        if(!syUserService.checkUserNameUnique(user)){
            return error("新增用户" + user.getUserName() + "已经存在");
        }
        else if(StringUtils.isNotEmpty(user.getPhonenumber()) && !syUserService.checkPhoneUnique(user)){
            return error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail()) && !syUserService.checkEmailUnique(user))
        {
            return error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(syUserService.insertUser(user));
    }


}
