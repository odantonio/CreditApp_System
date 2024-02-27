package org.mantys.creditapp.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CPF
import org.mantys.creditapp.system.entity.Address
import org.mantys.creditapp.system.entity.Customer
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "You must've fill with your first name.")
    val firstName: String,

    @field:NotEmpty(message = "You must've fill with your last name.")
    val lastName: String,

    @field:CPF(message = "You entered an invalid CPF, please confirm your CPF.")
    val cpf: String,

    @field:NotNull(message = "Invalid Input, this field must have a valid value")
    val income: BigDecimal,

    @field:Email(message = "You entered an Invalid Email.")
    @field:NotEmpty(message = "Enter a Valid Email")
    val email: String,

    @field:NotEmpty(message = "You must create an Password")
    @field:Size(
        min = 6,
        max = 24,
        message = "Your password must be between 6 and 24 characters."
    )
    val password: String,

    @field:NotEmpty(message = "This field must be filled.")
    val zipCode: String,

    @field:NotEmpty(message = "This field must be filled.")
    val street: String,

    @field:NotEmpty(message = "This field must be filled.")
    val number: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street,
            number = this.number
        )
    )
}
