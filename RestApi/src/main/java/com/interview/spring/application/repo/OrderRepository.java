/*
 * Creation : 4 Sep 2023
 */
package com.interview.spring.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.spring.application.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
