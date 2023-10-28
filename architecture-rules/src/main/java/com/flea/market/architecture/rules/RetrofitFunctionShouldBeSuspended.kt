package com.flea.market.architecture.rules

import com.flea.market.architecture.util.isSuspend
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtNamedFunction

class RetrofitFunctionShouldBeSuspended(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "Retrofit functions should be marked as suspended",
        Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        val currentRetrofitAnnotation = function.annotationEntries.filter { annotation ->
            knownRetrofitAnnotations.any { (annotationName, importName) ->
                annotationName == annotation.shortName.toString() &&
                        function.containingKtFile.importList?.imports
                            ?.any { import -> import.importPath.toString() == importName } ?: false
            }
        }

        if (currentRetrofitAnnotation.isNotEmpty() && !function.isSuspend) {
            report(
                CorrectableCodeSmell(
                    issue = issue,
                    entity = Entity.from(function),
                    message = """The function ${function.name} should be marked as suspend""",
                    references = emptyList(),
                    autoCorrectEnabled = false
                )
            )
        }
    }

    private val knownRetrofitAnnotations = listOf(
        "GET" to "retrofit2.http.GET",
        "DELETE" to "retrofit2.http.DELETE",
        "PATCH" to "retrofit2.http.PATCH",
        "POST" to "retrofit2.http.POST",
        "PUT" to "retrofit2.http.PUT",
    )
}