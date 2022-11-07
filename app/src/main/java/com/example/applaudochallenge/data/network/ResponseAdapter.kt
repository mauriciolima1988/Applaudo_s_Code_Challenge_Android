package com.example.applaudochallenge.data.network

import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResponseAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val bodyErrorConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<NetworkResponse<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return NetworkResponseCall(call, bodyErrorConverter)
    }
}