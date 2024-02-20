package com.jackbracey.resttestexercise.Services;

import com.jackbracey.resttestexercise.Entities.CustomerEntity;
import com.jackbracey.resttestexercise.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerEntity> saveAll(List<CustomerEntity> entities) {
        return customerRepository.saveAll(entities);
    }

    public CustomerEntity save(CustomerEntity entity) {
        return customerRepository.save(entity);
    }

    public List<CustomerEntity> findCustomersById(List<Integer> ids) {
        return customerRepository.findByIdIn(ids);
    }

}
