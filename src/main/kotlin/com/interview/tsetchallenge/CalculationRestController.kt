package com.interview.tsetchallenge

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
        calculationService.runCalculationAndStoreResult(calculationParameters,calculationId)
        return calculationId
    }
    @GetMapping("/result/{calculation-id}")
    fun fetchResultById(@PathVariable("calculation-id") calculationId : Int) : Double?{
       return calculationService.getCalculationResultById(calculationId)
    }

    private fun generateRandomId():Int{
        return Random.nextInt(1, 5000)
    }
}