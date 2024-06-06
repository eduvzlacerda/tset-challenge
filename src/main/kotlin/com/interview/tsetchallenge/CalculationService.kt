package com.interview.tsetchallenge

import com.interview.tsetchallenge.model.CalculationParametersDTO
import com.interview.tsetchallenge.model.CalculationResult
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToLong

@Service
class CalculationService(
    private val calculationResultRepository: CalculationResultRepository
){
   @Async
    fun runCalculationAndStoreResult(calculationParameters: CalculationParametersDTO, calculationId : Int){

        val result = this.runCalculation(calculationParameters).toFloat()
       //round result to 2 decimal places
        val roundedResult = result.toBigDecimal().setScale(2, RoundingMode.UP).toFloat()
        this.saveCalculationResult(calculationId,roundedResult)
    }

    fun getCalculationResultById(calculationId : Int) : Float?{
        return calculationResultRepository.findByIdOrNull(calculationId)?.result
    }

    private fun runCalculation(param: CalculationParametersDTO): Double {
        val principal = param.principal
        val annualRate = param.annualInterestRate/100
        val timesCompounded = param.timesPerYear
        val years = param.years

        return principal * (1 + annualRate / timesCompounded).pow(timesCompounded * years)
    }

    private fun saveCalculationResult(id:Int, result: Float){
        calculationResultRepository.save(CalculationResult(id,result))
    }


}