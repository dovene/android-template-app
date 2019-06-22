package com.dov.templateapp.view.activities
import addFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dov.templateapp.R
import com.dov.templateapp.view.fragments.BaseFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import replaceFragment
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(getLayout())
    }

    open fun getLayout() : Int{
        return R.layout.default_activity_layout
    }


    fun addRootFragment(baseFragment: BaseFragment){
        addFragment(baseFragment,R.id.fragment_holder)
    }

    fun replaceRootFragment(baseFragment: BaseFragment){
        replaceFragment(baseFragment,R.id.fragment_holder)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}