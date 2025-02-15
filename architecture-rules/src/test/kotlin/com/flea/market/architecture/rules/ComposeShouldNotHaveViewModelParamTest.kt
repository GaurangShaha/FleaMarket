package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class ComposeShouldNotHaveViewModelParamTest {
    private val testConfig = TestConfig()

    private val rule = ComposeShouldNotHaveViewModelParam(testConfig)

    @Test
    fun `passes when parameter does not contain viewmodel`() {
        val code = """@Composable
                internal fun ProductDetailsScreen(
                    uiState: ProductDetailsUiState,
                    processIntent: (ProductDetailsIntent) -> Unit
                ) {}
        """.trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when parameter contains viewmodel`() {
        val code = """@Composable
                internal fun ProductDetailsScreen(
                    uiState: ProductDetailsUiState,
                    viewModel: ProductDetailsViewModel
                ) {}
        """.trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}