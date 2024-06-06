package com.interview.tsetchallenge

import com.interview.tsetchallenge.exceptionsHandeling.CalculationResultNotFoundException
import com.interview.tsetchallenge.model.CalculationParametersDTO
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.random.Random


@RestController
@RequestMapping("/api/calculation")
class CalculationRestController(
    private val calculationService: CalculationService,
) {

    @PostMapping("/initiate")
    fun startCalculation(
       @Valid @RequestBody calculationParameters : CalculationParametersDTO
    ):Int{
        val calculationId = this.generateRandomId()
        calculationService.runCalculationAndStoreResultAsync(calculationParameters,calculationId)
        return calculationId
    }

    @GetMapping("/result/{calculation-id}")
    fun fetchResultById(@PathVariable("calculation-id") calculationId: Int): Double {
        return calculationService.getCalculationResultById(calculationId)
            ?: throw CalculationResultNotFoundException(
                "Calculation with id $calculationId not found. " +
                    "Consider waiting for the calculation ti be finished"
            )
    }

    private fun generateRandomId():Int{
        return Random.nextInt(1, 5000)
    }
}