package com.complaints.repository;


import com.complaints.repository.entity.ComplaintEntity;
import com.complaints.repository.entity.key.ComplaintId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintsRepository extends CrudRepository<ComplaintEntity, ComplaintId> {
    @Override
    List<ComplaintEntity> findAll();
}
