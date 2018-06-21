package com.erafn.parser.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.erafn.parser.R
import com.erafn.parser.data.Patient
import kotlinx.android.synthetic.main.patient_list_item.view.*

class MainListAdapter(private var dataset: List<Patient>, val view : PatientListViewContract) :
        RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    class ViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view) {
        var textView : TextView = view.patientNameText
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.patient_list_item, parent, false) as ViewGroup
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient = dataset[position]
        holder.textView.text = patient.patientName
        holder.view.setOnClickListener({
            view.openDetailViewForPatient(patient)
        })
    }

    override fun getItemCount() = dataset.size

    fun setData(dataset: List<Patient>) {
        this.dataset = dataset
    }
}