package com.company.project.service.impl;

import com.company.project.dao.TbReportOemProductDifferenceMapper;
import com.company.project.model.TbReportOemProductDifference;
import com.company.project.service.TbReportOemProductDifferenceService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by dewey on 2020/03/13.
 */
@Service
@Transactional
public class TbReportOemProductDifferenceServiceImpl extends AbstractService<TbReportOemProductDifference> implements TbReportOemProductDifferenceService {
    @Resource
    private TbReportOemProductDifferenceMapper tbReportOemProductDifferenceMapper;

}
