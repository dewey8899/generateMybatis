package com.company.project.service.impl;

import com.company.project.dao.TbPermissionMapper;
import com.company.project.model.TbPermission;
import com.company.project.service.TbPermissionService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/04/01.
 */
@Service
@Transactional
public class TbPermissionServiceImpl extends AbstractService<TbPermission> implements TbPermissionService {
    @Resource
    private TbPermissionMapper tbPermissionMapper;

}
