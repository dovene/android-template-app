package com.dov.templateapp.model
/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class ResourceWrapper<out T>(val status: Status, val data: T?, val errorCustomizedMessage: String?, val throwable : Throwable?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }


    companion object {
        fun <T> success(data: T?): ResourceWrapper<T> {
            return ResourceWrapper(Status.SUCCESS, data, null,null)
        }

        fun <T> error(errorCustomizedMessage: String,throwable : Throwable?, data: T?): ResourceWrapper<T> {
            return ResourceWrapper(Status.ERROR, data, errorCustomizedMessage, throwable)
        }

        fun <T> loading(data: T?): ResourceWrapper<T> {
            return ResourceWrapper(Status.LOADING, data, null,null)
        }
    }
}