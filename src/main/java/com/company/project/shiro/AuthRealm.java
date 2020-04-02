package com.company.project.shiro;

import com.company.project.dao.TbPermissionMapper;
import com.company.project.dao.TbPermissionRoleMapper;
import com.company.project.dao.TbUserMapper;
import com.company.project.dao.TbUserRoleMapper;
import com.company.project.model.TbPermission;
import com.company.project.model.TbPermissionRole;
import com.company.project.model.TbUser;
import com.company.project.model.TbUserRole;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dewey.du
 * @create 2020/4/1 0001
 */

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private TbUserRoleMapper userRoleMapper;

    @Autowired
    private TbPermissionRoleMapper permissionRoleMapper;
    @Autowired
    private TbPermissionMapper permissionMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        TbUser user = (TbUser)principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        TbUserRole userRole = new TbUserRole();
        userRole.setUid(user.getUid());
        List<TbUserRole> userRoleList = userRoleMapper.select(userRole);
        if (userRoleList.size()==0) return null;
        Set<Integer> roleIdSet = userRoleList.stream().map(TbUserRole::getRid).collect(Collectors.toSet());
        Condition condition = new Condition(TbPermissionRole.class);
        condition.createCriteria().andIn("id",roleIdSet);
        List<TbPermissionRole> tbPermissionRoles = permissionRoleMapper.selectByExample(condition);
        if (tbPermissionRoles.size() ==0 ){
            return null;
        }
        Set<Integer> pidSet = tbPermissionRoles.stream().map(TbPermissionRole::getPid).collect(Collectors.toSet());
        Condition permissionExample = new Condition(TbPermission.class);
        permissionExample.createCriteria().andIn("pid", pidSet);
        List<TbPermission> tbPermissions = permissionMapper.selectByExample(permissionExample);
        if (tbPermissions.size() ==0 ){
            return null;
        }
        Set<String> permissionList = tbPermissions.stream().map(TbPermission::getPname).collect(Collectors.toSet());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return info;
    }

    //认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        TbUser user = new TbUser();
        user.setUsername(username);
        List<TbUser> select = userMapper.select(user);
        if (CollectionUtils.isNotEmpty(select)){
            user = select.get(0);
        }
        return new SimpleAuthenticationInfo(user,user.getPasword(),this.getClass().getName());
    }
}
