package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class ViewModelShouldDerivedFromBaseViewModelTest {
    private val testConfig = TestConfig()

    private val rule = ViewModelShouldDerivedFromBaseViewModel(testConfig)

    @Test
    fun `passes when viewmodel extends from BaseViewModel`() {
        val code = """
            class NewsViewModel:BaseViewModel()
""".trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when viewmodel does not extends from BaseViewModel`() {
        val code = """
            class NewsViewModel:ViewModel()

            class HighlightViewModel:AndroidViewModel()
""".trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}