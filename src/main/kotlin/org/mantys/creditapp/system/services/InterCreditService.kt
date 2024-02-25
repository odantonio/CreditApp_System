package org.mantys.creditapp.system.services

import org.mantys.creditapp.system.entity.Credit
import java.util.*

interface InterCreditService {
    fun save(credit: Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}