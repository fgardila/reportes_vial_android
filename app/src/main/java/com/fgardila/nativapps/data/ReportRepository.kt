package com.fgardila.nativapps.data

import com.fgardila.nativapps.data.model.Report
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ReportRepository @Inject constructor(private val reportDao: ReportDao) {

    val getAllTask: Flow<List<Report>> = reportDao.getAllReports()

    suspend fun addTask(report: Report) {
        reportDao.addReport(report = report)
    }
}