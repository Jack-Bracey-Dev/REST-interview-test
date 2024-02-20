package com.jackbracey.resttestexercise.Repositories;

import com.jackbracey.resttestexercise.Entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    List<CustomerEntity> findByIdIn(Collection<Integer> ids);
}
