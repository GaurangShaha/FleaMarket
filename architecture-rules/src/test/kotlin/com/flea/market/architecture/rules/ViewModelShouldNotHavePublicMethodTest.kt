package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class ViewModelShouldNotHavePublicMethodTest {
    private val testConfig = TestConfig()

    private val rule = ViewModelShouldNotHavePublicMethod(testConfig)

    @Test
    fun `passes when viewmodel does not have parameter of type SavedStateHandle`() {
        val code = """
            class NewsViewModel:BaseViewModel<ProductDetailsIntent, ProductDetailsUiState>(Loading){
                fun processIntent(){}
            }
""".trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when viewmodel have parameter of type SavedStateHandle`() {
        val code = """
            class NewsViewModel(private val savedStateHandle: SavedStateHandle):BaseViewModel<ProductDetailsIntent, ProductDetailsUiState>(Loading){
                fun error(){
                }
            }
""".trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}