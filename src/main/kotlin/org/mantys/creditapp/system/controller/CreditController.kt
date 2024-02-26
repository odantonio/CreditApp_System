package org.mantys.creditapp.system.controller

import org.mantys.creditapp.system.dto.CreditDto
import org.mantys.creditapp.system.dto.CreditView
import org.mantys.creditapp.system.dto.CreditViewList
import org.mantys.creditapp.system.entity.Credit
import org.mantys.creditapp.system.services.implement.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): ResponseEntity<String> {
        val savedCredit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${savedCredit.creditCode} - Customer ${savedCredit.customer?.firstName} saved successfully!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customer_id") customerId: Long): ResponseEntity<List<CreditViewList>> {
        val credit: List<CreditViewList> = this.creditService.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewList(credit) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(credit)
    }

    @GetMapping("/{credit_code}")
    fun findByCreditCode(
        @RequestParam(value = "customer_id") customerId: Long,
        @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}