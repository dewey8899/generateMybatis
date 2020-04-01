package com.company.project.service.impl;

import com.company.project.dao.TbUserRoleMapper;
import com.company.project.model.TbUserRole;
import com.company.project.service.TbUserRoleService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/04/01.
 */
@Service
@Transactional
public class TbUserRoleServiceImpl extends AbstractService<TbUserRole> implements TbUserRoleService {
    @Resource
    private TbUserRoleMapper tbUserRoleMapper;

}
