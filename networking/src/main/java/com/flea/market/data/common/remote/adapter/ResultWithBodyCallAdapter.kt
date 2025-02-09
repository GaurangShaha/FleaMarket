package com.flea.market.data.common.remote.adapter

import com.flea.market.data.common.remote.call.ResultWithBodyCall
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResultWithBodyCallAdapter(
    private val resultType: Type,
    private val resultRawType: Type,
    private val coroutineScope: CoroutineScope,
) : CallAdapter<Type, Call<Result<Type?>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>) = ResultWithBodyCall(call, resultRawType, coroutineScope)
}
