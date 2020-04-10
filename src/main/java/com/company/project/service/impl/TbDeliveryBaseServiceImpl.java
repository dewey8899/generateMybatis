package com.company.project.service.impl;

import com.company.project.dao.TbDeliveryBaseMapper;
import com.company.project.model.TbDeliveryBase;
import com.company.project.service.TbDeliveryBaseService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/04/09.
 */
@Service
@Transactional
public class TbDeliveryBaseServiceImpl extends AbstractService<TbDeliveryBase> implements TbDeliveryBaseService {
    @Resource
    private TbDeliveryBaseMapper tbDeliveryBaseMapper;

}
