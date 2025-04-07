package com.teamravanan.repo;

import com.teamravanan.entity.MemberEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface MemberRepo extends MongoRepository<MemberEntity, Long> {
    MemberEntity findById(long id);
}
