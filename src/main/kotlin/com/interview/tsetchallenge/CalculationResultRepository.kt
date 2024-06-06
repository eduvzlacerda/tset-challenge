package com.interview.tsetchallenge

import com.interview.tsetchallenge.model.CalculationResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CalculationResultRepository : JpaRepository<CalculationResult, Int> {
}