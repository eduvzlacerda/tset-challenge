package com.interview.tsetchallenge.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class CalculationResult(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "result")
    val result: Float
)