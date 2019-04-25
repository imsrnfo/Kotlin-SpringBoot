package com.example.demo.configuration.security

import com.example.demo.configuration.grafana.METRIC_LOGIN_COUNTER
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationSuccessHandlerImpl : AuthenticationSuccessHandler {

    private val redirectStrategy = DefaultRedirectStrategy()

    @Autowired
    lateinit var mreg: MeterRegistry

    @Throws(IOException::class)
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        if (response.isCommitted) return
        redirectStrategy.sendRedirect(request, response, "/home")

        val session = request.getSession(false) ?: return
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)

        //grafana
        mreg.timer(METRIC_LOGIN_COUNTER).record(1, TimeUnit.MILLISECONDS)

    }
}