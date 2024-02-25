package org.mantys.creditapp.system.services

import org.mantys.creditapp.system.entity.Customer

interface CustomerService {
    fun save(customer: Customer): Customer
    fun findById(customerId: Long): Customer
    fun delete(customerId: Long): Customer
}