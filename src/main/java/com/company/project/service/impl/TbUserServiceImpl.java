package com.company.project.service.impl;

import com.company.project.dao.TbUserMapper;
import com.company.project.model.TbUser;
import com.company.project.service.TbUserService;
import com.company.project.core.AbstractService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


/**
 * Created by dewey on 2020/04/01.
 */
@Service
@Transactional
public class TbUserServiceImpl extends AbstractService<TbUser> implements TbUserService {
    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    @Async
    public Future <List<TbUser>> userList(Integer id) throws InterruptedException {
        TbUser user = new TbUser();
        user.setUid(id);
        Thread.sleep(5000L);
        List<TbUser> select = tbUserMapper.select(user);
        return new AsyncResult<>(select);
    }

}
