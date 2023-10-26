package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class RepositoryShouldNotDependOnRoomTest {
    private val testConfig = TestConfig()

    private val rule = RepositoryShouldNotDependOnRoom(testConfig)

    @Test
    fun `passes when repository does not depends on room`() {
        val code = """
            import com.flea.market.product.remote.entity.ProductDetailsEntity

            interface NewsRepository""".trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when repository depends on room`() {
        val code = """
            import com.flea.market.product.remote.entity.ProductDetailsEntity
            import androidx.room.Query

            class NewsRepository""".trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}