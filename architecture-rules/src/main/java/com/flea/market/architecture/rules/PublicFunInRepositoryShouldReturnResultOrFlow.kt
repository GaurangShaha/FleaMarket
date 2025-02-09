package com.flea.market.architecture.rules

import com.flea.market.architecture.util.returnsFlow
import com.flea.market.architecture.util.returnsResult
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.psiUtil.isPublic

class PublicFunInRepositoryShouldReturnResultOrFlow(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "Public function in repository should return either Result or Flow",
        Debt.TWENTY_MINS
    )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        if (klass.name?.endsWith("Repository") == true && klass.isInterface()) {
            klass.body?.functions?.filter { namedFunction ->
                namedFunction.isPublic && !(namedFunction.returnsFlow || namedFunction.returnsResult)
            }?.forEach { namedFunction ->
                report(
                    CorrectableCodeSmell(
                        issue,
                        entity = Entity.from(namedFunction),
                        message = """The repository ${klass.name} has a public function ${namedFunction.name} which does not return Flow or Result. Please consider removing ${namedFunction.name} or change return type to Result/Flow.""",
                        references = emptyList(),
                        autoCorrectEnabled = false
                    )
                )
            }
        }
    }
}
