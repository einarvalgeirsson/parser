package com.erafn.parser.view

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.erafn.parser.data.Patient
import com.erafn.parser.util.parsePatientData
import com.erafn.parser.util.readFile
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainViewModel : ViewModel() {

    val data : MutableLiveData<List<Patient>> = MutableLiveData()

    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context

    /**
     * Fetch the patient data from disk and update
     * the LiveData object once the operation is
     * done.
     */
    fun getData() {
        var patientData : List<Patient> = mutableListOf()
        launch(UI) {
            async {
                patientData = getPatientData()
            }.await()
            data.value = patientData
        }
    }

    private fun getPatientData(): List<Patient> {
        return parsePatientData(readDataFromFile())
    }

    private fun readDataFromFile(): String {
        context.let {
            return readFile(it, "data.txt")
        }
    }


    /**
     *  This is a bit of a hack to get the Context into the ViewModel
     *  in order to fetch the file from assets.
     *
     *  In real life the data would come from the backend or a database
     **/
    fun setContext(context : Context) {
        this.context = context
    }
}