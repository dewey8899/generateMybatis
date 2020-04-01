package com.company.project.service.impl;

import com.company.project.dao.TbPermissionRoleMapper;
import com.company.project.model.TbPermissionRole;
import com.company.project.service.TbPermissionRoleService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/04/01.
 */
@Service
@Transactional
public class TbPermissionRoleServiceImpl extends AbstractService<TbPermissionRole> implements TbPermissionRoleService {
    @Resource
    private TbPermissionRoleMapper tbPermissionRoleMapper;

}
