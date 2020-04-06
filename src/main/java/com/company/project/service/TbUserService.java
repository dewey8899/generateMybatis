package com.company.project.service;
import com.company.project.model.TbUser;
import com.company.project.core.Service;

import java.util.List;
import java.util.concurrent.Future;


/**
 * Created by dewey on 2020/04/01.
 */
public interface TbUserService extends Service<TbUser> {
    Future<List<TbUser>> userList(Integer id) throws InterruptedException;
}
