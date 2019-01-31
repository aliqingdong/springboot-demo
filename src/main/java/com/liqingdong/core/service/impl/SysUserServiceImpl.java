package com.liqingdong.core.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liqingdong.core.entity.SysUser;
import com.liqingdong.core.mapper.SysUserMapper;
import com.liqingdong.core.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户 服务实现类
 *
 * @author auto generate
 * @since 2019-01-29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findUserByUsername(String userName) {
        SysUser param = new SysUser();
        param.setUsername(userName);
        return baseMapper.selectOne(param);
    }
}
