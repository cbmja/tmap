package com.tmap.tmap.mapper;

import com.tmap.tmap.dto.Store;

import java.util.List;

public interface StoreMapper {

    int save(Store store);

    List<Store> findAll();

    List<Store> findDiscount();

}
