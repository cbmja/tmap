package com.tmap.tmap.mapper;

import com.tmap.tmap.dto.ApUser;


public interface AppUserMapper {

    ApUser findByIdAndPw(ApUser form);

    ApUser findById(ApUser form);

}
