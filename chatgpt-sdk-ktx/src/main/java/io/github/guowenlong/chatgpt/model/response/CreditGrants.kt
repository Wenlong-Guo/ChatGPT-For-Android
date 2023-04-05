package io.github.guowenlong.chatgpt.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description:
 * Author:      郭文龙
 * Date:        2023/4/6 3:27
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class CreditGrants(
    @Json(name = "object")
    var `object`: String? = null,
    @Json(name = "total_granted")
    val totalGranted: Double? = null,
    @Json(name = "total_used")
    val totalUsed: Double? = null,
    @Json(name = "total_available")
    val totalAvailable: Double? = null,
    @Json(name = "grants")
    val grants: Grants? = null
) {
    @JsonClass(generateAdapter = true)
    class Grants(
        @Json(name = "object")
        val `object`: String? = null,
        @Json(name = "data")
        val data: List<Datum>? = null
    ) {
        @JsonClass(generateAdapter = true)
        class Datum(
            @Json(name = "object")
            val `object`: String? = null,
            @Json(name = "id")
            val id: String? = null,
            @Json(name = "grant_amount")
            val grantAmount: Double? = null,
            @Json(name = "used_amount")
            val usedAmount: Double? = null,
            @Json(name = "effective_at")
            val effectiveAt: Long? = null,
            @Json(name = "expires_at")
            val expiresAt: Long? = null
        )
    }
}
