package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class DBFunctionShouldBeSuspendedTest {
    private val testConfig = TestConfig()

    private val rule = DBFunctionShouldBeSuspended(testConfig)

    @Test
    fun `passes when all database accessing function are either suspend or returns flow`() {
        val code = """package com.flea.market.cart.local.source
                    
                    import androidx.room.Dao
                    import androidx.room.Query
                    import androidx.room.Upsert
                    import com.flea.market.cart.local.entity.CartProductDetailsEntity
                    import kotlinx.coroutines.flow.Flow
                    
                    @Dao
                    interface CartLocalSource {
                        @Query("SELECT * FROM cartProductDetailsEntity")
                        fun getAll(): Flow<List<CartProductDetailsEntity>>
                    
                        @Upsert
                        suspend fun addOrUpdateProduct(productDetails: CartProductDetailsEntity)
                    
                        @Query("SELECT * FROM cartProductDetailsEntity WHERE id= :productId")
                        suspend fun getProductById(productId: Int): CartProductDetailsEntity?
                    
                        @Query("DELETE FROM cartProductDetailsEntity WHERE id = :productId")
                        suspend fun removeProduct(productId: Int)
                    }
""".trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when database accessing function are neither suspend nor returns flow`() {
        val code = """package com.flea.market.cart.local.source
                    
                    import androidx.room.Dao
                    import androidx.room.Query
                    import androidx.room.Upsert
                    import com.flea.market.cart.local.entity.CartProductDetailsEntity
                    import kotlinx.coroutines.flow.Flow
                    
                    @Dao
                    interface CartLocalSource {
                        @Query("SELECT * FROM cartProductDetailsEntity")
                        fun getAll(): Flow<List<CartProductDetailsEntity>>
                    
                        @Upsert
                        suspend fun addOrUpdateProduct(productDetails: CartProductDetailsEntity)
                    
                        @Query("SELECT * FROM cartProductDetailsEntity WHERE id= :productId")
                        suspend fun getProductById(productId: Int): CartProductDetailsEntity?
                    
                        @Query("DELETE FROM cartProductDetailsEntity WHERE id = :productId")
                        fun removeProduct(productId: Int)
                    }
""".trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}