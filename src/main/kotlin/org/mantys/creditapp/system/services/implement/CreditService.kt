package org.mantys.creditapp.system.services.implement

import org.mantys.creditapp.system.entity.Credit
import org.mantys.creditapp.system.repository.CreditRepository
import org.mantys.creditapp.system.services.InterCreditService
import java.util.*

class CreditService(
    private val CreditRepository: CreditRepository,
    private val customerService: CustomerService
): InterCreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.CreditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        return this.CreditRepository.findAllByCustomerId(customerId)
    }

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = this.CreditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("Credit Code $creditCode does not exist.")
        return if(credit.customer?.id == customerId) credit else throw RuntimeException("Please, contact the Admin!")
    }
}