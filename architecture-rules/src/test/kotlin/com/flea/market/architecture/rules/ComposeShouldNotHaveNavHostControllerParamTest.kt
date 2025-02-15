package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class ComposeShouldNotHaveNavHostControllerParamTest {
    private val testConfig = TestConfig()

    private val rule = ComposeShouldNotHaveNavHostControllerParam(testConfig)

    @Test
    fun `passes when parameter does not contain NavHostController`() {
        val code = """@Composable
                internal fun ProductDetailsScreen(
                    uiState: ProductDetailsUiState,
                    processIntent: (ProductDetailsIntent) -> Unit
                ) {}
        """.trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when parameter contains NavHostController`() {
        val code = """@Composable
                internal fun ProductDetailsScreen(
                    uiState: ProductDetailsUiState,
                    processIntent: (ProductDetailsIntent) -> Unit,
                    navHost: NavHostController
                ) {}
        """.trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}