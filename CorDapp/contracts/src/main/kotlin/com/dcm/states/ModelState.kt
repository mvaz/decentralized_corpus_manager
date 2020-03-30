package com.dcm.states

import com.dcm.contract.ModelContract
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.ContractState
import net.corda.core.contracts.LinearState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.identity.Party
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

/*********
@Dev: The Model State Object represents the characteristics commonly found in Machine Learning Models.
@param corpus each intent and its relative label -> intent:label.
@param classificationReport the classification report for each label
For example:
    {
        "MyLabelA": {
        "precision": 0.9983108108108109,
        "recall": 0.9949494949494949,
        "f1-score": 0.9966273187183812,
        "support": 594
        }
    }
@param participants all contributers to the data set.
 *********/
@BelongsToContract(ModelContract::class)
data class ModelState(
        val corpus: LinkedHashMap<String, String>,
        val classificationReport: LinkedHashMap<String, LinkedHashMap<String, Double>>,
        val creator: Party,
        override val participants: List<Party>,
        override val linearId: UniqueIdentifier = UniqueIdentifier()) : LinearState{

    fun replaceModelCorpus(newCorpus: LinkedHashMap<String, String>) : ModelState{
        return copy(corpus = newCorpus)
    }

    fun replaceClassificationReport( newClassificationReport: LinkedHashMap<String, LinkedHashMap<String, Double>>) : ModelState {
        return copy(classificationReport = newClassificationReport)
    }

}
