package com.flea.market.data.common.remote

import com.flea.market.data.common.remote.adapter.ResultWithBodyCallAdapter
import com.flea.market.data.common.remote.adapter.ResultWithResponseCallAdapter
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory private constructor(
    private val coroutineScope: CoroutineScope,
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(callType)
        if (rawType != Result::class.java) {
            return null
        }

        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        val resultRawType = getRawType(resultType)

        return if (resultRawType == Response::class.java) {
            val responseType = getParameterUpperBound(0, resultType as ParameterizedType)
            ResultWithResponseCallAdapter(
                resultType = responseType,
                coroutineScope = coroutineScope
            )
        } else {
            ResultWithBodyCallAdapter(
                resultType = resultType,
                resultRawType = resultRawType,
                coroutineScope = coroutineScope
            )
        }
    }

    companion object {
        @JvmStatic
        fun create(
            coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
        ): ResultCallAdapterFactory = ResultCallAdapterFactory(coroutineScope = coroutineScope)
    }
}
