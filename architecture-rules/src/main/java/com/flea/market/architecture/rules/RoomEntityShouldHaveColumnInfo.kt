package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.psiUtil.getValueParameters

class RoomEntityShouldHaveColumnInfo(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "Classes marked with @Entity annotation should have all parameters and properties with annotation @ColumnInfo",
        Debt.FIVE_MINS
    )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if (klass.annotationEntries.any { it.shortName.toString() == "Entity" }) {
            klass.getProperties()
                .filter { ktProperty -> ktProperty.annotationEntries.none { it.shortName.toString() == "ColumnInfo" } }
                .forEach {
                    report(
                        CorrectableCodeSmell(
                            issue = issue,
                            entity = Entity.from(it),
                            message = """Classes marked with @Entity annotation should have all properties with annotation @ColumnInfo""",
                            references = emptyList(),
                            autoCorrectEnabled = false
                        )
                    )
                }

            klass.getValueParameters()
                .filter { ktParameter -> ktParameter.annotationEntries.none { it.shortName.toString() == "ColumnInfo" } }
                .forEach {
                    report(
                        CorrectableCodeSmell(
                            issue = issue,
                            entity = Entity.from(it),
                            message = """Classes marked with @Entity annotation should have all parameters with annotation @ColumnInfo""",
                            references = emptyList(),
                            autoCorrectEnabled = false
                        )
                    )
                }
        }
    }
}