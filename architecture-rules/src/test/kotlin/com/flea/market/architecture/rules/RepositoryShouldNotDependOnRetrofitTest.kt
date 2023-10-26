package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class RepositoryShouldNotDependOnRetrofitTest {
    private val testConfig = TestConfig()

    private val rule = RepositoryShouldNotDependOnRetrofit(testConfig)

    @Test
    fun `passes when repository does not depends on retrofit`() {
        val code = """
            import com.flea.market.product.remote.entity.ProductDetailsEntity

            class NewsRepository""".trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when repository depends on retrofit`() {
        val code = """
            import com.flea.market.product.remote.entity.ProductDetailsEntity
            import retrofit2.Response
            import retrofit2.http.GET
            import retrofit2.http.Path

            class NewsRepository""".trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}