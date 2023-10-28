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

class ComposeShouldCollectFlowWithLifecycle(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "Compose should collect flow with lifecycle",
        Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (function.isComposable) {
            function.containingKtFile.importList?.imports?.filter {
                knowInvalidCollectCall.contains(it.importPath.toString())
            }?.forEach {
                report(
                    CorrectableCodeSmell(
                        issue = issue,
                        entity = Entity.from(it),
                        message = """Please use collectAsStateWithLifecycle() in the function ${function.name} instead of ${it.importPath.toString()}""",
                        references = emptyList(),
                        autoCorrectEnabled = false
                    )
                )
            }
        }
    }

    private val knowInvalidCollectCall = listOf(
        "kotlinx.coroutines.flow.collect",
        "androidx.compose.runtime.collectAsState",
        "kotlinx.coroutines.flow.collectIndexed",
        "kotlinx.coroutines.flow.collectLatest",
        "kotlinx.coroutines.flow.toCollection",
        "kotlinx.coroutines.flow.toSet",
        "kotlinx.coroutines.flow.toList"
    )
}