package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtClass

class ViewModelShouldDerivedFromBaseViewModel(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "ViewModel should be derived from BaseViewModel",
        Debt.TEN_MINS
    )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if ("BaseViewModel" == klass.name)
            return

        val hasDefaultViewModel = klass.getSuperTypeList()?.entries
            ?.any {
                it.typeAsUserType?.referencedName == "ViewModel" || it.typeAsUserType?.referencedName == "AndroidViewModel"
            } ?: false

        if (hasDefaultViewModel) {
            report(
                CorrectableCodeSmell(
                    issue = issue,
                    entity = Entity.from(klass),
                    message = """The function ${klass.name} should be derived from BaseViewModel""",
                    references = emptyList(),
                    autoCorrectEnabled = false
                )
            )
        }
    }
}