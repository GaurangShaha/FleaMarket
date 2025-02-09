package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class PublicFunInRepositoryShouldReturnResultOrFlowTest {
    private val testConfig = TestConfig()

    private val rule = PublicFunInRepositoryShouldReturnResultOrFlow(testConfig)

    @Test
    fun `passes when all public function returns Result or Flow`() {
        val code = """interface NewsRepository{
            fun a(): StateFlow<Unit>
            
            fun d(): SharedFlow<Unit>
            
            fun b(): Result<Unit>
            
            fun c(val a: Int): Flow<String>
        }""".trimIndent()

        assert(rule.lint(code).isEmpty())
    }
    @Test
    fun `error when all public function returns Result or Flow`() {
        val code = """interface NewsRepository{
            fun a():Unit
            
            fun b(): Result<Unit>
            
            fun c(val a: Int): Flow<String>
        }""".trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}
