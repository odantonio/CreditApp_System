package org.mantys.creditapp.system.services

import org.mantys.creditapp.system.entity.Credit
import java.util.*

interface CreditService {
    fun save(credit: Credit): Credit
    fun findByCustomer(customerId: Long): List<Credit>
    fun findByCreditCode(creditCode: UUID): Credit
}