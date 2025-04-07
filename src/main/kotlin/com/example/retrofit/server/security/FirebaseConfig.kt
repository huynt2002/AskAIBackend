package com.example.retrofit.server.security

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.ByteArrayInputStream

@Configuration
class FirebaseConfig {

    @Bean
    fun firebaseApp(): FirebaseApp {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                val serviceAccount = ByteArrayInputStream(System.getenv("FIREBASE_CONFIG"))
                    //this::class.java.getResourceAsStream("/private-key.json")
                        ?: throw IllegalStateException(
                            "Firebase service account JSON not found in resources"
                        )

                val options =
                    FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build()

                FirebaseApp.initializeApp(options)
                println("FirebaseApp initialized successfully")
            }
            return FirebaseApp.getInstance()
        } catch (e: Exception) {
            println("Firebase initialization failed: ${e.message}")
            e.printStackTrace()
            throw e // rethrow so Spring logs it
        }
    }

    @Bean
    fun firebaseAuth(firebaseApp: FirebaseApp): FirebaseAuth {
        return FirebaseAuth.getInstance(firebaseApp)
    }
}
