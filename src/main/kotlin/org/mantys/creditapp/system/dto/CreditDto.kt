package org.mantys.creditapp.system.dto

import org.mantys.creditapp.system.entity.Credit
import org.mantys.creditapp.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirtInstallment: LocalDate,
    val numberOfInstallment: Int,
    val cutomerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayOfInstallment = this.dayFirtInstallment,
        numberOfInstallments = this.numberOfInstallment,
        customer = Customer(id = this.cutomerId)
    )
}
