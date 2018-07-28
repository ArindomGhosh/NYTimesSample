package test.com.nytimes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.kodein.di.generic.instance
import test.com.nytimes.common.Resource
import test.com.nytimes.databinding.ActivityMainBinding
import test.com.nytimes.mostpopular.model.MostPopularParameters
import test.com.nytimes.mostpopular.model.MostPopularResult
import test.com.nytimes.mostpopular.ui.DetailActivity
import test.com.nytimes.mostpopular.ui.MostPopularRecyclerViewAdapter
import test.com.nytimes.mostpopular.ui.MostPopularViewModel


const val MOST_POPULAR_ITEM = "MOST_POPULAR_ITEM"

class MainActivity : BaseActivity(), MostPopularRecyclerViewAdapter.OnItemClickListener {
    private val viewModelFactory: ViewModelFactory by instance()
    private lateinit var viewModel: MostPopularViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this,viewModelFactory)
                .get(MostPopularViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.appBarMain.included.viewModel = viewModel
        binding.setLifecycleOwner(this)

        setSupportActionBar(toolbar)

        viewModel.parameters.value = MostPopularParameters(
                                     "all-sections",
                                      7,
                                       getString(R.string.nytimes_api_key))

        val adapter = MostPopularRecyclerViewAdapter()
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

        viewModel.mostPopular.observe(this, Observer {
            when(it?.status){
                Resource.Status.LOADING -> viewModel.hideProgress.value = false
                Resource.Status.ERROR -> {
                    viewModel.hideProgress.value = true
                    Snackbar.make(drawer_layout, it?.throwable?.message.toString(), Snackbar.LENGTH_LONG).show()
                }
                Resource.Status.SUCCESS -> {
                    viewModel.hideProgress.value = true
                    it.data?.let {
                        adapter.setPopularList(it.results)
                    }
                }
            }
        })

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun setOnItemClickListener(mostPopularResult: MostPopularResult) {
        val intent = Intent(this, DetailActivity::class.java)

        val args = Bundle()
        args.putParcelable(MOST_POPULAR_ITEM,mostPopularResult)
        intent.putExtras(args)

        startActivity(intent)
    }
}
