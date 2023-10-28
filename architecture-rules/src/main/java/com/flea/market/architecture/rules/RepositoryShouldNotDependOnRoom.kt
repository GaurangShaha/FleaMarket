package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.CorrectableCodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity.CodeSmell
import org.jetbrains.kotlin.com.intellij.psi.PsiFile
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.psiUtil.getChildrenOfType

class RepositoryShouldNotDependOnRoom(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        CodeSmell,
        """Repository should not depend on classes from room library. Please create a LocalSource to remove the dependency""",
        Debt.TWENTY_MINS
    )

    override fun visitFile(file: PsiFile) {
        super.visitFile(file)
        if (file.fileType != KotlinFileType.INSTANCE)
            return

        file.getChildrenOfType<KtClass>()
            .filter { klass -> klass.name?.endsWith("Repository") ?: false }
            .forEach { klass ->
                val hasRetrofitDependency = (file as KtFile).importList?.imports
                    ?.any { import -> import.text.contains("androidx.room") } ?: false

                if (hasRetrofitDependency) {
                    report(
                        CorrectableCodeSmell(
                            issue = issue,
                            entity = Entity.from(klass),
                            message = """The repository ${klass.name} has a dependency on classes from room library. Please create a ${
                                klass.name?.replace("Repository", "")
                            }LocalSource to remove the dependency""",
                            references = emptyList(),
                            autoCorrectEnabled = false
                        )
                    )
                }
            }
    }
}
