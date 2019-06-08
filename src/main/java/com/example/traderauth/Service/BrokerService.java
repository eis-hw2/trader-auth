package com.example.traderauth.Service;


import com.example.traderauth.Domain.Entity.Broker;

import java.util.List;

public interface BrokerService {

    List<Broker> findAll();

    Broker findById(Integer id);
}
