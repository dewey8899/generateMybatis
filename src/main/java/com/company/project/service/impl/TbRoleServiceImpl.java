package com.company.project.service.impl;

import com.company.project.dao.TbRoleMapper;
import com.company.project.model.TbRole;
import com.company.project.service.TbRoleService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/04/01.
 */
@Service
@Transactional
public class TbRoleServiceImpl extends AbstractService<TbRole> implements TbRoleService {
    @Resource
    private TbRoleMapper tbRoleMapper;

}
