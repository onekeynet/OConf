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

package org.onosproject.yang.compiler.parser.impl.listeners;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.onosproject.yang.compiler.datamodel.YangLeaf;
import org.onosproject.yang.compiler.datamodel.YangModule;
import org.onosproject.yang.compiler.datamodel.YangNode;
import org.onosproject.yang.compiler.datamodel.YangNodeType;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;
import org.onosproject.yang.compiler.parser.impl.YangUtilsParserManager;

import java.io.IOException;
import java.util.ListIterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test case for mandatory listener.
 */
public class MandatoryListenerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final YangUtilsParserManager manager = new YangUtilsParserManager();

    /**
     * Checks valid mandatory with value true statement.
     */
    @Test
    public void processMandatoryTrue() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/MandatoryTrue.yang");

        // Check whether the data model tree returned is of type module.
        assertThat((node instanceof YangModule), is(true));

        // Check whether the node type is set properly to module.
        assertThat(node.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) node;
        assertThat(yangNode.getName(), is("Test"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        // Check whether the mandatory value is set correctly.
        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.isMandatory(), is(true));
    }

    /**
     * Checks valid mandatory with value false statement.
     */
    @Test
    public void processMandatoryFalse() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/MandatoryFalse.yang");

        // Check whether the data model tree returned is of type module.
        assertThat((node instanceof YangModule), is(true));

        // Check whether the node type is set properly to module.
        assertThat(node.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) node;
        assertThat(yangNode.getName(), is("Test"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        // Check whether the mandatory value is set correctly.
        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.isMandatory(), is(false));
    }

    /**
     * Checks default value of mandatory statement.
     */
    @Test
    public void processMandatoryDefaultValue() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/MandatoryDefaultValue.yang");

        // Check whether the data model tree returned is of type module.
        assertThat((node instanceof YangModule), is(true));

        // Check whether the node type is set properly to module.
        assertThat(node.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) node;
        assertThat(yangNode.getName(), is("Test"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        // Check whether the mandatory value is set correctly.
        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.isMandatory(), is(false));
    }

    /**
     * Checks invalid of mandatory statement and expects exception.
     */
    @Test
    public void processMandatoryEmptyStatement() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("no viable alternative at input ';'");
        YangNode node = manager.getDataModel("src/test/resources/MandatoryEmptyStatement.yang");
    }

    /**
     * Checks invalid mandatory statement(without statement end) and expects
     * exception.
     */
    @Test
    public void processMandatoryWithoutStatementEnd() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("no viable alternative at input '}'");
        YangNode node = manager.getDataModel("src/test/resources/MandatoryWithoutStatementEnd.yang");
    }
}
