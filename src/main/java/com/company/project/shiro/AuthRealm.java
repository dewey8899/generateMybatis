package com.company.project.shiro;

import com.company.project.dao.TbUserMapper;
import com.company.project.dao.TbUserRoleMapper;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author dewey.du
 * @create 2020/4/1 0001
 */

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private TbUserRoleMapper userRoleMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        TbUser user = (TbUser)principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        TbUserRole userRole = new TbUserRole();
        userRole.setUid(user.getUid());
        List<TbUserRole> userRoleList = userRoleMapper.select(userRole);
        if (userRoleList.size()==0) return null;
        List<String> permissionList = Lists.newArrayList();
        for (TbUserRole tbUserRole : userRoleList) {

        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return null;
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
