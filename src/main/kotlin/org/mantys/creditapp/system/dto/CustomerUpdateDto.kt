package org.mantys.creditapp.system.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.mantys.creditapp.system.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "You must've fill with your first name.")
    val firstName: String,

    @field:NotEmpty(message = "You must've fill with your last name.")
    val lastName: String,

    @field:NotNull(message = "Invalid Input, this field must have a valid value")
    val income: BigDecimal,

    @field:NotEmpty(message = "This field must be filled.")
    val zipCode: String,

    @field:NotEmpty(message = "This field must be filled.")
    val street: String,

    @field:NotEmpty(message = "This field must be filled.")
    val number: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        customer.address.number = this.number
        return customer
    }
}
