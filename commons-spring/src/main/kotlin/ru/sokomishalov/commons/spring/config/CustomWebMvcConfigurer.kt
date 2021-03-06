/**
 * Copyright (c) 2019-present Mikhael Sokolov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("unused")

package ru.sokomishalov.commons.spring.config

import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ru.sokomishalov.commons.core.serialization.OBJECT_MAPPER
import java.util.concurrent.Executors

open class CustomWebMvcConfigurer : WebMvcConfigurer {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters += MappingJackson2HttpMessageConverter(OBJECT_MAPPER)
    }

    override fun configureAsyncSupport(configurer: AsyncSupportConfigurer) {
        configurer.setTaskExecutor(ConcurrentTaskExecutor(Executors.newCachedThreadPool()))
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .maxAge(3600)
    }
}