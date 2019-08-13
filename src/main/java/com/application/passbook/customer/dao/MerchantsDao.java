package com.application.passbook.customer.dao;

import com.application.passbook.customer.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {

    Merchants findByName(String name);

    List<Merchants> findByIdIn(List<Integer> ids);
}
