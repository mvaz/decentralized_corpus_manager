package net.corda.training.com.dcm.state

import com.dcm.states.ModelState
import net.corda.core.contracts.UniqueIdentifier
import org.junit.Test
import kotlin.test.assertEquals
import net.corda.training.ALICE
import net.corda.training.BOB
import org.junit.Before

class ModelStateTests {
    private var testClassificationReport : LinkedHashMap<String, LinkedHashMap<String, Double>> = LinkedHashMap<String, LinkedHashMap<String, Double>>()
    private var newClassificationReport : LinkedHashMap<String, LinkedHashMap<String, Double>> =  LinkedHashMap<String, LinkedHashMap<String, Double>>()
    private var origCorpus : LinkedHashMap<String, String> =  LinkedHashMap<String, String>()
    private var newCorpus : LinkedHashMap<String, String> =  LinkedHashMap<String, String>()

    @Before
    fun setup(){
        //var testClassificationReport : LinkedHashMap<String, LinkedHashMap<String, Double>> =
        testClassificationReport["BookRestaurant"]?.set("f1-score", 0.9975103734439834)
        testClassificationReport["BookRestaurant"]?.set("precision", 0.9983388704318937)
        testClassificationReport["BookRestaurant"]?.set("recall", 0.9966832504145937)
        testClassificationReport["BookRestaurant"]?.set("support", 603.0)
        testClassificationReport["GetWeather"]?.set("f1-score", 0.9966273187183812)
        testClassificationReport["GetWeather"]?.set("precision", 0.9983108108108109)
        testClassificationReport["GetWeather"]?.set("recall", 0.9949494949494949)
        testClassificationReport["GetWeather"]?.set("support", 594.0)
        testClassificationReport["Negative"]?.set("f1-score", 0.9993197278911564)
        testClassificationReport["Negative"]?.set("precision", 0.9989799387963277)
        testClassificationReport["Negative"]?.set("recall", 0.9996597482136781)
        testClassificationReport["Negative"]?.set("support", 2939.0)

        //var newClassificationReport : LinkedHashMap<String, LinkedHashMap<String, Double>>
        newClassificationReport["BookRestaurant"]?.set("f1-score", 0.998000)
        newClassificationReport["BookRestaurant"]?.set("precision", 0.999000)
        newClassificationReport["BookRestaurant"]?.set("recall", 0.997000)
        newClassificationReport["BookRestaurant"]?.set("support", 624.0)
        newClassificationReport["GetWeather"]?.set("f1-score", 0.997000)
        newClassificationReport["GetWeather"]?.set("precision", 0.999000)
        newClassificationReport["GetWeather"]?.set("recall", 0.995000)
        newClassificationReport["GetWeather"]?.set("support", 597.0)
        newClassificationReport["Negative"]?.set("f1-score", 0.9994000)
        newClassificationReport["Negative"]?.set("precision", 0.999000)
        newClassificationReport["Negative"]?.set("recall", 0.9997000)
        newClassificationReport["Negative"]?.set("support", 2800.0)

        //var origCorpus : LinkedHashMap<String, String>
        origCorpus["What will the weather be this year in Horseshoe Lake State Fish and Wildlife Area?"] = "GetWeather"
        origCorpus["Book a bistro in New Zealand in 119 and a half days."] = "BookRestaurant"
        origCorpus["add how to my week end playlist"] = "Negative"

        //var newCorpus : LinkedHashMap<String, String> =  LinkedHashMap<String, String>()
        newCorpus["What will the weather be this year in Horseshoe Lake State Fish and Wildlife Area?"] = "GetWeather"
        newCorpus["Will it snowstorm neighboring the Rio Grande Wild and Scenic River on feb. the second?"] = "GetWeather"
        newCorpus["Book a bistro in New Zealand in 119 and a half days."] = "BookRestaurant"
        newCorpus["Book a table at T-Rex distant from Halsey St."] = "BookRestaurant"
        newCorpus["add how to my week end playlist"] = "Negative"
        newCorpus["add clem burke in my playlist Pre-Party R&B Jams"] = "Negative"
    }

    @Test
    fun hasAllFieldsAndProperTypes(){
        ModelState::class.java.getDeclaredField("corpus")
        assertEquals(ModelState::class.java.getDeclaredField("corpus").type, LinkedHashMap::class.java)

        ModelState::class.java.getDeclaredField("classificationReport")
        assertEquals(ModelState::class.java.getDeclaredField("classificationReport").type, LinkedHashMap::class.java)

        ModelState::class.java.getDeclaredField("participants")
        assertEquals(ModelState::class.java.getDeclaredField("participants").type, List::class.java)

        ModelState::class.java.getDeclaredField("linearId")
        assertEquals(ModelState::class.java.getDeclaredField("linearId").type, UniqueIdentifier::class.java)
    }

    @Test
    fun checkReplaceModelCorpus(){
        val model = ModelState(
                corpus = origCorpus,
                classificationReport = testClassificationReport,
                participants = listOf(ALICE.party, BOB.party)
        )

        assertEquals(newCorpus, model.replaceModelCorpus(newCorpus).corpus)
    }

    @Test
    fun checkReplaceModelClassificationReport(){
        val model = ModelState(
            corpus = origCorpus,
            classificationReport =testClassificationReport,
            participants = listOf(ALICE.party, BOB.party)
        )

        assertEquals(newClassificationReport, model.replaceClassificationReport(newClassificationReport).classificationReport)
    }
}