package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtClass

class ViewModelShouldNotHaveSavedStateHandleAsParam(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "ViewModel should not have SavedStateHandle",
        Debt.TWENTY_MINS
    )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        val isViewModel = klass.getSuperTypeList()?.entries
            ?.any { it.typeAsUserType?.referencedName == "BaseViewModel" } ?: false

        if (isViewModel) {
            klass.primaryConstructor?.valueParameters
                ?.filter { it?.typeReference?.text == "SavedStateHandle" }
                ?.forEach {
                    report(
                        CorrectableCodeSmell(
                            issue = issue,
                            entity = Entity.from(it),
                            message = """Remove variable of SavedStateHandle from ${klass.name}, instead pass it by creating a wrapper class for navigation argument.""",
                            references = emptyList(),
                            autoCorrectEnabled = false
                        )
                    )
                }
        }
    }
}