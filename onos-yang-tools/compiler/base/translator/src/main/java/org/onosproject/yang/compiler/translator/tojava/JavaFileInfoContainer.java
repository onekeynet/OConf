/*
 * Copyright 2016-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.yang.compiler.translator.tojava;

/**
 * Represents data model nodes which are required to generate java classes, need to support
 * java file info.
 */
public interface JavaFileInfoContainer {

    /**
     * Returns the generated java file information.
     *
     * @return generated java file information
     */
    JavaFileInfoTranslator getJavaFileInfo();

    /**
     * Sets the java file info object.
     *
     * @param javaInfo java file info object
     */
    void setJavaFileInfo(JavaFileInfoTranslator javaInfo);
}
