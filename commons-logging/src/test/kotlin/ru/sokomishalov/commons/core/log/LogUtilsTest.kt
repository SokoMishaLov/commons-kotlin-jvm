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
package ru.sokomishalov.commons.core.log

import org.junit.Test

/**
 * @author sokomishalov
 */

class LogUtilsTest {

    @Test
    fun `Assert logger`() {
        LogWithInterface().doJob()
        LogWithCompanion().doJob()
        LogWithDelegate().doJob()
    }

}

class LogWithInterface : Loggable {
    fun doJob() {
        log("interface")
    }
}

class LogWithCompanion {

    companion object : Loggable

    fun doJob() {
        logInfo { "companion" }
    }
}

class LogWithDelegate {

    companion object {
        private val log by loggerDelegate()
    }

    fun doJob() {
        log.info { "companion delegate" }
    }
}