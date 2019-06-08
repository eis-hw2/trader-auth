package com.example.traderauth.Service;

import com.example.traderauth.Domain.Entity.BrokerSideUser;
import com.example.traderauth.Domain.Entity.TraderSideUser;

import java.util.List;

public interface TraderSideUserService {
    TraderSideUser register(TraderSideUser traderSideUser);

    TraderSideUser findById(String id);

    TraderSideUser findByUsername(String username);

    List<TraderSideUser> findAll();

    BrokerSideUser addBrokerSideUser(String username, BrokerSideUser brokerSideUser);

    BrokerSideUser removeBrokerSideUser(String username, Integer bid);

    BrokerSideUser modifyBrokerSideUser(String username, BrokerSideUser brokerSideUser);
}
