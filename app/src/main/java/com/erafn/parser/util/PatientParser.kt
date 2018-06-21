package com.erafn.parser.util

import com.erafn.parser.data.Patient
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

private val DATA_DELIMITER = "|"

/**
 * Parse a string of patient data to get a list of
 * Patient objects
 *
 * @param data : String representation of multiple patients
 * @return a list of Patient objects
 */
fun parsePatientData(data : String) : List<Patient> {
        val p = mutableListOf<Patient>()
        data
                .lines()
                .map { it.split(DATA_DELIMITER) }
                .forEach {
                        p.add(parseSinglePatient(it))
                }
        return p
}

private fun parseSinglePatient(patientData : List<String>) : Patient {
        val clinicNumber = patientData[0]
        val barcode = patientData[1]
        val patientId = patientData[2]
        val patientName = patientData[3]
        val dateOfBirth = patientData[4]
        val gender = patientData[5]
        val collectionDate = patientData[6]
        val collectionTime = patientData[7]
        val testCode = patientData[8]
        val testName = patientData[9]
        val result = patientData[10]
        val unit = patientData[11]
        val refrangeLow = patientData[12]
        val refrangeHigh = patientData[13]
        val note = patientData[14]
        val nonSpecRefs = patientData[15]

        return Patient(
                clinicNumber = clinicNumber.toInt(),
                barcode = barcode.toLong(),
                patientId = patientId.toInt(),
                patientName = patientName,
                dateOfBirth = DateTime.parse(dateOfBirth),
                gender = gender,
                collectionDate = DateTime.parse(collectionDate),
                collectionTime = DateTime.parse(collectionTime, DateTimeFormat.forPattern("HH:mm")),
                testCode = testCode,
                testName = testName,
                result = result,
                unit = unit,
                refrangeLow = refrangeLow,
                refrangeHigh = refrangeHigh,
                note = note,
                nonSpecRefs = nonSpecRefs
        )
}
