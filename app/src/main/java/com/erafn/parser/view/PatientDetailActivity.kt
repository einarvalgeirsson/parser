package com.erafn.parser.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.erafn.parser.R
import com.erafn.parser.data.Patient
import kotlinx.android.synthetic.main.patient_detail_layout.*

class PatientDetailActivity : AppCompatActivity() {

    private lateinit var patient : Patient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_detail_layout)

        patient = intent.getSerializableExtra("patient") as Patient

        with(patient) {
            clinic_nbr_text.text = "Clinic Number: $clinicNumber"
            barcode_text.text = "BarCode: $barcode"
            patient_id_text.text = "Patient Id: $patientId"
        }
    }
}
