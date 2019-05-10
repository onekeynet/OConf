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

package org.onosproject.yang.compiler.datamodel;

import org.onosproject.yang.compiler.datamodel.exceptions.DataModelException;
import org.onosproject.yang.compiler.datamodel.utils.Parsable;
import org.onosproject.yang.compiler.datamodel.utils.YangConstructType;
import org.onosproject.yang.model.DataNode;
import org.onosproject.yang.model.SchemaContext;
import org.onosproject.yang.model.SchemaId;
import org.onosproject.yang.model.SingleInstanceNodeContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.E_ID;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.FMT_NOT_EXIST;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.detectCollidingChildUtil;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.errorMsg;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.getNodeIdFromSchemaId;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.getParentSchemaContext;

/*-
 * Reference RFC 6020.
 *
 * The "container" statement is used to define an interior data node in the
 * schema tree. It takes one argument, which is an identifier, followed by a
 * block of sub-statements that holds detailed container information.
 *
 * A container node does not have a value, but it has a list of child nodes in
 * the data tree. The child nodes are defined in the container's sub-statements.
 *
 * Containers with Presence
 *
 * YANG supports two styles of containers, those that exist only for organizing
 * the hierarchy of data nodes, and those whose presence in the configuration
 * has an explicit meaning.
 *
 * In the first style, the container has no meaning of its own, existing only to
 * contain child nodes. This is the default style.
 *
 * For example, the set of scrambling options for Synchronous Optical Network
 * (SONET) interfaces may be placed inside a "scrambling" container to enhance
 * the organization of the configuration hierarchy, and to keep these nodes
 * together. The "scrambling" node itself has no meaning, so removing the node
 * when it becomes empty relieves the user from performing this task.
 *
 * In the second style, the presence of the container itself is configuration
 * data, representing a single bit of configuration data. The container acts as
 * both a configuration knob and a means of organizing related configuration.
 * These containers are explicitly created and deleted.
 *
 * YANG calls this style a "presence container" and it is indicated using the
 * "presence" statement, which takes as its argument a text string indicating
 * what the presence of the node means.
 *
 * The container's Substatements
 *
 *                +--------------+---------+-------------+------------------+
 *                | substatement | section | cardinality |data model mapping|
 *                +--------------+---------+-------------+------------------+
 *                | anyxml       | 7.10    | 0..n        | -not supported   |
 *                | choice       | 7.9     | 0..n        | -child nodes     |
 *                | config       | 7.19.1  | 0..1        | -boolean         |
 *                | container    | 7.5     | 0..n        | -child nodes     |
 *                | description  | 7.19.3  | 0..1        | -string          |
 *                | grouping     | 7.11    | 0..n        | -child nodes     |
 *                | if-feature   | 7.18.2  | 0..n        | -YangIfFeature   |
 *                | leaf         | 7.6     | 0..n        | -YangLeaf        |
 *                | leaf-list    | 7.7     | 0..n        | -YangLeafList    |
 *                | list         | 7.8     | 0..n        | -child nodes     |
 *                | must         | 7.5.3   | 0..n        | -YangMust        |
 *                | presence     | 7.5.5   | 0..1        | -boolean         |
 *                | reference    | 7.19.4  | 0..1        | -string          |
 *                | status       | 7.19.2  | 0..1        | -YangStatus      |
 *                | typedef      | 7.3     | 0..n        | -child nodes     |
 *                | uses         | 7.12    | 0..n        | -child nodes     |
 *                | when         | 7.19.5  | 0..1        | -YangWhen        |
 *                +--------------+---------+-------------+------------------+
 */

/**
 * Represents data model node to maintain information defined in YANG container.
 */
public abstract class YangContainer
        extends YangNode
        implements YangLeavesHolder, YangCommonInfo, Parsable, CollisionDetector,
        YangAugmentableNode, YangMustHolder, YangWhenHolder, YangIfFeatureHolder, YangIsFilterContentNodes,
        YangConfig, SingleInstanceNodeContext, SchemaDataNode,
        DefaultDenyWriteExtension, DefaultDenyAllExtension {

    private static final long serialVersionUID = -4962764560362228905L;

    /**
     * If container maintains config data.
     */
    private boolean isConfig;

    /**
     * Description of container.
     */
    private String description;

    /**
     * List of leaves contained.
     */
    private List<YangLeaf> listOfLeaf;

    /**
     * List of leaf-lists contained.
     */
    private List<YangLeafList> listOfLeafList;

    /**
     * If it is a presence container, then the textual documentation of presence
     * usage.
     */
    private String presence;

    /**
     * Reference of the module.
     */
    private String reference;

    private List<YangAugment> yangAugmentedInfo = new ArrayList<>();

    /**
     * Status of the node.
     */
    private YangStatusType status = YangStatusType.CURRENT;

    /**
     * List of must statement constraints.
     */
    private List<YangMust> mustConstraintList;

    /**
     * When data of the node.
     */
    private YangWhen when;

    /**
     * List of if-feature.
     */
    private List<YangIfFeature> ifFeatureList;

    /**
     * References the extension default-deny-write.
     */
    private boolean defaultDenyWrite;

    /**
     * References the extension default-deny-all.
     */
    private boolean defaultDenyAll;

    /**
     * Create a container node.
     */
    public YangContainer() {
        super(YangNodeType.CONTAINER_NODE, new HashMap<>(),
              DataNode.Type.SINGLE_INSTANCE_NODE);
        listOfLeaf = new LinkedList<>();
        listOfLeafList = new LinkedList<>();
        mustConstraintList = new LinkedList<>();
        ifFeatureList = new LinkedList<>();
    }

    @Override
    public void addToChildSchemaMap(YangSchemaNodeIdentifier schemaNodeIdentifier,
                                    YangSchemaNodeContextInfo yangSchemaNodeContextInfo) {
        getYsnContextInfoMap().put(schemaNodeIdentifier, yangSchemaNodeContextInfo);
    }

    @Override
    public void incrementMandatoryChildCount() {
        // TODO
    }

    @Override
    public void addToDefaultChildMap(YangSchemaNodeIdentifier yangSchemaNodeIdentifier, YangSchemaNode yangSchemaNode) {
        // TODO
    }

    @Override
    public YangSchemaNodeType getYangSchemaNodeType() {
        return YangSchemaNodeType.YANG_SINGLE_INSTANCE_NODE;
    }

    /**
     * Returns the when.
     *
     * @return the when
     */
    @Override
    public YangWhen getWhen() {
        return when;
    }

    /**
     * Sets the when.
     *
     * @param when the when to set
     */
    @Override
    public void setWhen(YangWhen when) {
        this.when = when;
    }

    /**
     * Returns the config flag.
     *
     * @return the isConfig
     */
    @Override
    public boolean isConfig() {
        return isConfig;
    }

    /**
     * Sets the config flag.
     *
     * @param isConfig the config flag
     */
    @Override
    public void setConfig(boolean isConfig) {
        this.isConfig = isConfig;
    }

    /**
     * Returns the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description set the description
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the list of leaves.
     *
     * @return the list of leaves
     */
    @Override
    public List<YangLeaf> getListOfLeaf() {
        return listOfLeaf;
    }

    /**
     * Sets the list of leaves.
     *
     * @param leafsList the list of leaf to set
     */
    @Override
    public void setListOfLeaf(List<YangLeaf> leafsList) {
        listOfLeaf = leafsList;
    }

    /**
     * Adds a leaf.
     *
     * @param leaf the leaf to be added
     */
    @Override
    public void addLeaf(YangLeaf leaf) {
        listOfLeaf.add(leaf);
    }

    /**
     * Removes a leaf.
     *
     * @param leaf the leaf to be removed
     */
    @Override
    public void removeLeaf(YangLeaf leaf) {
        listOfLeaf.remove(leaf);
    }

    /**
     * Returns the list of leaf-list.
     *
     * @return the list of leaf-list
     */
    @Override
    public List<YangLeafList> getListOfLeafList() {
        return listOfLeafList;
    }

    /**
     * Sets the list of leaf-list.
     *
     * @param listOfLeafList the list of leaf-list to set
     */
    @Override
    public void setListOfLeafList(List<YangLeafList> listOfLeafList) {
        this.listOfLeafList = listOfLeafList;
    }

    /**
     * Adds a leaf-list.
     *
     * @param leafList the leaf-list to be added
     */
    @Override
    public void addLeafList(YangLeafList leafList) {
        listOfLeafList.add(leafList);
    }

    /**
     * Removes a leaf-list.
     *
     * @param leafList the leaf-list to be removed
     */
    @Override
    public void removeLeafList(YangLeafList leafList) {
        listOfLeafList.remove(leafList);
    }

    /**
     * Returns the presence string if present.
     *
     * @return the presence
     */
    public String getPresence() {
        return presence;
    }

    /**
     * Sets the presence string.
     *
     * @param presence the presence flag
     */
    public void setPresence(String presence) {
        this.presence = presence;
    }

    /**
     * Returns the textual reference.
     *
     * @return the reference
     */
    @Override
    public String getReference() {
        return reference;
    }

    /**
     * Sets the textual reference.
     *
     * @param reference the reference to set
     */
    @Override
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Returns the status.
     *
     * @return the status
     */
    @Override
    public YangStatusType getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set
     */
    @Override
    public void setStatus(YangStatusType status) {
        this.status = status;
    }

    @Override
    public boolean getDefaultDenyWrite() {
        return defaultDenyWrite;
    }

    @Override
    public void setDefaultDenyWrite(boolean defaultDenyWrite) {
        this.defaultDenyWrite = defaultDenyWrite;
    }

    @Override
    public boolean getDefaultDenyAll() {
        return defaultDenyAll;
    }

    @Override
    public void setDefaultDenyAll(boolean defaultDenyAll) {
        this.defaultDenyAll = defaultDenyAll;
    }

    /**
     * Returns the type of the data.
     *
     * @return returns CONTAINER_DATA
     */
    @Override
    public YangConstructType getYangConstructType() {
        return YangConstructType.CONTAINER_DATA;
    }

    /**
     * Validates the data on entering the corresponding parse tree node.
     *
     * @throws DataModelException a violation of data model rules
     */
    @Override
    public void validateDataOnEntry()
            throws DataModelException {
        // TODO auto-generated method stub, to be implemented by parser
    }

    /**
     * Validates the data on exiting the corresponding parse tree node.
     *
     * @throws DataModelException a violation of data model rules
     */
    @Override
    public void validateDataOnExit()
            throws DataModelException {
        List<YangLeaf> leaves = getListOfLeaf();
        List<YangLeafList> leafLists = getListOfLeafList();
        validateConfig(leaves, leafLists);
    }

    /**
     * Validates config statement of container.
     *
     * @param leaves    list of leaf attributes of container
     * @param leafLists list of leaf-list attributes of container
     * @throws DataModelException a violation of data model rules
     */
    private void validateConfig(List<YangLeaf> leaves,
                                List<YangLeafList> leafLists)
            throws DataModelException {

        /*
         * If a node has "config" set to "false", no node underneath it can have
         * "config" set to "true".
         */
        if (!isConfig && leaves != null) {
            for (YangLeaf leaf : leaves) {
                if (leaf.isConfig()) {
                    throw new DataModelException(
                            "If a container has \"config\" set to \"false\", " +
                                    "no node underneath it can have " +
                                    "\"config\" set to \"true\"." + getName() +
                                    " in " + getLineNumber() + " at " +
                                    getCharPosition() +
                                    " in " + getFileName() + "\"");
                }
            }
        }

        if (!isConfig && leafLists != null) {
            for (YangLeafList leafList : leafLists) {
                if (leafList.isConfig()) {
                    throw new DataModelException(
                            "If a container has \"config\" set to \"false\", " +
                                    "no node underneath it can have " +
                                    "\"config\" set to \"true\"." +
                                    getName() + " in " +
                                    getLineNumber() + " at " +
                                    getCharPosition() +
                                    " in " + getFileName() + "\"");
                }
            }
        }
    }

    @Override
    public void detectCollidingChild(String identifierName,
                                     YangConstructType dataType)
            throws DataModelException {
        // Asks helper to detect colliding child.
        detectCollidingChildUtil(identifierName, dataType, this);
    }

    @Override
    public void detectSelfCollision(String identifierName,
                                    YangConstructType dataType)
            throws DataModelException {
        if (getName().equals(identifierName)) {
            throw new DataModelException(
                    "YANG file error: Duplicate input identifier detected," +
                            " same as container \"" +
                            getName() + " in " +
                            getLineNumber() + " at " +
                            getCharPosition() +
                            " in " + getFileName() + "\"");
        }
    }

    @Override
    public List<YangIfFeature> getIfFeatureList() {
        return ifFeatureList;
    }

    @Override
    public void addIfFeatureList(YangIfFeature ifFeature) {
        if (getIfFeatureList() == null) {
            setIfFeatureList(new LinkedList<>());
        }
        getIfFeatureList().add(ifFeature);
    }

    @Override
    public void setIfFeatureList(List<YangIfFeature> ifFeatureList) {
        this.ifFeatureList = ifFeatureList;
    }

    @Override
    public List<YangMust> getListOfMust() {
        return mustConstraintList;
    }

    @Override
    public void setListOfMust(List<YangMust> mustConstraintList) {
        this.mustConstraintList = mustConstraintList;
    }

    @Override
    public void addMust(YangMust must) {
        if (getListOfMust() == null) {
            setListOfMust(new LinkedList<>());
        }
        getListOfMust().add(must);
    }

    @Override
    public void addAugmentation(YangAugment augmentInfo) {
        yangAugmentedInfo.add(augmentInfo);
    }

    @Override
    public void removeAugmentation(YangAugment augmentInfo) {
        yangAugmentedInfo.remove(augmentInfo);
    }

    @Override
    public List<YangAugment> getAugmentedInfoList() {
        return yangAugmentedInfo;
    }

    @Override
    public void setLeafNameSpaceAndAddToParentSchemaMap() {
        // Add namespace for all leafs.
        for (YangLeaf yangLeaf : getListOfLeaf()) {
            yangLeaf.setLeafNameSpaceAndAddToParentSchemaMap(getNameSpace());
        }
        // Add namespace for all leaf list.
        for (YangLeafList yangLeafList : getListOfLeafList()) {
            yangLeafList.setLeafNameSpaceAndAddToParentSchemaMap(getNameSpace());
        }
    }

    @Override
    public void setLeafParentContext() {
        // Add parent context for all leafs.
        for (YangLeaf yangLeaf : getListOfLeaf()) {
            yangLeaf.setParentContext(getParentSchemaContext(this));
        }
        // Add parent context for all leaf list.
        for (YangLeafList yangLeafList : getListOfLeafList()) {
            yangLeafList.setParentContext(getParentSchemaContext(this));
        }
    }

    public void cloneAugmentInfo() {
        yangAugmentedInfo = new ArrayList<>();
    }

    @Override
    public SchemaContext getChildContext(SchemaId schemaId) {

        checkNotNull(schemaId, E_ID);
        YangSchemaNodeIdentifier id = getNodeIdFromSchemaId(
                schemaId, getNameSpace().getModuleNamespace());
        try {
            YangSchemaNode node = getChildSchema(id).getSchemaNode();
            if (node instanceof SchemaDataNode) {
                return node;
            } else {
                throw new IllegalArgumentException(errorMsg(FMT_NOT_EXIST,
                                                            schemaId.name(),
                                                            getName()));
            }
        } catch (DataModelException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
