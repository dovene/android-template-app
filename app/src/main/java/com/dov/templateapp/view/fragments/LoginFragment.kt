package com.dov.templateapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dov.templateapp.R
import com.dov.templateapp.databinding.LoginFragmentBinding
import com.dov.templateapp.model.Movie
import com.dov.templateapp.view.activities.HomeActivity
import com.dov.templateapp.viewmodel.LoginFragmentViewModel
import displaySnackMessage
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginFragmentViewModel
    private lateinit var binding: LoginFragmentBinding
    private val onEditorActionListener = OnEditorActionListener { _, actionId, _ ->
        var handled = false
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            viewModel.onLoginButtonClicked()
            handled = false
        }
        handled}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginFragmentViewModel::class.java)
        binding.loginFragmentViewModel = viewModel
        viewModel.getLoginResult().observe(this, Observer {
            if (it?.data != null) {
                startActivity(HomeActivity.newIntent(it.data.results as ArrayList<Movie>, baseActivity))
                activity?.finish()
            } else {
                it?.errorCustomizedMessage?.let { message ->
                    activity?.displaySnackMessage(center_holder, message, true)
                }
            }
        })

        viewModel.getProcessState().observe(this, Observer {
             if(it){
                 loginBT.visibility = View.INVISIBLE
                 progressBar.visibility = View.VISIBLE
             } else {
                 loginBT.visibility = View.VISIBLE
                 progressBar.visibility = View.INVISIBLE
             }
        })
        passTV.setOnEditorActionListener(onEditorActionListener)
        emailTV.setOnEditorActionListener(onEditorActionListener)
    }
}