package com.flea.market.architecture.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class ArchitectureRulesProvider : RuleSetProvider {
    override val ruleSetId = "ArchitectureRule"

    override fun instance(config: Config) = RuleSet(
        id = ruleSetId,
        rules = listOf(
            DBFunctionShouldBeSuspended(config),
            PublicFunInRepositoryShouldReturnResultOrFlow(config),
            RepositoryShouldNotDependOnRetrofit(config),
            RepositoryShouldNotDependOnRoom(config),
            RetrofitFunctionShouldBeSuspended(config),
            ViewModelShouldDerivedFromBaseViewModel(config),
            ViewModelShouldNotHaveSavedStateHandleAsParam(config),
            ViewModelShouldNotHavePublicMethod(config),
            ViewModelShouldNotHavePublicProperty(config),
        )
    )
}