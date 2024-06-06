package com.interview.tsetchallenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class TsetChallengeApplication

fun main(args: Array<String>) {
    runApplication<TsetChallengeApplication>(*args)
}
