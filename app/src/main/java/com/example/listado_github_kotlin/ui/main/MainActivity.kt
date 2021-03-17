package com.example.listado_github_kotlin.ui.main

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.MainState
import com.example.data.Repository
import com.example.data.ScreenState
import com.example.listado_github_kotlin.R
import com.example.listado_github_kotlin.databinding.ActivityMainBinding
import com.example.listado_github_kotlin.ui.base.BaseActivity
import com.example.listado_github_kotlin.ui.base.ServiceBuilder
import com.example.listado_github_kotlin.ui.main.adapters.RepositoryAdapter
import com.example.listado_github_kotlin.ui.main.webservice.MyClient
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel by viewModel<MainViewModel>()
    lateinit var repositoryAdapter:RepositoryAdapter

    override fun initializedBinding(): ActivityMainBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        mainViewModel.init()
        setTitle(R.string.toolbar_main)

        repositoryAdapter = RepositoryAdapter(layoutInflater = layoutInflater) {
            goToDetail(it)
        }

        binding.recyclerview.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = repositoryAdapter
        }

        mainViewModel.repositoryLiveData.observe(this) {
            manageScreenState(it)
        }

    }

    private fun manageScreenState(screenState: ScreenState<MainState>) {
        when(screenState) {
            ScreenState.Loading-> {

            }
            is ScreenState.Render-> manageResult(screenState.renderState)
        }
    }

    private fun manageResult(mainState: MainState) {
        //Esconder el Loading
        when(mainState) {
            MainState.Empty-> {
            //Mensaje lista vacia
            }
            is MainState.DrawItems-> repositoryAdapter.setLista(mainState.items)
            is MainState.ShowErrorMessage -> Toast.makeText(this, mainState.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToDetail(repository:Repository) {
        val intent = Intent(this, RepositoryDetalle::class.java).apply {
            putExtra("data", repository)
        }
        startActivity(intent)
    }
}