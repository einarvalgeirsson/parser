package com.erafn.parser.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.erafn.parser.R
import com.erafn.parser.data.Patient
import net.danlew.android.joda.JodaTimeAndroid

class MainActivity : AppCompatActivity(), PatientListViewContract {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MainListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        JodaTimeAndroid.init(this)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.setContext(this)

        // Observe the List of patients. When it's updated, update the view
        // with the list data.
        val dataObserver = Observer<List<Patient>> { patients -> updateViewWithPatients(patients)}
        mainViewModel.data.observe(this, dataObserver)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MainListAdapter(emptyList(), this)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getData()
    }

    private fun updateViewWithPatients(patients: List<Patient>?) {
        patients?.let {
            with(viewAdapter) {
                setData(patients)
                notifyDataSetChanged()
            }
        }
    }

    /**
     * Open the detail view for the specific patient
     * The patient object is serialized and passed to the
     * PatientDetailActivity via an intent.
     *
     * @param patient : the patient that is to be displayed
     */
    override fun openDetailViewForPatient(patient: Patient) {
        val patientDetailPageIntent = Intent(applicationContext, PatientDetailActivity::class.java)
        patientDetailPageIntent.putExtra("patient", patient)
        startActivity(patientDetailPageIntent)
    }
}
