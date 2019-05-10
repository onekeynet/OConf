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

package org.onosproject.yang.compiler.parser;

import org.onosproject.yang.compiler.datamodel.YangNode;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;

import java.io.IOException;

/**
 * Abstraction of entity which provides parser service of YANG files for yangutils-maven-plugin.
 */
public interface YangUtilsParser {

    /**
     * Returns the data model node. It is an entry function to initiate the YANG file parsing.
     *
     * @param file input YANG file
     * @return YangNode root node of the data model tree
     * @throws ParserException when fails to get the data model
     * @throws IOException when there is an exception in IO operation
     */
    YangNode getDataModel(String file) throws IOException, ParserException;
}
