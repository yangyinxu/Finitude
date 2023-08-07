package com.yangyinxu.finitude.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    // take non-null T class data variable
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
