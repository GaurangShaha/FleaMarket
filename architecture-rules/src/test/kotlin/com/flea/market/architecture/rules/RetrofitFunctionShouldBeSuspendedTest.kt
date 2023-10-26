package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class RetrofitFunctionShouldBeSuspendedTest {
    private val testConfig = TestConfig()

    private val rule = RetrofitFunctionShouldBeSuspended(testConfig)

    @Test
    fun `passes when all database accessing function are either suspend or returns flow`() {
        val code = """package com.flea.market.product.remote.source
                    
                    import com.flea.market.product.remote.entity.ProductDetailsEntity
                    import retrofit2.Response
                    import retrofit2.http.GET
                    import retrofit2.http.Path
                    
                    internal interface ProductRemoteSource {
                        @GET("/products")
                        suspend fun getProductList(): Response<List<ProductDetailsEntity>>
                    
                        @GET("/products/{id}")
                        suspend fun getProductDetails(@Path("id") id: Int): Response<ProductDetailsEntity>
                    }
""".trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when database accessing function are neither suspend nor returns flow`() {
        val code = """package com.flea.market.product.remote.source
                    
                    import com.flea.market.product.remote.entity.ProductDetailsEntity
                    import retrofit2.Response
                    import retrofit2.http.GET
                    import retrofit2.http.Path
                    
                    internal interface ProductRemoteSource {
                        @GET("/products")
                        fun getProductList(): Response<List<ProductDetailsEntity>>
                    
                        @GET("/products/{id}")
                        fun getProductDetails(@Path("id") id: Int): Response<ProductDetailsEntity>
                    }
""".trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}