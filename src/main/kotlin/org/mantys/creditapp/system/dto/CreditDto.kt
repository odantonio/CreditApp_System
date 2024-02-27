package org.mantys.creditapp.system.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import org.mantys.creditapp.system.entity.Credit
import org.mantys.creditapp.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Invalid Input, this field must have a valid value")
    var creditValue: BigDecimal,

    @field:Future(message = "Past dates aren't a valid date!")
    @field:NotNull()
    var dayFirstInstallment: LocalDate,

    @field:NotNull()
    @field:Min(value = 3, message = "The minimum number of installments is 3 parcels.")
    @field:Max(value = 48, message = "The maximum number of installments is 48 parcels.")
    var numberOfInstallment: Int,

    @field:NotNull(message = "CustomerID absent!")
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
