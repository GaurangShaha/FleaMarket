package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.psiUtil.isPublic

class ViewModelShouldNotHavePublicProperty(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "ViewModel should not have public properties",
        Debt.FIVE_MINS
    )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        val isViewModel = klass.getSuperTypeList()?.entries
            ?.any { it.typeAsUserType?.referencedName == "BaseViewModel" } ?: false

        if (isViewModel) {
            klass.primaryConstructor?.valueParameters?.filter { it.isPublic }?.forEach {
                reportError(it, it.name.orEmpty())
            }
            klass.body?.properties?.filter { it.isPublic }?.forEach {
                reportError(it, it.name.orEmpty())
            }
        }
    }

    private fun reportError(psiElement: PsiElement, name: String) {
        report(
            CorrectableCodeSmell(
                issue = issue,
                entity = Entity.from(psiElement),
                message = """ViewModel should not have public parameters or properties. Make variable $name as private.""",
                references = emptyList(),
                autoCorrectEnabled = false
            )
        )
    }
}