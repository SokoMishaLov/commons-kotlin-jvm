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
@file:Suppress("JAVA_CLASS_ON_COMPANION", "unused")

package ru.sokomishalov.commons.core.log

import org.slf4j.Logger

/**
 * @author sokomishalov
 */
interface Loggable {

    val logger: Logger get() = CustomLoggerFactory.getLogger(javaClass)

    fun logInfo(s: String?) = logger.info(s)

    fun logInfo(lazyMessage: () -> String?) = logger.info(lazyMessage = lazyMessage)

    fun logDebug(s: String?) = logger.debug(s)

    fun logDebug(lazyMessage: () -> String?) = logger.debug(lazyMessage = lazyMessage)

    fun logWarn(t: Throwable) = logger.warn(t.message, t)

    fun logWarn(message: String?) = logger.warn(message)

    fun logWarn(lazyMessage: () -> String?) = logger.warn(lazyMessage = lazyMessage)

    fun logTrace(message: String?) = logger.trace(message)

    fun logTrace(lazyMessage: () -> String?) = logger.trace(lazyMessage = lazyMessage)

    fun logError(message: String?, throwable: Throwable) = logger.error(message, throwable)

    fun logError(throwable: Throwable, message: String? = throwable.message) = logger.error(message, throwable)

    fun logError(throwable: Throwable, lazyMessage: () -> String?) = logger.error(throwable = throwable, lazyMessage = lazyMessage)
}