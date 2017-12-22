package com.feliperoriz.watchdog.data

import com.squareup.moshi.Json
import org.joda.time.DateTime
import java.util.*

/**
 * Created by feliperoriz on 12/21/17.
 */
data class MemberDTO (
        val id: String,
        val name: String,
        val gender: String,
        val first_name: String,
        val middle_name: String?,
        val last_name: String,
        val party: String,
        val next_election: String,
        val seniority: String,
        val twitter_id: String?
)