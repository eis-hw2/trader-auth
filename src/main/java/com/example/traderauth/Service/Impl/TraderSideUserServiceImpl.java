package com.example.traderauth.Service.Impl;

import com.example.traderauth.Dao.Repo.TraderSideUserDao;
import com.example.traderauth.Domain.Entity.BrokerSideUser;
import com.example.traderauth.Domain.Entity.TraderSideUser;
import com.example.traderauth.Domain.Entity.Util.Role;
import com.example.traderauth.Service.TraderSideUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TraderSideUserServiceImpl implements TraderSideUserService {
    @Autowired
    private TraderSideUserDao traderSideUserDao;

    @Override
    public TraderSideUser register(TraderSideUser traderSideUser) {
        Map<String, BrokerSideUser> map = new HashMap<>();
        traderSideUser.setBrokerSideUsers(map);
        traderSideUser.setRoles(Role.getDefaultRole());
        return traderSideUserDao.save(traderSideUser);
    }

    @Override
    public TraderSideUser findById(String id) {
        return traderSideUserDao.findById(id).get();
    }

    @Override
    public TraderSideUser findByUsername(String username) {
        return traderSideUserDao.findByUsername(username);
    }

    @Override
    public List<TraderSideUser> findAll() {
        return traderSideUserDao.findAll();
    }

    @Secured(Role.TRADER)
    @Override
    public BrokerSideUser addBrokerSideUser(String username, BrokerSideUser brokerSideUser) {
        TraderSideUser traderSideUser = traderSideUserDao.findByUsername(username);
        BrokerSideUser res = traderSideUser.addBrokerSideUser(brokerSideUser);
        traderSideUserDao.save(traderSideUser);
        return res;
    }

    @Secured(Role.TRADER)
    @Override
    public BrokerSideUser removeBrokerSideUser(String username, Integer bid) {
        TraderSideUser traderSideUser = traderSideUserDao.findByUsername(username);
        BrokerSideUser res = traderSideUser.removeBrokerSideUser(bid);
        traderSideUserDao.save(traderSideUser);
        return res;
    }

    @Secured(Role.TRADER)
    @Override
    public BrokerSideUser modifyBrokerSideUser(String username, BrokerSideUser brokerSideUser) {
        TraderSideUser traderSideUser = traderSideUserDao.findByUsername(username);
        BrokerSideUser res = traderSideUser.modifyBrokerSideUser(brokerSideUser);
        traderSideUserDao.save(traderSideUser);
        return res;
    }
}
