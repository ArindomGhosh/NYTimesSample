package test.com.nytimes.mostpopular.model

data class MostPopularResponse(
        val status: String?,
        val copyright: String?,
        val num_results: Long?,
        val results: List<MostPopularResult>
)

data class MostPopularResult(
        val url: String,
        val column: String?,
        val section: String,
        val byline: String,
        val type: String,
        val title: String,
        val abstract: String,
        val published_date: String,
        val source: String
 )