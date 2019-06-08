package com.example.traderauth.Controller;

import com.example.traderauth.Domain.Entity.BrokerSideUser;
import com.example.traderauth.Domain.Entity.TraderSideUser;
import com.example.traderauth.Domain.Factory.ResponseWrapperFactory;
import com.example.traderauth.Domain.Wrapper.ResponseWrapper;
import com.example.traderauth.Service.TraderSideUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/TraderSideUser")
public class TraderSideUserController {
    @Autowired
    private TraderSideUserService traderSideUserService;

    @PostMapping("")
    public ResponseWrapper register(@RequestBody TraderSideUser traderSideUser){
        try {
            TraderSideUser user = traderSideUserService.register(traderSideUser);
            return ResponseWrapperFactory.create(ResponseWrapper.SUCCESS, user);
        }
        catch (Exception e){
            return ResponseWrapperFactory.create(ResponseWrapper.ERROR, e.getMessage());
        }
    }

    @PostMapping("/BrokerSideUser")
    public ResponseWrapper addBrokerSideUser(@RequestBody BrokerSideUser brokerSideUser){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BrokerSideUser res = traderSideUserService.addBrokerSideUser(username, brokerSideUser);
        return ResponseWrapperFactory.create(ResponseWrapper.SUCCESS, res);
    }

    @DeleteMapping("/BrokerSideUser/{brokerId}")
    public ResponseWrapper removeBrokerSideUser(@PathVariable Integer brokerId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BrokerSideUser res = traderSideUserService.removeBrokerSideUser(username, brokerId);
        return ResponseWrapperFactory.create(ResponseWrapper.SUCCESS, res);
    }

    @PutMapping("/BrokerSideUser/{brokerId}")
    public ResponseWrapper modifyBrokerSideUser(@RequestBody BrokerSideUser brokerSideUser){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BrokerSideUser res = traderSideUserService.modifyBrokerSideUser(username, brokerSideUser);
        return ResponseWrapperFactory.create(ResponseWrapper.SUCCESS, res);
    }



    @GetMapping("/{userId}")
    public ResponseWrapper findById(@PathVariable String userId){
        TraderSideUser user = traderSideUserService.findById(userId);
        return ResponseWrapperFactory.create(ResponseWrapper.SUCCESS, user);
    }

    @GetMapping("")
    public ResponseWrapper findAll(){
        List<TraderSideUser> users = traderSideUserService.findAll();
        return ResponseWrapperFactory.create(ResponseWrapper.SUCCESS, users);
    }

    @GetMapping("/myself")
    public ResponseWrapper myself(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        TraderSideUser user = traderSideUserService.findByUsername(username);
        return ResponseWrapperFactory.create(ResponseWrapper.SUCCESS, user);
    }
}
