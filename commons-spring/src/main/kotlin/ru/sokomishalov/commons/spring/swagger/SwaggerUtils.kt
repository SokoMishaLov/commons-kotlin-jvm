@file:Suppress("unused")

package ru.sokomishalov.commons.spring.swagger

import com.fasterxml.classmate.TypeResolver
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.schema.AlternateTypeRules.newRule
import springfox.documentation.service.Contact
import springfox.documentation.service.SecurityScheme
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

/**
 * @author sokomishalov
 */

fun Docket.customizeDocket(
        securityContext: SecurityContext? = null,
        securityScheme: SecurityScheme? = null,
        ignoredParameterTypes: List<Class<out Any>> = listOf(ServerHttpRequest::class.java),
        genericModelSubstitutes: List<Class<out Any>> = listOf(ResponseEntity::class.java, Mono::class.java),
        title: String = "",
        description: String = "",
        contact: Contact = Contact("Sokolov Mikhael", "https://t.me/sokomishalov", "sokomishalov@mail.ru"),
        version: String = "1.0.0"
): Docket {
    val typeResolver = TypeResolver()
    return this
            .securityContexts(when {
                securityContext != null -> listOf(securityContext)
                else -> emptyList()
            })
            .securitySchemes(when {
                securityScheme != null -> listOf(securityScheme)
                else -> emptyList()
            })
            .ignoredParameterTypes(*ignoredParameterTypes.toTypedArray())
            .genericModelSubstitutes(*genericModelSubstitutes.toTypedArray())
            .alternateTypeRules(
                    newRule(
                            typeResolver.resolve(Flux::class.java, FilePart::class.java),
                            typeResolver.resolve(List::class.java, MultipartFile::class.java)
                    ),
                    newRule(
                            typeResolver.resolve(Mono::class.java, FilePart::class.java),
                            typeResolver.resolve(MultipartFile::class.java)
                    )
            )
            .apiInfo(ApiInfoBuilder()
                    .title(title)
                    .description(description)
                    .license("Apache 2.0")
                    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                    .contact(contact)
                    .version(version)
                    .build()
            )
}