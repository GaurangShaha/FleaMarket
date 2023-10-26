package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.psiUtil.isPublic

class ViewModelShouldNotHavePublicProperty(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "ViewModel should not have public properties",
        Debt.TEN_MINS
    )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        val isViewModel = klass.getSuperTypeList()?.entries
            ?.any { it.typeAsUserType?.referencedName == "BaseViewModel" } ?: false

        if (isViewModel) {
            klass.primaryConstructor?.valueParameters?.filter { it.isPublic }?.forEach {
                reportError(klass, it.name.orEmpty())
            }
            klass.body?.properties?.filter { it.isPublic }?.forEach {
                reportError(klass, it.name.orEmpty())
            }
        }
    }

    private fun reportError(klass: KtClass, name: String) {
        report(
            CorrectableCodeSmell(
                issue = issue,
                entity = Entity.from(klass),
                message = """Make variable $name as private.""",
                references = emptyList(),
                autoCorrectEnabled = false
            )
        )
    }
}