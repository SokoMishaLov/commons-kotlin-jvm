/**
 * Copyright 2019-2020 the original author or authors.
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

package ru.sokomishalov.commons.spring.net

import org.springframework.util.SocketUtils.*
import ru.sokomishalov.commons.spring.net.SocketType.TCP
import ru.sokomishalov.commons.spring.net.SocketType.UDP

fun randomFreePort(
        type: SocketType = TCP,
        range: IntRange = (PORT_RANGE_MIN..PORT_RANGE_MAX)
): Int {
    return when (type) {
        TCP -> findAvailableTcpPort(range.first, range.last)
        UDP -> findAvailableUdpPort(range.first, range.last)
    }
}