package com.flea.market.product.test

import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
public class MainThreadTestListener : TestListener {
    override suspend fun beforeSpec(spec: Spec) {
        Dispatchers.setMain(UnconfinedTestDispatcher(TestCoroutineScheduler()))
    }

    override suspend fun afterSpec(spec: Spec) {
        Dispatchers.resetMain()
    }
}
