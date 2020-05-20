package at.jku.isse.clones.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R0ACTest : StringSpec({
    "should run through"{
        R0AC.run()
    }

    "equal results"{
        val result = R0AC.targets(R0AC.allDevs).map { targets ->
            val results = R0AC.requests.map {
                targets.invoke(null, it.l, it.r)
            }

            Pair(targets, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result.first().second
        }
    }
})