package com.interview.tsetchallenge

import com.interview.tsetchallenge.model.CalculationParametersDTO
import com.interview.tsetchallenge.model.CalculationResult
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class CalculationService(
    private val calculationResultRepository: CalculationResultRepository
){
    @Async
    fun runCalculationAndStoreResultAsync(calculationParameters: CalculationParametersDTO, calculationId : Int){

        val result = this.runFutureValueCalculation(calculationParameters)
        val roundedResult = String.format("%.2f", result).toDouble()
        this.saveCalculationResult(calculationId,roundedResult)
   }

    fun getCalculationResultById(calculationId : Int) : Double?{
        return calculationResultRepository.findByIdOrNull(calculationId)?.result
    }

    private fun runFutureValueCalculation(param: CalculationParametersDTO): Double {
        val principal = param.principal
        val annualRate = param.annualInterestRate/100
        val timesCompounded = param.timesPerYear
        val years = param.years
        val regularContribution = param.monthlyContribution


        val futureValue = principal * (1 + annualRate / timesCompounded).pow(timesCompounded * years)

        return if(regularContribution!=null){

            futureValue + regularContribution * ((1 + annualRate / timesCompounded).pow(timesCompounded * years) - 1) / (annualRate / timesCompounded)

        }else{
            futureValue
        }

    }

    private fun saveCalculationResult(id:Int, result: Double){
        calculationResultRepository.save(CalculationResult(id,result))
    }


}