package org.mantys.creditapp.system.services

import org.mantys.creditapp.system.entity.Customer

interface InterCustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}