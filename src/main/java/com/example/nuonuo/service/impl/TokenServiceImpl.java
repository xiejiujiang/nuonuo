package com.example.nuonuo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.service.TokenService;
import com.example.nuonuo.utils.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private orderMapper orderMapper;

    public void refreshToken(){
    }

}
