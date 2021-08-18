package com.payment.management.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rhythm
 *
 */
@Repository
public interface PaymentRepository extends CrudRepository<PaymentDAO, Integer> {

}
