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
package ru.sokomishalov.commons.core.io

import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/**
 * @author sokomishalov
 */
class ZipIterator(private val zis: ZipInputStream) : Iterator<ZipEntry> {
    private var next: ZipEntry? = null

    override operator fun hasNext(): Boolean {
        next = zis.nextEntry
        return next != null
    }

    override operator fun next(): ZipEntry = next ?: throw NoSuchElementException()
}