package com.flea.market.architecture.rules

import com.flea.market.architecture.util.isComposed
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtNamedFunction

class ComposeShouldNotHaveNavHostControllerParam(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "Compose functions should not have NavHostController as parameter",
        Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (function.isComposed) {
            function.valueParameters.filter {
                it.typeReference?.text?.equals("NavHostController") ?: false
            }.map {
                report(
                    CorrectableCodeSmell(
                        issue = issue,
                        entity = Entity.from(it),
                        message = """The function ${function.name} should not have NavHostController as parameter.""",
                        references = emptyList(),
                        autoCorrectEnabled = false
                    )
                )
            }
        }
    }
}