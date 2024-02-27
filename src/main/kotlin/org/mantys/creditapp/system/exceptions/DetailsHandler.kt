package org.mantys.creditapp.system.exceptions

import java.time.LocalDateTime

data class DetailsHandler(
    val title: String,
    val timestamp: LocalDateTime,
    val status: Int,
    val excepetion: String,
    val details: MutableMap<String, String?>
) {
}