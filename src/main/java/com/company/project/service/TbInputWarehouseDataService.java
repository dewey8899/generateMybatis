package com.company.project.service;
import com.company.project.model.TbInputWarehouseData;
import com.company.project.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by dewey on 2020/04/09.
 */
public interface TbInputWarehouseDataService extends Service<TbInputWarehouseData> {
    Map<String,List<TbInputWarehouseData>> download();
}
