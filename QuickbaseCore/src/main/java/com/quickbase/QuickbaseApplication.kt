package com.quickbase

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class QuickbaseApplication

fun main(args: Array<String>) {
    SpringApplication.run(QuickbaseApplication::class.java, *args)
}
