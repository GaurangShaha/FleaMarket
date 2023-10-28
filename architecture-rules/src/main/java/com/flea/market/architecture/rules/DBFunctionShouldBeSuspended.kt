package com.flea.market.architecture.rules

import com.flea.market.architecture.util.isSuspend
import com.flea.market.architecture.util.returnsFlow
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.psi.KtNamedFunction

class DBFunctionShouldBeSuspended(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        "DB functions of room should be marked as suspended",
        Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        val currentRoomAnnotation = function.annotationEntries.filter { annotation ->
            knownRoomAnnotations.any { (annotationName, importName) ->
                annotationName == annotation.shortName.toString() &&
                        function.containingKtFile.importList?.imports
                            ?.any { import -> import.importPath.toString() == importName } ?: false
            }
        }

        if (currentRoomAnnotation.isNotEmpty() && !(function.isSuspend || function.returnsFlow)) {
            report(
                CorrectableCodeSmell(
                    issue = issue,
                    entity = Entity.from(function),
                    message = """The function ${function.name} should be marked as suspend as it is performing CURD operations on DB.""",
                    references = emptyList(),
                    autoCorrectEnabled = false
                )
            )
        }
    }

    private val knownRoomAnnotations = listOf(
        "Query" to "androidx.room.Query",
        "Upsert" to "androidx.room.Upsert",
        "Insert" to "androidx.room.Insert",
        "RawQuery" to "androidx.room.RawQuery",
        "Transaction" to "androidx.room.Transaction",
        "Update" to "androidx.room.Update",
    )
}