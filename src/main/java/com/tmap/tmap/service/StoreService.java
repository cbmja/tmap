package com.tmap.tmap.service;

import com.tmap.tmap.dto.Store;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final SqlSessionTemplate sql;


    public int save(Store store){

        try{
            int res = sql.insert("com.tmap.tmap.mapper.StoreMapper.save",store);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    public List<Store> findAll(){

        try{
            List<Store> storeList = sql.selectList("com.tmap.tmap.mapper.StoreMapper.findAll");
            return storeList;
        }catch (Exception e){
            e.printStackTrace();
            List<Store> err = new ArrayList<>();
            Store s = new Store();
            s.setErr("err");
            return err;
        }

    }

    public List<Store> findDiscount(){

        try{
            List<Store> storeList = sql.selectList("com.tmap.tmap.mapper.StoreMapper.findDiscount");
            return storeList;
        }catch (Exception e){
            e.printStackTrace();
            List<Store> err = new ArrayList<>();
            Store s = new Store();
            s.setErr("err");
            return err;
        }

    }


}
