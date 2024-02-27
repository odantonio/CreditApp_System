package org.mantys.creditapp.system.controller

import jakarta.validation.Valid
import org.mantys.creditapp.system.dto.CustomerDto
import org.mantys.creditapp.system.dto.CustomerUpdateDto
import org.mantys.creditapp.system.dto.CustomerView
import org.mantys.creditapp.system.entity.Customer
import org.mantys.creditapp.system.services.implement.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) {
        return this.customerService.delete(id)
    }

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val updatedCustomer: Customer = this.customerService.findById(id)
        val customerToUpdated = customerUpdateDto.toEntity(updatedCustomer)
        val customerUpdated = this.customerService.save(customerToUpdated)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}