package com.example.listado_github_kotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.MainState
import com.example.data.Repository
import com.example.data.ScreenState
import com.example.data.fold
import com.example.listado_github_kotlin.ui.NetworkController
import com.example.listado_github_kotlin.ui.main.webservice.MyClient
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val myClient: MyClient, private val networkController: NetworkController) : ViewModel() {

    private val _repositoryMutableData = MutableLiveData<ScreenState<MainState>>()
    val repositoryLiveData:LiveData<ScreenState<MainState>>
    get() = _repositoryMutableData

    private lateinit var job: Job
    private lateinit var uiScope: CoroutineScope
    private lateinit var ioContext: CoroutineContext

    fun init() {
        onCreate()
        getList()
    }

    private fun onCreate() {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        ioContext = Dispatchers.IO + job
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
        ioContext.cancel()
    }

    private fun getList() {
        updateUI(ScreenState.Loading)
        uiScope.launch {
            val result = uiScope.async { withContext(ioContext) {
                 try {
                    val response = myClient.getRepositoryList()
                    networkController.checkResponse(response)
                } catch (e: Exception) {
                    networkController.checkException(e)
                }
            } }

            result.await().fold({
                updateUI(ScreenState.Render(MainState.ShowErrorMessage(it)))
            }, {
                if (it.isEmpty()) {
                    updateUI(ScreenState.Render(MainState.Empty))
                } else {
                    updateUI(ScreenState.Render(MainState.DrawItems(it)))
                }
            })

        }
    }

    private fun updateUI(state: ScreenState<MainState>) {
        _repositoryMutableData.postValue(state)
    }

}