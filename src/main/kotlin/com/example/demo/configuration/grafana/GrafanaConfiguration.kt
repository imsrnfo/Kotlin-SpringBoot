package com.example.demo.configuration.grafana

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

val METRIC_LOGIN_COUNTER = "login.counter"

@Component
class GrafanaConfiguration {

    @Autowired
    lateinit var mreg: MeterRegistry

    @PostConstruct
    fun init() {
        Timer.builder(METRIC_LOGIN_COUNTER).publishPercentiles(0.5, 0.95).description("Usuario ingresado").register(mreg)
    }

}