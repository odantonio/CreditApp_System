package org.mantys.creditapp.system.services.implement

import org.mantys.creditapp.system.entity.Customer
import org.mantys.creditapp.system.exceptions.BusinessHandler
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
            throw BusinessHandler("ID $id not founded.")
        }
    }

    // Há um erro de retorno da função, que diz sucesso mesmo que o id não exista
    override fun delete(id: Long) {
        val userDel: Customer = this.findById(id)
        return this.customerRepository.delete(userDel)
    }

}