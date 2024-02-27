package org.mantys.creditapp.system.services.implement

import org.mantys.creditapp.system.entity.Credit
import org.mantys.creditapp.system.exceptions.BusinessHandler
import org.mantys.creditapp.system.repository.CreditRepository
import org.mantys.creditapp.system.services.InterCreditService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CreditService(
    @Autowired
    private val creditRepository: CreditRepository,
    @Autowired
    private val customerService: CustomerService
) : InterCreditService {
    override fun save(credit: Credit): Credit {
        this.dateValidation(credit.dayFirstInstallment)
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    private fun dateValidation(dayFirstInstallment: LocalDate): Boolean {
        return if (dayFirstInstallment.isBefore(LocalDate.now().plusMonths(3))) {
            true
        } else {
            throw BusinessHandler("The Date required is not valid. The max amount are 3 months.")
        }
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        return this.creditRepository.findAllByCustomerId(customerId)
    }

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw BusinessHandler("Credit Code $creditCode does not exist.")
        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Please, contact the Admin!")
    }
}