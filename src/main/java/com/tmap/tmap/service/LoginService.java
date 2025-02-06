package com.tmap.tmap.service;

import com.tmap.tmap.dto.ApUser;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final SqlSessionTemplate sql;


    @Transactional
    public Map<String , String> findByIdAndPw(ApUser form){

        Map<String , String> res = new HashMap<>();
        ApUser user = sql.selectOne("com.tmap.tmap.mapper.AppUserMapper.findById",form);

        if(user == null){
            res.put("res" , "anonymous");
            return res;
        }

        ApUser pwCheck = sql.selectOne("com.tmap.tmap.mapper.AppUserMapper.findByIdAndPw",form);

        if(pwCheck == null){
            res.put("res" , "wrongPw");
            return res;
        }else{
            res.put("res" , pwCheck.getUserCode());
            res.put("departure" , pwCheck.getSubAddr1());
            res.put("destination" , pwCheck.getHomeAddr1());

            res.put("destinationx" , pwCheck.getHome1LocX());
            res.put("destinationy" , pwCheck.getHome1LocY());

            res.put("departurex" , pwCheck.getSub1LocX());
            res.put("departurey" , pwCheck.getSub1LocY());

            return res;
        }

    }




}
