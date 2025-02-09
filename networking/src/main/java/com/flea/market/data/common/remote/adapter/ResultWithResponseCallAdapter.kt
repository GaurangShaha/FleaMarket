package com.flea.market.data.common.remote.adapter

import com.flea.market.data.common.remote.call.ResultWithResponseCall
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.lang.reflect.Type

internal class ResultWithResponseCallAdapter(
    private val resultType: Type,
    private val coroutineScope: CoroutineScope,
) : CallAdapter<Type, Call<Result<Response<Type?>>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type?>): Call<Result<Response<Type?>>> =
        ResultWithResponseCall(call, coroutineScope)
}
