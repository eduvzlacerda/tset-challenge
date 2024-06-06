package com.interview.tsetchallenge.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class CalculationParametersDTO(

    @field:Positive(message = "Argument 'Principal' is mandatory and must be greater than zero")
    val principal: Double,

    @field:NotNull(message = "Argument 'annualInterestRate' is mandatory")
    val annualInterestRate: Double,

    @field:Positive(message = "Argument 'timesPerYear' is mandatory and must be greater than zero")
    val timesPerYear: Int,

    @field:Positive(message = "Argument 'years' is mandatory a must be greater than zero")
    val years: Int ,

    @field:Positive(message = "Argument 'regularContribution' must be greater than zero")
    val monthlyContribution : Double?
)

