package com.flea.market.architecture.util

import org.jetbrains.kotlin.psi.KtNamedFunction

val KtNamedFunction.isSuspend: Boolean
    get() = modifierList?.text?.contains("suspend") ?: false

val KtNamedFunction.returnsFlow: Boolean
    get() = typeReference?.let {
        it.text.startsWith("Flow") || it.text.startsWith("SharedFlow") ||
                it.text.startsWith("StateFlow")
    } ?: false

val KtNamedFunction.returnsResult: Boolean
    get() = typeReference?.text?.startsWith("Result") ?: false

val KtNamedFunction.isComposable: Boolean
    get() = annotationEntries.any { it.shortName.toString() == "Composable" }

