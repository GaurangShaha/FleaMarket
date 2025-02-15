package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class ComposeShouldCollectFlowWithLifecycleTest {
    private val testConfig = TestConfig()

    private val rule = ComposeShouldCollectFlowWithLifecycle(testConfig)

    @Test
    fun `passes when used the correct collect on flow`() {
        val code = """
            import androidx.compose.runtime.collectAsStateWithLifecycle

            @Composable
            internal fun ProductDetailsScreen(
                uiState: ProductDetailsUiState,
                processIntent: (ProductDetailsIntent) -> Unit
            ) {
                val addedToFavourite by uiState.markedAsFavouriteFlow.collectAsStateWithLifecycle()
            }
        """.trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when used the incorrect collect on flow`() {
        val code = """import androidx.compose.runtime.collectAsState

            @Composable
            internal fun ProductDetailsScreen(
                uiState: ProductDetailsUiState,
                processIntent: (ProductDetailsIntent) -> Unit
            ) {
                val addedToFavourite by uiState.markedAsFavouriteFlow.collectAsState()
            } 
        """.trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}