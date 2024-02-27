package org.mantys.creditapp.system.exceptions

data class BusinessHandler(
    override val message: String?
): RuntimeException(message) {

}