package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Test

class RoomEntityShouldHaveColumnInfoTest {
    private val testConfig = TestConfig()

    private val rule = RoomEntityShouldHaveColumnInfo(testConfig)

    @Test
    fun `passes when classes marked with @Entity annotation has all parameters and properties with annotation @ColumnInfo`() {
        val code = """package com.flea.market.favourite.local.entity

                    import androidx.room.ColumnInfo
                    import androidx.room.Entity
                    import androidx.room.PrimaryKey
                    
                    @Entity
                    data class FavouriteProductDetailsEntity(
                        @ColumnInfo("id") @PrimaryKey val id: Int,
                        @ColumnInfo("category") val category: String,
                        @ColumnInfo("image") val image: String,
                        @ColumnInfo("price") val price: Double,
                        @ColumnInfo("title") val title: String,
                        @ColumnInfo("description") val description: String,
                        @ColumnInfo("rating") val rating: Double,
                    )

""".trimIndent()

        assert(rule.lint(code).isEmpty())
    }

    @Test
    fun `error when  classes marked with @Entity annotation has all parameters and properties with annotation @ColumnInfo`() {
        val code = """package com.flea.market.favourite.local.entity

                    import androidx.room.ColumnInfo
                    import androidx.room.Entity
                    import androidx.room.PrimaryKey
                    
                    @Entity
                    data class FavouriteProductDetailsEntity(
                        @ColumnInfo("id") @PrimaryKey val id: Int,
                        @ColumnInfo("category") val category: String,
                        @ColumnInfo("image") val image: String,
                        @ColumnInfo("price") val price: Double,
                        @ColumnInfo("title") val title: String,
                        @ColumnInfo("description") val description: String,
                        val rating: Double,
                    )

""".trimIndent()

        assert(rule.lint(code).isNotEmpty())
    }
}