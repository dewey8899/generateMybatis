package com.company.project.shiro;

import com.company.project.dao.*;
import com.company.project.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
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
    private TbRoleMapper roleMapper;

    @Autowired
    private TbPermissionRoleMapper permissionRoleMapper;
    @Autowired
    private TbPermissionMapper permissionMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        TbUser user = (TbUser)principalCollection.fromRealm(this.getClass().getName()).iterator().next();//方法一获取用户
        TbUser user = (TbUser)principalCollection.getPrimaryPrincipal();//方法二获取用户
        TbUserRole userRole = new TbUserRole();
        userRole.setUid(user.getUid());
        List<TbUserRole> userRoleList = userRoleMapper.select(userRole);
        if (userRoleList.size()==0) return null;
        Set<Integer> roleIdSet = userRoleList.stream().map(TbUserRole::getRid).collect(Collectors.toSet());
        Condition conditionRole = new Condition(TbRole.class);
        conditionRole.createCriteria().andIn("rid",roleIdSet);
        List<TbRole> tbRoles = roleMapper.selectByExample(conditionRole);
        Set<String> roleNameList = tbRoles.stream().map(TbRole::getRname).collect(Collectors.toSet());
        Condition condition = new Condition(TbPermissionRole.class);
        condition.createCriteria().andIn("rid",roleIdSet);
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
        //赋予权限
        info.addStringPermissions(permissionList);
        //赋予角色
        info.addRoles(roleNameList);
        return info;
    }

    //认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        TbUser user = new TbUser();
        user.setUsername(username);
        List<TbUser> select = userMapper.select(user);
        //返回null -> shiro就会知道这是用户不存在的异常
        if(CollectionUtils.isEmpty(select)){
            return null;
        }
        user = select.get(0);
        // 验证密码 【注：这里不采用shiro自身密码验证 ， 采用的话会导致用户登录密码错误时，已登录的账号也会自动下线！  如果采用，移除下面的清除缓存到登录处 处理】
        if (!password.equals(user.getPasword())){
            throw new IncorrectCredentialsException("用户名或者密码错误");
        }
        // 判断账号是否被冻结(如果数据库设置冻结属性)
//        if (user.getFlag()==null|| "0".equals(user.getFlag())){
//            throw new LockedAccountException();
//        }
        /**
         * 进行验证 -> 注：shiro会自动验证密码
         * 参数1：principal -> 放对象就可以在页面任意地方拿到该对象里面的值
         * 参数2：hashedCredentials -> 密码
         * 参数3：credentialsSalt -> 设置盐值
         * 参数4：realmName -> 自定义的Realm
         */
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPasword(), ByteSource.Util.bytes(user.getSalt()), getName());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPasword(), this.getClass().getName());
        return info;
    }
}
