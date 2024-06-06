package com.interview.tsetchallenge.model

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Positive
import org.springframework.cglib.proxy.InterfaceMaker

data class CalculationParametersDTO(
    @field:Positive(message = "Principal must be greater than zero")
    val principal: Double,

    val annualInterestRate: Double,

    @field:Min(value = 1, message = "timesPerYear must be at least 1")
    val timesPerYear: Int,

    @field:Positive(message = "years must be greater than zero")
    val years: Int
)

