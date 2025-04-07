package com.example.retrofit.server.util

import org.springframework.security.core.context.SecurityContextHolder

fun getSecurityAuthUid(): String {
    return SecurityContextHolder.getContext().authentication.principal as String
}
