package com.flea.market.architecture.rules

import com.flea.market.architecture.util.isComposable
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtNamedFunction

class ComposeShouldNotHaveViewModelParam(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "Compose functions should not have viewmodel as parameter",
        Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (function.isComposable) {
            function.valueParameters.filter {
                val typeReferenceText = it.typeReference?.text.orEmpty()
                typeReferenceText.endsWith("ViewModel") || typeReferenceText.endsWith("VM")
            }.map {
                report(
                    CorrectableCodeSmell(
                        issue = issue,
                        entity = Entity.from(it),
                        message = """The function ${function.name} should not have viewmodel as parameter. Instead please collect and pass the uiState.""",
                        references = emptyList(),
                        autoCorrectEnabled = false
                    )
                )
            }
        }
    }
}