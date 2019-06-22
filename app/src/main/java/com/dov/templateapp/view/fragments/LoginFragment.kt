package com.dov.templateapp.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dov.templateapp.R
import com.dov.templateapp.databinding.LoginFragmentBinding
import com.dov.templateapp.model.Movie
import com.dov.templateapp.view.activities.HomeActivity
import com.dov.templateapp.viewmodel.LoginFragmentViewModel
import displaySnackMessage
import kotlinx.android.synthetic.main.default_activity_layout.*
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : BaseFragment() {

    private lateinit var model: LoginFragmentViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginFragmentViewModel::class.java)
        binding.loginFragmentViewModel = model
        model.getLoginResult().observe(this, Observer {
            if (it?.data != null) {
                startActivity(HomeActivity.newIntent(it.data.results as ArrayList<Movie>, baseActivity))
                activity?.finish()
            } else {
                it?.errorCustomizedMessage?.let { message ->
                    activity?.displaySnackMessage(center_holder, message, true)
                }
            }
        })
    }
}