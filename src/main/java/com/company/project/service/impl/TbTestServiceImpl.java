package com.company.project.service.impl;

import com.company.project.dao.TbTestMapper;
import com.company.project.model.TbTest;
import com.company.project.service.TbTestService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/03/13.
 */
@Service
@Transactional
public class TbTestServiceImpl extends AbstractService<TbTest> implements TbTestService {
    @Resource
    private TbTestMapper tbTestMapper;

}
