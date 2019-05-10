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

package org.onosproject.yang.compiler.plugin.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.onosproject.yang.compiler.datamodel.YangAugment;
import org.onosproject.yang.compiler.datamodel.YangChoice;
import org.onosproject.yang.compiler.datamodel.YangContainer;
import org.onosproject.yang.compiler.datamodel.YangDerivedInfo;
import org.onosproject.yang.compiler.datamodel.YangGrouping;
import org.onosproject.yang.compiler.datamodel.YangLeaf;
import org.onosproject.yang.compiler.datamodel.YangList;
import org.onosproject.yang.compiler.datamodel.YangModule;
import org.onosproject.yang.compiler.datamodel.YangNode;
import org.onosproject.yang.compiler.datamodel.YangNodeType;
import org.onosproject.yang.compiler.datamodel.YangTypeDef;
import org.onosproject.yang.compiler.datamodel.YangUses;
import org.onosproject.yang.compiler.datamodel.utils.ResolvableStatus;
import org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes;
import org.onosproject.yang.compiler.linker.impl.YangLinkerManager;
import org.onosproject.yang.compiler.linker.impl.YangLinkerUtils;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;
import org.onosproject.yang.compiler.parser.impl.YangUtilsParserManager;
import org.onosproject.yang.compiler.tool.YangCompilerManager;
import org.onosproject.yang.compiler.utils.io.YangPluginConfig;
import org.onosproject.yang.compiler.utils.io.impl.YangIoUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.onosproject.yang.compiler.datamodel.YangNodeType.MODULE_NODE;
import static org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes.DERIVED;
import static org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes.STRING;
import static org.onosproject.yang.compiler.utils.io.impl.YangFileScanner.getYangFiles;

/**
 * Test cases for testing inter file linking.
 */
public class InterFileLinkingTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final YangUtilsParserManager manager = new YangUtilsParserManager();

    private final YangCompilerManager utilManager =
            new YangCompilerManager();
    private final YangLinkerManager yangLinkerManager = new YangLinkerManager();

    /**
     * Checks inter file type linking.
     */
    @Test
    public void processInterFileTypeLinking()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interfiletype";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();

        YangNode refNode = null;
        YangNode selfNode = null;

        // Create YANG node set
        yangLinkerManager.createYangNodeSet(utilManager.getYangNodeSet());

        // Add references to import list.
        yangLinkerManager.addRefToYangFilesImportList(utilManager.getYangNodeSet());

        YangLinkerUtils.updateFilePriority(utilManager.getYangNodeSet());

        // Carry out inter-file linking.
        yangLinkerManager.processInterFileLinking(utilManager.getYangNodeSet());

        Iterator<YangNode> yangNodeIterator = utilManager.getYangNodeSet().iterator();

        YangNode rootNode = yangNodeIterator.next();

        if (rootNode.getName().equals("module1")) {
            selfNode = rootNode;
            refNode = yangNodeIterator.next();
        } else {
            refNode = rootNode;
            selfNode = yangNodeIterator.next();
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("module1"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("hello"));
        assertThat(leafInfo.getDataType().getDataType(), is(DERIVED));

        assertThat(((YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo()).getReferredTypeDef(),
                   Is.is((YangTypeDef) refNode.getChild()));

        assertThat(leafInfo.getDataType().getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        YangDerivedInfo<?> derivedInfo = (YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo();

        // Check for the effective built-in type.
        assertThat(derivedInfo.getEffectiveBuiltInType(), is(STRING));

        // Check for the restriction.
        assertThat(derivedInfo.getLengthRes(), is(nullValue()));
        assertThat(derivedInfo.getRangeRes(), is(nullValue()));
        assertThat(derivedInfo.getPatternResList(), is(nullValue()));
        assertThat(derivedInfo.getResolvedExtendedInfo(), is(nullValue()));
    }

    /**
     * Checks inter file uses linking.
     */
    @Test
    public void processInterFileUsesLinking()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interfileuses";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();

        YangNode refNode = null;
        YangNode selfNode = null;

        // Create YANG node set
        yangLinkerManager.createYangNodeSet(utilManager.getYangNodeSet());

        // Add references to import list.
        yangLinkerManager.addRefToYangFilesImportList(utilManager.getYangNodeSet());

        YangLinkerUtils.updateFilePriority(utilManager.getYangNodeSet());

        // Carry out inter-file linking.
        yangLinkerManager.processInterFileLinking(utilManager.getYangNodeSet());

        Iterator<YangNode> yangNodeIterator = utilManager.getYangNodeSet().iterator();

        YangNode rootNode = yangNodeIterator.next();

        if (rootNode.getName().equals("module1")) {
            selfNode = rootNode;
            refNode = yangNodeIterator.next();
        } else {
            refNode = rootNode;
            selfNode = yangNodeIterator.next();
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("module1"));

        ListIterator<YangLeaf> leafIterator;
        YangLeaf leafInfo;

        // Check whether grouping is the sibling of module's child.
        assertThat(refNode.getChild() instanceof YangGrouping, is(true));

        YangGrouping grouping = (YangGrouping) refNode.getChild();
        leafIterator = grouping.getListOfLeaf().listIterator();
        leafInfo = leafIterator.next();

        // Check whether the information in the leaf is correct under grouping.
        assertThat(leafInfo.getName(), is("hello"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("string"));
        assertThat(leafInfo.getDataType().getDataType(), is(YangDataTypes.STRING));

        // Check whether uses is module's child.
        assertThat(yangNode.getChild() instanceof YangUses, is(true));
        YangUses uses = (YangUses) yangNode.getChild();

        // Check whether uses get resolved.
        assertThat(uses.getResolvableStatus(),
                   is(ResolvableStatus.RESOLVED));
    }

    /**
     * Checks inter file type linking with include list.
     */
    @Test
    public void processInterFileTypeLinkingWithIncludeList()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interfiletypewithinclude";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();

        YangNode refNode = null;
        YangNode selfNode = null;

        // Create YANG node set
        yangLinkerManager.createYangNodeSet(utilManager.getYangNodeSet());

        // Carry out linking of sub module with module.
        yangLinkerManager.linkSubModulesToParentModule(utilManager.getYangNodeSet());

        // Add reference to include list.
        yangLinkerManager.addRefToYangFilesIncludeList(utilManager.getYangNodeSet());

        YangLinkerUtils.updateFilePriority(utilManager.getYangNodeSet());

        // Carry out inter-file linking.
        yangLinkerManager.processInterFileLinking(utilManager.getYangNodeSet());

        Iterator<YangNode> yangNodeIterator = utilManager.getYangNodeSet().iterator();

        YangNode rootNode = yangNodeIterator.next();

        if (rootNode.getName().equals("module1")) {
            selfNode = rootNode;
            refNode = yangNodeIterator.next();
        } else {
            refNode = rootNode;
            selfNode = yangNodeIterator.next();
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("module1"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("hello"));
        assertThat(leafInfo.getDataType().getDataType(), is(DERIVED));

        assertThat(((YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo()).getReferredTypeDef(),
                   is((YangTypeDef) refNode.getChild()));

        assertThat(leafInfo.getDataType().getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        YangDerivedInfo<?> derivedInfo = (YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo();

        // Check for the effective built-in type.
        assertThat(derivedInfo.getEffectiveBuiltInType(), is(STRING));

        // Check for the restriction.
        assertThat(derivedInfo.getLengthRes(), is(nullValue()));
        assertThat(derivedInfo.getRangeRes(), is(nullValue()));
        assertThat(derivedInfo.getPatternResList(), is(nullValue()));
        assertThat(derivedInfo.getResolvedExtendedInfo(), is(nullValue()));
    }

    /**
     * Checks inter file uses linking with include list.
     */
    @Test
    public void processInterFileUsesLinkingWithInclude()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interfileuseswithinclude";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();

        YangNode refNode = null;
        YangNode selfNode = null;

        // Create YANG node set
        yangLinkerManager.createYangNodeSet(utilManager.getYangNodeSet());

        // Carry out linking of sub module with module.
        yangLinkerManager.linkSubModulesToParentModule(utilManager.getYangNodeSet());

        // Add reference to include list.
        yangLinkerManager.addRefToYangFilesIncludeList(utilManager.getYangNodeSet());

        YangLinkerUtils.updateFilePriority(utilManager.getYangNodeSet());

        // Carry out inter-file linking.
        yangLinkerManager.processInterFileLinking(utilManager.getYangNodeSet());

        Iterator<YangNode> yangNodeIterator = utilManager.getYangNodeSet().iterator();

        YangNode rootNode = yangNodeIterator.next();

        if (rootNode.getName().equals("module1")) {
            selfNode = rootNode;
            refNode = yangNodeIterator.next();
        } else {
            refNode = rootNode;
            selfNode = yangNodeIterator.next();
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(YangNodeType.MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("module1"));

        ListIterator<YangLeaf> leafIterator;
        YangLeaf leafInfo;

        // Check whether grouping is the sibling of module's child.
        assertThat(refNode.getChild() instanceof YangGrouping, is(true));

        YangGrouping grouping = (YangGrouping) refNode.getChild();
        leafIterator = grouping.getListOfLeaf().listIterator();
        leafInfo = leafIterator.next();

        // Check whether the information in the leaf is correct under grouping.
        assertThat(leafInfo.getName(), is("hello"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("string"));
        assertThat(leafInfo.getDataType().getDataType(), is(YangDataTypes.STRING));

        // Check whether uses is module's child.
        assertThat(yangNode.getChild() instanceof YangUses, is(true));
        YangUses uses = (YangUses) yangNode.getChild();

        // Check whether uses get resolved.
        assertThat(uses.getResolvableStatus(),
                   is(ResolvableStatus.RESOLVED));
    }

    /**
     * Checks inter file type linking with revision.
     */
    @Test
    public void processInterFileTypeLinkingWithRevision()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interfiletypewithrevision";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();

        YangNode refNode = null;
        YangNode selfNode = null;

        // Create YANG node set
        yangLinkerManager.createYangNodeSet(utilManager.getYangNodeSet());

        // Add references to import list.
        yangLinkerManager.addRefToYangFilesImportList(utilManager.getYangNodeSet());

        YangLinkerUtils.updateFilePriority(utilManager.getYangNodeSet());

        // Carry out inter-file linking.
        yangLinkerManager.processInterFileLinking(utilManager.getYangNodeSet());

        Iterator<YangNode> yangNodeIterator = utilManager.getYangNodeSet().iterator();

        YangNode rootNode = yangNodeIterator.next();

        if (rootNode.getName().equals("module1")) {
            selfNode = rootNode;
            refNode = yangNodeIterator.next();
        } else {
            refNode = rootNode;
            selfNode = yangNodeIterator.next();
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("module1"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("hello"));
        assertThat(leafInfo.getDataType().getDataType(), is(DERIVED));

        assertThat(((YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo()).getReferredTypeDef(),
                   is((YangTypeDef) refNode.getChild()));

        assertThat(leafInfo.getDataType().getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        YangDerivedInfo<?> derivedInfo = (YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo();

        // Check for the effective built-in type.
        assertThat(derivedInfo.getEffectiveBuiltInType(), is(STRING));

        // Check for the restriction.
        assertThat(derivedInfo.getLengthRes(), is(nullValue()));
        assertThat(derivedInfo.getRangeRes(), is(nullValue()));
        assertThat(derivedInfo.getPatternResList(), is(nullValue()));
        assertThat(derivedInfo.getResolvedExtendedInfo(), is(nullValue()));
    }

    /**
     * Checks inter file type linking with revision in name.
     */
    @Test
    public void processInterFileTypeLinkingWithRevisionInName()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interfiletypewithrevisioninname";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();

        YangNode refNode = null;
        YangNode selfNode = null;

        // Create YANG node set
        yangLinkerManager.createYangNodeSet(utilManager.getYangNodeSet());

        // Add references to import list.
        yangLinkerManager.addRefToYangFilesImportList(utilManager.getYangNodeSet());

        YangLinkerUtils.updateFilePriority(utilManager.getYangNodeSet());

        // Carry out inter-file linking.
        yangLinkerManager.processInterFileLinking(utilManager.getYangNodeSet());

        Iterator<YangNode> yangNodeIterator = utilManager.getYangNodeSet().iterator();

        YangNode rootNode = yangNodeIterator.next();

        if (rootNode.getName().equals("module1")) {
            selfNode = rootNode;
            refNode = yangNodeIterator.next();
        } else {
            refNode = rootNode;
            selfNode = yangNodeIterator.next();
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("module1"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("invalid-interval"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("hello"));
        assertThat(leafInfo.getDataType().getDataType(), is(DERIVED));

        assertThat(((YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo()).getReferredTypeDef(),
                   is((YangTypeDef) refNode.getChild()));

        assertThat(leafInfo.getDataType().getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        YangDerivedInfo<?> derivedInfo = (YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo();

        // Check for the effective built-in type.
        assertThat(derivedInfo.getEffectiveBuiltInType(), is(STRING));

        // Check for the restriction.
        assertThat(derivedInfo.getLengthRes(), is(nullValue()));
        assertThat(derivedInfo.getRangeRes(), is(nullValue()));
        assertThat(derivedInfo.getPatternResList(), is(nullValue()));
        assertThat(derivedInfo.getResolvedExtendedInfo(), is(nullValue()));
    }

    /**
     * Checks hierarchical inter file type linking.
     */
    @Test
    public void processHierarchicalInterFileTypeLinking()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/hierarchicalinterfiletype";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();

        YangNode refNode1 = null;
        YangNode refNode2 = null;
        YangNode selfNode = null;

        // Create YANG node set
        yangLinkerManager.createYangNodeSet(utilManager.getYangNodeSet());

        // Add references to import list.
        yangLinkerManager.addRefToYangFilesImportList(utilManager.getYangNodeSet());

        YangLinkerUtils.updateFilePriority(utilManager.getYangNodeSet());

        // Carry out inter-file linking.
        yangLinkerManager.processInterFileLinking(utilManager.getYangNodeSet());

        for (YangNode rootNode : utilManager.getYangNodeSet()) {
            if (rootNode.getName().equals("ietf-network-topology")) {
                selfNode = rootNode;
            } else if (rootNode.getName().equals("ietf-network")) {
                refNode1 = rootNode;
            } else {
                refNode2 = rootNode;
            }
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("ietf-network-topology"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("source-node"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("node-id"));
        assertThat(leafInfo.getDataType().getDataType(), is(DERIVED));

        assertThat(((YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo()).getReferredTypeDef(),
                   is((YangTypeDef) refNode1.getChild()));

        assertThat(leafInfo.getDataType().getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        YangDerivedInfo<?> derivedInfo = (YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo();

        // Check for the effective built-in type.
        assertThat(derivedInfo.getEffectiveBuiltInType(), is(STRING));

        // Check for the restriction.
        assertThat(derivedInfo.getLengthRes(), is(nullValue()));
        assertThat(derivedInfo.getRangeRes(), is(nullValue()));
        assertThat(derivedInfo.getPatternResList(), is(nullValue()));
        assertThat(derivedInfo.getResolvedExtendedInfo(), is(nullValue()));
    }

    /**
     * Checks hierarchical intra with inter file type linking.
     */
    @Test
    public void processHierarchicalIntraWithInterFileTypeLinking()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/hierarchicalintrawithinterfiletype";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();

        YangNode refNode1 = null;
        YangNode selfNode = null;

        // Create YANG node set
        yangLinkerManager.createYangNodeSet(utilManager.getYangNodeSet());

        // Add references to import list.
        yangLinkerManager.addRefToYangFilesImportList(utilManager.getYangNodeSet());

        YangLinkerUtils.updateFilePriority(utilManager.getYangNodeSet());

        // Carry out inter-file linking.
        yangLinkerManager.processInterFileLinking(utilManager.getYangNodeSet());

        for (YangNode rootNode : utilManager.getYangNodeSet()) {
            if (rootNode.getName().equals("ietf-network")) {
                selfNode = rootNode;
            } else if (rootNode.getName().equals("ietf-inet-types")) {
                refNode1 = rootNode;
            }
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("ietf-network"));

        ListIterator<YangLeaf> leafIterator = yangNode.getListOfLeaf().listIterator();
        YangLeaf leafInfo = leafIterator.next();

        assertThat(leafInfo.getName(), is("node-ref"));
        assertThat(leafInfo.getDataType().getDataTypeName(), is("node-id"));
        assertThat(leafInfo.getDataType().getDataType(), is(DERIVED));

        assertThat(((YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo()).getReferredTypeDef(),
                   is((YangTypeDef) selfNode.getChild()));

        assertThat(leafInfo.getDataType().getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        YangDerivedInfo<?> derivedInfo = (YangDerivedInfo<?>) leafInfo.getDataType().getDataTypeExtendedInfo();

        // Check for the effective built-in type.
        assertThat(derivedInfo.getEffectiveBuiltInType(), is(STRING));

        // Check for the restriction.
        assertThat(derivedInfo.getLengthRes(), is(nullValue()));
        assertThat(derivedInfo.getRangeRes(), is(nullValue()));
        assertThat(derivedInfo.getPatternResList(), is(nullValue()));
        assertThat(derivedInfo.getResolvedExtendedInfo(), is(nullValue()));
    }

    /**
     * Checks hierarchical intra with inter file type linking.
     */
    @Test
    public void interFileWithUsesReferringType()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interfilewithusesreferringtype";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.resolveDependenciesUsingLinker();

        YangPluginConfig yangPluginConfig = new YangPluginConfig();
        yangPluginConfig.setCodeGenDir("target/interfilewithusesreferringtype/");

        utilManager.translateToJava(yangPluginConfig);

        YangIoUtils.deleteDirectory("target/interfilewithusesreferringtype/");
    }

    /**
     * Checks hierarchical intra with inter file type linking.
     */
    @Test
    public void file1UsesFile2TypeDefFile3Type()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/file1UsesFile2TypeDefFile3Type";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();
        utilManager.resolveDependenciesUsingLinker();

        YangPluginConfig yangPluginConfig = new YangPluginConfig();
        yangPluginConfig.setCodeGenDir("target/file1UsesFile2TypeDefFile3Type/");

        utilManager.translateToJava(yangPluginConfig);
        String dir1 = System.getProperty("user.dir") + "/"
                + "target/file1UsesFile2TypeDefFile3Type/";
        YangPluginConfig.compileCode(dir1);
        YangIoUtils.deleteDirectory("target/file1UsesFile2TypeDefFile3Type/");
    }

    /**
     * Checks hierarchical intra with inter file type linking.
     */
    @Test
    public void interFileIetf()
            throws IOException, ParserException, MojoExecutionException {

        YangIoUtils.deleteDirectory("target/interfileietf/");
        String searchDir = "src/test/resources/interfileietf";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();
        utilManager.resolveDependenciesUsingLinker();

        YangPluginConfig yangPluginConfig = new YangPluginConfig();
        yangPluginConfig.setCodeGenDir("target/interfileietf/");

        utilManager.translateToJava(yangPluginConfig);

        YangIoUtils.deleteDirectory("target/interfileietf/");
    }

    /**
     * Checks hierarchical intra with inter file type linking.
     */
    @Test
    public void usesInContainer()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/usesInContainer";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();
        utilManager.resolveDependenciesUsingLinker();

        YangPluginConfig yangPluginConfig = new YangPluginConfig();
        yangPluginConfig.setCodeGenDir("target/usesInContainer/");

        utilManager.translateToJava(yangPluginConfig);

        YangIoUtils.deleteDirectory("target/usesInContainer/");
    }

    /**
     * Checks hierarchical intra with inter file type linking.
     */
    @Test
    public void groupingNodeSameAsModule()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/groupingNodeSameAsModule";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();
        utilManager.resolveDependenciesUsingLinker();

        YangPluginConfig yangPluginConfig = new YangPluginConfig();
        yangPluginConfig.setCodeGenDir("target/groupingNodeSameAsModule/");

        utilManager.translateToJava(yangPluginConfig);
        String dir1 = System.getProperty("user.dir") + "/"
                + "target/groupingNodeSameAsModule/";
        YangPluginConfig.compileCode(dir1);
        YangIoUtils.deleteDirectory("target/groupingNodeSameAsModule/");
    }

    /**
     * Checks priority of the file.
     */
    @Test
    public void interFilePriority()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interfilepriority";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();
        utilManager.resolveDependenciesUsingLinker();

        YangNode selfNode = null;
        YangNode refNode1 = null;
        YangNode refNode2 = null;

        for (YangNode rootNode : utilManager.getYangNodeSet()) {
            if (rootNode.getName().equals("module1")) {
                selfNode = rootNode;
            } else if (rootNode.getName().equals("module2")) {
                refNode1 = rootNode;
            } else {
                refNode2 = rootNode;
            }
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));

        // Check whether the module name is set correctly.
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("module1"));
        assertThat(yangNode.getPriority(), is(2));

        // Check whether the data model tree returned is of type module.
        assertThat(refNode1 instanceof YangModule, is(true));

        // Check whether the node type is set properly to module.
        assertThat(refNode1.getNodeType(), is(MODULE_NODE));

        YangModule referredNode1 = (YangModule) refNode1;
        assertThat(referredNode1.getName(), is("module2"));
        assertThat(referredNode1.getPriority(), is(3));
    }

    /**
     * Checks contents of uses are copied as child of grouping.
     */
    @Test
    public void usesInsideChildOfGrouping()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/usesInsideChildOfGrouping";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();
        utilManager.resolveDependenciesUsingLinker();

        YangNode selfNode = null;
        YangNode refNode1 = null;

        for (YangNode rootNode : utilManager.getYangNodeSet()) {
            if (rootNode.getName().equals("ietf-network")) {
                selfNode = rootNode;
            } else if (rootNode.getName().equals("ietf-te-topology")) {
                refNode1 = rootNode;
            }
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("ietf-network"));

        YangModule refNode = (YangModule) refNode1;
        assertThat(refNode.getName(), is("ietf-te-topology"));

        YangAugment augment = ((YangAugment) refNode.getChild().getNextSibling().
                getNextSibling().getNextSibling().getNextSibling());
        assertThat(augment.getName(), is("/nw:networks/nw:network/nw:node"));

        YangUses uses = ((YangUses) augment.getChild());
        YangContainer container = ((YangContainer) uses.getNextSibling());
        assertThat(container.getName(), is("te"));

        container = ((YangContainer) container.getChild());
        assertThat(container.getName(), is("config"));

        uses = ((YangUses) container.getChild().getNextSibling());
        assertThat(uses.getName(), is("te-node-config-attributes"));

        YangContainer container1 = ((YangContainer) uses.getNextSibling());
        assertThat(container1.getName(), is("te-node-attributes"));

        uses = ((YangUses) container1.getChild());
        assertThat(uses.getName(), is("te-node-connectivity-matrix"));

        YangList list = ((YangList) uses.getNextSibling());
        assertThat(list.getName(), is("connectivity-matrix"));
    }

    /**
     * Checks contents of uses are copied as child of grouping.
     */
    @Test
    public void interFileUsesInsideChildOfGrouping()
            throws IOException, ParserException, MojoExecutionException {

        String searchDir = "src/test/resources/interFileUsesInsideChildOfGrouping";

        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();
        utilManager.resolveDependenciesUsingLinker();

        YangNode selfNode = null;
        YangNode refNode1 = null;

        for (YangNode rootNode : utilManager.getYangNodeSet()) {
            if (rootNode.getName().equals("ietf-network")) {
                selfNode = rootNode;
            } else if (rootNode.getName().equals("ietf-te-topology")) {
                refNode1 = rootNode;
            }
        }

        // Check whether the data model tree returned is of type module.
        assertThat(selfNode instanceof YangModule, is(true));
        assertThat(selfNode.getNodeType(), is(MODULE_NODE));
        YangModule yangNode = (YangModule) selfNode;
        assertThat(yangNode.getName(), is("ietf-network"));

        YangModule refNode = (YangModule) refNode1;
        assertThat(refNode.getName(), is("ietf-te-topology"));

        YangAugment augment = ((YangAugment) refNode.getChild().getNextSibling().
                getNextSibling().getNextSibling().getNextSibling().getNextSibling());
        assertThat(augment.getName(), is("/nw:networks/nw:network/nt:link"));

        YangUses uses = ((YangUses) augment.getChild());
        assertThat(uses.getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));
        YangContainer container = ((YangContainer) uses.getNextSibling());
        assertThat(container.getName(), is("te"));

        container = ((YangContainer) container.getChild());
        assertThat(container.getName(), is("config"));

        uses = ((YangUses) container.getChild().getNextSibling());
        assertThat(uses.getName(), is("te-link-config-attributes"));
        assertThat(uses.getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        YangContainer container1 = ((YangContainer) uses.getNextSibling());
        assertThat(container1.getName(), is("te-link-attributes"));

        container = ((YangContainer) container1.getChild());
        assertThat(container.getName(), is("underlay"));

        uses = ((YangUses) container.getChild());
        assertThat(uses.getName(), is("te-link-underlay-attributes"));
        assertThat(uses.getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        container = ((YangContainer) uses.getNextSibling());
        assertThat(container.getName(), is("underlay-primary-path"));

        YangList yangList = ((YangList) container.getChild());
        assertThat(yangList.getName(), is("path-element"));

        uses = ((YangUses) yangList.getChild());
        assertThat(uses.getName(), is("te-path-element"));
        assertThat(uses.getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        uses = ((YangUses) uses.getNextSibling());
        assertThat(uses.getName(), is("explicit-route-subobject"));
        assertThat(uses.getResolvableStatus(), Is.is(ResolvableStatus.RESOLVED));

        YangChoice choice = ((YangChoice) uses.getNextSibling());
        assertThat(choice.getName(), is("type"));
    }
}
