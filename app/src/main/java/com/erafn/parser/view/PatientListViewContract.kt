package com.erafn.parser.view

import com.erafn.parser.data.Patient

interface PatientListViewContract {
    fun openDetailViewForPatient(patient : Patient)
}