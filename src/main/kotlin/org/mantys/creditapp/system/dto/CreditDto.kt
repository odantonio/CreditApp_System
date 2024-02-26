package org.mantys.creditapp.system.dto

import org.mantys.creditapp.system.entity.Credit
import org.mantys.creditapp.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallment: Int,
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
