package com.sandbox.springoutboxpattern

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OTELConfiguration {

    @Bean
    fun otlpHttpSpanExporter(@Value("\${tracing.url}") url: String): OtlpHttpSpanExporter {
        return OtlpHttpSpanExporter.builder()
            .setEndpoint(url)
            .build()
    }
}
