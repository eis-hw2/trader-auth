package com.example.traderauth.Dao.Repo;

import com.example.traderauth.Domain.Entity.TraderSideUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TraderSideUserDao extends MongoRepository<TraderSideUser, String> {
    TraderSideUser findByUsername(String username);

}
