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
import org.onosproject.yang.compiler.datamodel.YangContainer;
import org.onosproject.yang.compiler.datamodel.YangLeaf;
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
 * Test cases for testing leaf listener.
 */
public class LeafListenerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final YangUtilsParserManager manager = new YangUtilsParserManager();

    /**
     * Checks all the values of leaf sub-statements are set correctly.
     */
    @Test
    public void processLeafSubStatements() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/LeafSubStatements.yang");

        // Check whether the data model tree returned is of type module.
        assertThat((node instanceof YangModule), is(true));

        // Check whether the node type is set properly to module.
        assertThat(node.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) node;
        assertThat(yangNode.getName(), is("Test"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("uint16"));
        assertThat(leafInfo.getDataType().getDataType(), is(YangDataTypes.UINT16));
        assertThat(leafInfo.getUnits(), is("\"seconds\""));
        assertThat(leafInfo.getDescription(), is("\"Interval before a route is declared invalid\""));
        assertThat(leafInfo.isConfig(), is(true));
        assertThat(leafInfo.isMandatory(), is(true));
        assertThat(leafInfo.getStatus(), is(YangStatusType.CURRENT));
        assertThat(leafInfo.getReference(), is("\"RFC 6020\""));
    }

    /**
     * Checks whether exception is thrown when leaf identifier starts with
     * digit.
     */
    @Test
    public void processLeafInvalidIdentifier() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("YANG file error : leaf name 1invalid-interval is not valid.");
        YangNode node = manager.getDataModel("src/test/resources/LeafInvalidIdentifier.yang");
    }

    /**
     * Checks whether exception is thrown when leaf keyword without Left brace
     * as per grammar.
     */
    @Test
    public void processLeafWithoutLeftBrace() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("missing '{' at 'type'");
        YangNode node = manager.getDataModel("src/test/resources/LeafWithoutLeftBrace.yang");
    }

    /**
     * Checks whether exception is thrown when config statement cardinality is
     * not as per grammar.
     */
    @Test
    public void processLeafConfigInvalidCardinality() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("YANG file error: \"config\" is defined more than once in \"leaf invalid-interval\".");
        YangNode node = manager.getDataModel("src/test/resources/LeafConfigInvalidCardinality.yang");
    }

    /**
     * Checks whether exception is thrown when mandatory statement cardinality
     * is not as per grammar.
     */
    @Test
    public void processLeafMandatoryInvalidCardinality() throws IOException, ParserException {
        thrown.expect(ParserException.class);
        thrown.expectMessage("YANG file error: \"mandatory\" is defined more than once in \"leaf invalid-interval\".");
        YangNode node = manager.getDataModel("src/test/resources/LeafMandatoryInvalidCardinality.yang");
    }

    /**
     * Checks leaf statement as sub-statement of container.
     */
    @Test
    public void processContainerSubStatementLeaf() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/ContainerSubStatementLeaf.yang");

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

        // Check whether leaf properties as set correctly.
        ListIterator<YangLeaf> leafIterator = container.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("uint16"));
        assertThat(leafInfo.getDataType().getDataType(), is(YangDataTypes.UINT16));
        assertThat(leafInfo.getUnits(), is("\"seconds\""));
        assertThat(leafInfo.getDescription(), is("\"Interval before a route is declared invalid\""));
        assertThat(leafInfo.isConfig(), is(true));
        assertThat(leafInfo.isMandatory(), is(true));
        assertThat(leafInfo.getStatus(), is(YangStatusType.CURRENT));
        assertThat(leafInfo.getReference(), is("\"RFC 6020\""));
    }

    /**
     * Checks duplicate leaf statement as sub-statement of module.
     */
    @Test(expected = ParserException.class)
    public void processModuleWithDuplicateLeaf() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/ModuleWithDuplicateLeaf.yang");
    }

    /**
     * Checks duplicate leaf statement as sub-statement of container.
     */
    @Test(expected = ParserException.class)
    public void processContainerWithDuplicateLeaf() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/ContainerWithDuplicateLeaf.yang");
    }

    /**
     * Checks duplicate leaf statement as sub-statement of list.
     */
    @Test(expected = ParserException.class)
    public void processListWithDuplicateLeaf() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/ListWithDuplicateLeaf.yang");
    }

    /**
     * Checks leaf statement as sub-statement of list.
     */
    @Test
    public void processListSubStatementLeaf() throws IOException, ParserException {

        YangNode node = manager.getDataModel("src/test/resources/ListSubStatementLeaf.yang");

        assertThat((node instanceof YangModule), is(true));

        // Check whether the node type is set properly to module.
        assertThat(node.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) node;
        assertThat(yangNode.getName(), is("Test"));

        // Check whether the list is child of module
        YangList yangList = (YangList) yangNode.getChild();
        assertThat(yangList.getName(), is("valid"));

        // Check whether leaf properties as set correctly.
        ListIterator<YangLeaf> leafIterator = yangList.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("uint16"));
        assertThat(leafInfo.getDataType().getDataType(), is(YangDataTypes.UINT16));
        assertThat(leafInfo.getUnits(), is("\"seconds\""));
        assertThat(leafInfo.getDescription(), is("\"Interval before a route is declared invalid\""));
        assertThat(leafInfo.isConfig(), is(true));
        assertThat(leafInfo.isMandatory(), is(true));
        assertThat(leafInfo.getStatus(), is(YangStatusType.CURRENT));
        assertThat(leafInfo.getReference(), is("\"RFC 6020\""));
    }
}
