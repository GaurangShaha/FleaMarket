package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class ComposeShouldNotHaveWindowSizeClassParamTest {
    private val testConfig = TestConfig()

    private val rule = ComposeShouldNotHaveWindowSizeClassParam(testConfig)

    @Test
    fun `passes when parameter does not contain WindowSizeClass`() {
        val code = """@Composable
                internal fun ProductDetailsScreen(
                    uiState: ProductDetailsUiState,
                    processIntent: (ProductDetailsIntent) -> Unit
                ) {}
        """.trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when parameter contains WindowSizeClass`() {
        val code = """@Composable
                internal fun ProductDetailsScreen(
                    uiState: ProductDetailsUiState,
                    windowSizeClass: WindowSizeClass
                ) {}
        """.trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}