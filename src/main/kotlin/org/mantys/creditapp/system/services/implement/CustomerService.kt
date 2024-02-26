package org.mantys.creditapp.system.services.implement

import org.mantys.creditapp.system.entity.Customer
import org.mantys.creditapp.system.repository.CustomerRepository
import org.mantys.creditapp.system.services.InterCustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class CustomerService(
    @Autowired
    private val customerRepository: CustomerRepository
) : InterCustomerService {
    override fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer)

    }

    override fun findById(id: Long): Customer {
        return this.customerRepository.findById(id).orElseThrow {
            throw RuntimeException("ID $id not founded.")
        }
    }

    override fun delete(id: Long) {
        return this.customerRepository.deleteById(id)
    }

}