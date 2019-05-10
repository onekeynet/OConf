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

import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.onosproject.yang.compiler.datamodel.YangContainer;
import org.onosproject.yang.compiler.datamodel.YangLeafList;
import org.onosproject.yang.compiler.datamodel.YangList;
import org.onosproject.yang.compiler.datamodel.YangModule;
import org.onosproject.yang.compiler.datamodel.YangNode;
import org.onosproject.yang.compiler.datamodel.YangNodeType;
import org.onosproject.yang.compiler.datamodel.YangStatusType;
import org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;
import org.onosproject.yang.compiler.parser.impl.YangUtilsParserManager;

import java.io.IOException;
import java.util.ListIterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test cases for testing leaf-list listener.
 */
public class LeafListListenerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final YangUtilsParserManager manager = new YangUtilsParserManager();

    /**
     * Checks all the values of leaf-list sub-statements are set correctly.
     */
    @Test
    public void processLeafListSubStatements() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/LeafListSubStatements.yang");

        // Check whether the data model tree returned is of type module.
        assertThat((node instanceof YangModule), is(true));

        // Check whether the node type is set properly to module.
        assertThat(node.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) node;
        assertThat(yangNode.getName(), is("Test"));

        ListIterator<YangLeafList> leafListIterator = yangNode.getListOfLeafList().listIterator();
        YangLeafList leafListInfo = leafListIterator.next();

        assertThat(leafListInfo.getName(), is("invalid-interval"));
        assertThat(leafListInfo.getDataType().getDataTypeName(), is("uint16"));
        assertThat(leafListInfo.getDataType().getDataType(), is(YangDataTypes.UINT16));
        assertThat(leafListInfo.getUnits(), is("\"seconds\""));
        assertThat(leafListInfo.getDescription(), is("\"Interval before a route is declared invalid\""));
        assertThat(leafListInfo.isConfig(), is(true));
        assertThat(leafListInfo.getMaxElements().getMaxElement(), is(3));
        assertThat(leafListInfo.getStatus(), Is.is(YangStatusType.CURRENT));
        assertThat(leafListInfo.getReference(), is("\"RFC 6020\""));
    }

    /**
     * Checks whether exception is thrown when leaf-list identifier starts with
     * digit.
     */
    @Test
    public void processLeafListInvalidIdentifier() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("YANG file error : leaf-list name 1invalid-interval is not valid.");
        YangNode node = manager.getDataModel("src/test/resources/LeafListInvalidIdentifier.yang");
    }

    /**
     * Checks whether exception is thrown when leaf-list keyword is incorrect.
     */
    @Test(expected = ParserException.class)
    public void processLeafListInvalidStatement() throws IOException, ParserException {
        YangNode node = manager.getDataModel("src/test/resources/LeafListInvalidStatement.yang");
    }

    /**
     * Checks whether exception is thrown when leaf-list keyword without Left
     * brace as per grammar.
     */
    @Test
    public void processLeafListWithoutLeftBrace() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("missing '{' at 'type'");
        YangNode node = manager.getDataModel("src/test/resources/LeafListWithoutLeftBrace.yang");
    }

    /**
     * Checks whether exception is thrown when config statement cardinality is
     * not as per grammar.
     */
    @Test
    public void processLeafListConfigInvalidCardinality() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("YANG file error: \"config\" is defined more than once in \"leaf-list " +
                "invalid-interval\".");
        YangNode node = manager.getDataModel("src/test/resources/LeafListConfigInvalidCardinality.yang");
    }

    /**
     * Checks whether exception is thrown when units statement cardinality is
     * not as per grammar.
     */
    @Test
    public void processLeafListUnitsInvalidCardinality() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("YANG file error: \"units\" is defined more than once in \"leaf-list invalid-interval\"");
        YangNode node = manager.getDataModel("src/test/resources/LeafListUnitsInvalidCardinality.yang");
    }

    /**
     * Checks leaf-list statement as sub-statement of container.
     */
    @Test
    public void processContainerSubStatementLeafList() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/ContainerSubStatementLeafList.yang");

        // Check whether the data model tree returned is of type module.
        assertThat((node instanceof YangModule), is(true));

        // Check whether the node type is set properly to module.
        assertThat(node.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) node;
        assertThat(yangNode.getName(), is("Test"));

        //Check whether the container is child of module.
        YangContainer container = (YangContainer) yangNode.getChild();
        assertThat(container.getName(), is("valid"));

        // Check whether leaf-list properties as set correctly.
        ListIterator<YangLeafList> leafListIterator = container.getListOfLeafList().listIterator();
        YangLeafList leafListInfo = leafListIterator.next();

        assertThat(leafListInfo.getName(), is("invalid-interval"));
        assertThat(leafListInfo.getDataType().getDataTypeName(), is("uint16"));
        assertThat(leafListInfo.getDataType().getDataType(), is(YangDataTypes.UINT16));
        assertThat(leafListInfo.getUnits(), is("\"seconds\""));
        assertThat(leafListInfo.getDescription(), is("\"Interval before a route is declared invalid\""));
        assertThat(leafListInfo.isConfig(), is(true));
        assertThat(leafListInfo.getMinElements().getMinElement(), is(1));
        assertThat(leafListInfo.getMaxElements().getMaxElement(), is(2147483647));
        assertThat(leafListInfo.getStatus(), is(YangStatusType.CURRENT));
        assertThat(leafListInfo.getReference(), is("\"RFC 6020\""));
    }

    /**
     * Checks leaf-list statement as sub-statement of list.
     */
    @Test
    public void processListSubStatementLeafList() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/ListSubStatementLeafList.yang");

        assertThat((node instanceof YangModule), is(true));

        // Check whether the node type is set properly to module.
        assertThat(node.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) node;
        assertThat(yangNode.getName(), is("Test"));

        // Check whether the list is child of module
        YangList yangList = (YangList) yangNode.getChild();
        assertThat(yangList.getName(), is("valid"));

        // Check whether leaf-list properties as set correctly.
        ListIterator<YangLeafList> leafListIterator = yangList.getListOfLeafList().listIterator();
        YangLeafList leafListInfo = leafListIterator.next();

        assertThat(leafListInfo.getName(), is("invalid-interval"));
        assertThat(leafListInfo.getDataType().getDataTypeName(), is("uint16"));
        assertThat(leafListInfo.getDataType().getDataType(), is(YangDataTypes.UINT16));
        assertThat(leafListInfo.getUnits(), is("\"seconds\""));
        assertThat(leafListInfo.getDescription(), is("\"Interval before a route is declared invalid\""));
        assertThat(leafListInfo.isConfig(), is(true));

        assertThat(leafListInfo.getStatus(), is(YangStatusType.CURRENT));
        assertThat(leafListInfo.getReference(), is("\"RFC 6020\""));
    }
}
