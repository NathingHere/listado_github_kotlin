package com.example.listado_github_kotlin.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<BINDING : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: BINDING

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(initializedBinding().root)
        initView()
    }

    abstract fun initializedBinding() : BINDING

    abstract fun initView()

}