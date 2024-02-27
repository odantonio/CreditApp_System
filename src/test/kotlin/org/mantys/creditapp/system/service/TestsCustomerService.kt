package org.mantys.creditapp.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mantys.creditapp.system.entity.Address
import org.mantys.creditapp.system.entity.Customer
import org.mantys.creditapp.system.exceptions.BusinessHandler
import org.mantys.creditapp.system.repository.CustomerRepository
import org.mantys.creditapp.system.services.implement.CustomerService
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class TestsCustomerService {
    @MockK
    lateinit var customerRepository: CustomerRepository

    @InjectMockKs
    lateinit var customerService: CustomerService

    @Test
    fun `should create customer`() {
        //given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer
        //when
        val savedCustomer = customerService.save(fakeCustomer)
        //then
        Assertions.assertThat(savedCustomer).isNotNull
        Assertions.assertThat(savedCustomer).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }

    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakeCustomerId: Long = Random(1L).nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeCustomerId)
        every { customerRepository.findById(fakeCustomerId) } returns Optional.of(fakeCustomer)
        //when
        val savedCustomer: Customer = customerService.findById(fakeCustomerId)
        //then
        Assertions.assertThat(savedCustomer).isNotNull
        Assertions.assertThat(savedCustomer).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(savedCustomer).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeCustomerId) }
    }

    @Test
    fun `should throw an exception id not found`() {
        //given
        val fakeCustomerId: Long = Random(1L).nextLong()
        every { customerRepository.findById(fakeCustomerId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessHandler::class.java)
            .isThrownBy { customerService.findById(fakeCustomerId) }
            .withMessage("ID $fakeCustomerId not founded.")
        verify(exactly = 1) { customerRepository.findById(fakeCustomerId) }
    }

    @Test
    fun `should delete through id`() {
        //given
        val fakeCustomerId: Long = Random(1L).nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeCustomerId)
        every { customerRepository.findById(fakeCustomerId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakeCustomerId)
        //then
        verify(exactly = 1) { customerRepository.findById(fakeCustomerId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }

    companion object {
        fun buildCustomer(
            firstName: String = "Olavo",
            lastName: String = "D'Antonio",
            cpf: String = "42566335885",
            email: String = "olavo@example.org",
            password: String = "1a2b3c4d5e",
            zipCode: String = "12345-678",
            street: String = "rua da casa do olavo",
            number: String = "12-123",
            income: BigDecimal = BigDecimal.valueOf(2341.50),
            id: Long = 1L
        ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street,
                number = number
            ),
            income = income,
            id = id
        )
    }
}