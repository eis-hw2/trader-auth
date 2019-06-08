package com.example.traderauth.Service.Impl;

import com.example.traderauth.Dao.Repo.BrokerDao;
import com.example.traderauth.Domain.Entity.Broker;
import com.example.traderauth.Service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerServiceImpl implements BrokerService {

    @Autowired
    private BrokerDao brokerDao;

    @Override
    public List<Broker> findAll(){
        return brokerDao.findAll();
    }

    @Override
    public Broker findById(Integer id) {
        return brokerDao.findById(id).get();
    }
}
