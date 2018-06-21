package com.erafn.parser.data

import org.joda.time.DateTime
import org.joda.time.Instant
import java.io.Serializable
import java.util.*

/**
 * Object representation of a Patient
 */
data class Patient(
        val clinicNumber : Int,
        val barcode: Long,
        val patientId : Int,
        val patientName : String,
        val dateOfBirth : DateTime,
        val gender : String,
        val collectionDate : DateTime,
        val collectionTime : DateTime,
        val testCode : String,
        val testName : String,
        val result : String,
        val unit : String,
        val refrangeLow : String,
        val refrangeHigh : String,
        val note : String,
        val nonSpecRefs : String
) : Serializable