package com.dov.templateapp.view.fragments

import android.content.Context
import android.support.v4.app.Fragment
import com.dov.templateapp.di.helper.Injectable
import com.dov.templateapp.view.activities.BaseActivity
import com.dov.templateapp.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.baseActivity = context as BaseActivity
    }

}