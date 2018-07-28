package test.com.nytimes.common

class Resource<T> private constructor(val status: Resource.Status,
                                      val data: T?,
                                      val throwable: Throwable?){
    enum class Status{
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun<T> success(data: T?): Resource<T>{
            return Resource(Status.SUCCESS,data,null)
        }
        fun<T> error(throwable: Throwable?): Resource<T>{
            return Resource(Status.ERROR,null,throwable)
        }
        fun<T> loading(): Resource<T>{
            return Resource(Status.LOADING,null,null)
        }
    }
}