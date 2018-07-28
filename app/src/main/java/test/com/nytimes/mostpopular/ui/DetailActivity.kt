package test.com.nytimes.mostpopular.ui

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import test.com.nytimes.BaseActivity
import test.com.nytimes.MOST_POPULAR_ITEM
import test.com.nytimes.R
import test.com.nytimes.mostpopular.model.MostPopularResult

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val parcel = intent.getParcelableExtra<MostPopularResult>(MOST_POPULAR_ITEM)

        articleTitle.text = parcel.title
        abstractInformation.text = parcel.abstract
        articleWrittenBy.text = parcel.byline
        url.text = "URL: "+parcel.url
        source.text = "Source: "+parcel.source
    }
}
