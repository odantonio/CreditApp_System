package org.mantys.creditapp.system.repository

import org.mantys.creditapp.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

/* A interface estende da JpaRepository
 * <Aqui a tabela, e aqui o tipo da chave primaria>
 *
 * Esta interface é o ponto de acesso ao nosso banco de dados
 * Nela instanciaremos as operações CRUD
 * */
@Repository
interface CreditRepository: JpaRepository<Credit,Long>{
    fun findByCreditCode(creditCode: UUID): Credit?
    @Query(value = "SELECT * FROM credits WHERE customer_id=?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<Credit>
}