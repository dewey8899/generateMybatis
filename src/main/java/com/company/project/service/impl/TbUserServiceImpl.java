package com.company.project.service.impl;

import com.company.project.dao.TbUserMapper;
import com.company.project.model.TbUser;
import com.company.project.service.TbUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/04/01.
 */
@Service
@Transactional
public class TbUserServiceImpl extends AbstractService<TbUser> implements TbUserService {
    @Resource
    private TbUserMapper tbUserMapper;

}
