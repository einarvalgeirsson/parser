package com.erafn.parser

import com.erafn.parser.util.parsePatientData
import com.google.common.truth.Truth.assertThat
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.junit.Test

class PatientParserTest {

    @Test
    fun patient_data_can_be_parsed_to_patient_object() {
        val patientData =
                """9003|4010000907|10|Name1 Name2 Name3|1984-07-04|F|2014-11-08|10:00|B2062|Iron|25|umol/l|9|34||"""

        val patientObject = parsePatientData(patientData)[0]

        assertThat(patientObject.clinicNumber).isEqualTo(9003)
        assertThat(patientObject.barcode).isEqualTo(4010000907L)
        assertThat(patientObject.patientId).isEqualTo(10)
        assertThat(patientObject.patientName).isEqualTo("Name1 Name2 Name3")
        assertThat(patientObject.dateOfBirth).isEqualTo(DateTime.parse("1984-07-04"))
        assertThat(patientObject.gender).isEqualTo("F")
        assertThat(patientObject.collectionDate).isEqualTo(DateTime.parse("2014-11-08"))
        assertThat(patientObject.collectionTime).isEqualTo(DateTime.parse("10:00", DateTimeFormat.forPattern("HH:mm")))
        assertThat(patientObject.testCode).isEqualTo("B2062")
        assertThat(patientObject.testName).isEqualTo("Iron")
        assertThat(patientObject.result).isEqualTo("25")
        assertThat(patientObject.unit).isEqualTo("umol/l")
        assertThat(patientObject.refrangeLow).isEqualTo("9")
        assertThat(patientObject.refrangeHigh).isEqualTo("34")
        assertThat(patientObject.note).isEmpty()
        assertThat(patientObject.nonSpecRefs).isEmpty()
    }
}