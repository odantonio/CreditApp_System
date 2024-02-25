package org.mantys.creditapp.system.repository

import org.mantys.creditapp.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer,Long>