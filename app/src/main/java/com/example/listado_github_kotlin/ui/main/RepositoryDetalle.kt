package com.example.listado_github_kotlin.ui.main

import android.os.Bundle
import android.view.MenuItem
import com.example.data.Repository
import com.example.listado_github_kotlin.databinding.ActivityMainBinding
import com.example.listado_github_kotlin.ui.base.BaseActivity

class RepositoryDetalle : BaseActivity<ActivityMainBinding>() {

    override fun initializedBinding(): ActivityMainBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun initView() {
        val repository = intent?.getParcelableExtra<Repository>("data")



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}