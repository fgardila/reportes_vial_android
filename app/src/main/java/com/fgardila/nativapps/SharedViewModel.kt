package com.fgardila.nativapps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fgardila.nativapps.data.ReportRepository
import com.fgardila.nativapps.data.model.Report
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject internal constructor(var repository: ReportRepository) : ViewModel() {

    private val reports: MutableLiveData<List<Report>> by lazy {
        MutableLiveData<List<Report>>().also {
            loadReports()
        }
    }

    private fun loadReports() {
        viewModelScope.launch {
            repository.getAllTask.collect {
                reports.value = it
            }
        }
    }

    fun getReports(): LiveData<List<Report>> {
        return reports
    }

    private val _description = MutableLiveData<String>()
    private val description = _description

    fun setDescription(description: String) {
        _description.value = description
    }

    private val _imageBase64 = MutableLiveData<String>()

    fun setImageBase64(image: String) {
        _imageBase64.value = image
        val description = description.value!!
        val report = Report(description = description, urlImage = image)
        addReport(report)
    }

    private fun addReport(report: Report) {
        viewModelScope.launch {
            repository.addTask(report = report)
            _status.value = true
        }
    }

    private val _status = MutableLiveData<Boolean>()
    val status = _status

    fun setStatus(status: Boolean) {
        _status.value = status
    }

}