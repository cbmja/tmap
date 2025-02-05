package com.tmap.tmap.service;

import com.tmap.tmap.dto.ApUser;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final SqlSessionTemplate sql;


    @Transactional
    public String findByIdAndPw(ApUser form){
        ApUser user = sql.selectOne("com.tmap.tmap.mapper.AppUserMapper.findById",form);

        if(user == null){
            return "anonymous";
        }

        ApUser pwCheck = sql.selectOne("com.tmap.tmap.mapper.AppUserMapper.findByIdAndPw",form);

        if(pwCheck == null){
            return "wrongPw";
        }else{
            return pwCheck.getUserCode();
        }

    }




}
