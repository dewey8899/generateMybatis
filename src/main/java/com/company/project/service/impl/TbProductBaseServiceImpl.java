package com.company.project.service.impl;

import com.company.project.dao.TbProductBaseMapper;
import com.company.project.model.TbProductBase;
import com.company.project.service.TbProductBaseService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/04/09.
 */
@Service
@Transactional
public class TbProductBaseServiceImpl extends AbstractService<TbProductBase> implements TbProductBaseService {
    @Resource
    private TbProductBaseMapper tbProductBaseMapper;

}
