package com.flea.market.architecture.rules

import com.flea.market.architecture.util.returnsFlow
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtNamedFunction

class FunctionReturnsFlowNaming(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "Function that returns flow should end with stream word",
        Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (function.returnsFlow && !function.name.orEmpty().endsWith("Stream")) {
            report(
                CorrectableCodeSmell(
                    issue = issue,
                    entity = Entity.from(function),
                    message = """Function ${function.name} returns flow so it should end with stream word""",
                    references = emptyList(),
                    autoCorrectEnabled = false
                )
            )
        }
    }
}