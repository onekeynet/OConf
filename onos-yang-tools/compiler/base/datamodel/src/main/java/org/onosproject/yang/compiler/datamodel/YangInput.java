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
import static java.util.Collections.unmodifiableList;
import static org.onosproject.yang.compiler.datamodel.YangNodeType.INPUT_NODE;
import static org.onosproject.yang.compiler.datamodel.YangSchemaNodeType.YANG_SINGLE_INSTANCE_NODE;
import static org.onosproject.yang.compiler.datamodel.exceptions.ErrorMessages.COLLISION_DETECTION;
import static org.onosproject.yang.compiler.datamodel.exceptions.ErrorMessages.INPUT;
import static org.onosproject.yang.compiler.datamodel.exceptions.ErrorMessages.getErrorMsgCollision;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.E_ID;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.FMT_NOT_EXIST;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.detectCollidingChildUtil;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.errorMsg;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.getNodeIdFromSchemaId;
import static org.onosproject.yang.compiler.datamodel.utils.DataModelUtils.getParentSchemaContext;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.INPUT_DATA;

/*
 * Reference RFC 6020.
 *
 * The "input" statement, which is optional, is used to define input
 * parameters to the RPC operation.  It does not take an argument.  The
 * substatements to "input" define nodes under the RPC's input node.
 *
 * If a leaf in the input tree has a "mandatory" statement with the
 * value "true", the leaf MUST be present in a NETCONF RPC invocation.
 * Otherwise, the server MUST return a "missing-element" error.
 *
 * If a leaf in the input tree has a default value, the NETCONF server
 * MUST use this value in the same cases as described in Section 7.6.1.
 * In these cases, the server MUST operationally behave as if the leaf
 * was present in the NETCONF RPC invocation with the default value as
 * its value.
 *
 * If a "config" statement is present for any node in the input tree,
 * the "config" statement is ignored.
 *
 * If any node has a "when" statement that would evaluate to false, then
 * this node MUST NOT be present in the input tree.
 *
 * The input substatements
 *
 *     +--------------+---------+-------------+------------------+
 *     | substatement | section | cardinality |data model mapping|
 *     +--------------+---------+-------------+------------------+
 *     | anyxml       | 7.10    | 0..n        | -not supported   |
 *     | choice       | 7.9     | 0..n        | -child nodes     |
 *     | container    | 7.5     | 0..n        | -child nodes     |
 *     | grouping     | 7.11    | 0..n        | -child nodes     |
 *     | leaf         | 7.6     | 0..n        | -YangLeaf        |
 *     | leaf-list    | 7.7     | 0..n        | -YangLeafList    |
 *     | list         | 7.8     | 0..n        | -child nodes     |
 *     | typedef      | 7.3     | 0..n        | -child nodes     |
 *     | uses         | 7.12    | 0..n        | -child nodes     |
 *     +--------------+---------+-------------+------------------+
 */

/**
 * Represents data model node to maintain information defined in YANG input.
 */
public abstract class YangInput
        extends YangNode
        implements YangLeavesHolder, Parsable, CollisionDetector,
        YangAugmentableNode, YangIsFilterContentNodes, InvalidOpTypeHolder,
        SchemaDataNode, SingleInstanceNodeContext {

    private static final long serialVersionUID = 806201608L;

    /**
     * List of leaves contained.
     */
    private List<YangLeaf> listOfLeaf;

    /**
     * List of leaf-lists contained.
     */
    private List<YangLeafList> listOfLeafList;

    private List<YangAugment> yangAugmentedInfo;

    /**
     * Create a rpc input node.
     */
    public YangInput() {
        super(INPUT_NODE, new HashMap<>(), DataNode.Type.SINGLE_INSTANCE_NODE);
        listOfLeaf = new LinkedList<>();
        listOfLeafList = new LinkedList<>();
        yangAugmentedInfo = new ArrayList<>();
    }

    @Override
    public void addToChildSchemaMap(YangSchemaNodeIdentifier id,
                                    YangSchemaNodeContextInfo context)
            throws DataModelException {
        getYsnContextInfoMap().put(id, context);
    }

    @Override
    public void incrementMandatoryChildCount() {
        //For non data nodes, mandatory child to be added to parent node.
        // TODO
    }

    @Override
    public void addToDefaultChildMap(YangSchemaNodeIdentifier id,
                                     YangSchemaNode yangSchemaNode) {
        //For non data nodes, default child to be added to parent node.
        // TODO
    }

    @Override
    public YangSchemaNodeType getYangSchemaNodeType() {
        return YANG_SINGLE_INSTANCE_NODE;
    }

    @Override
    public void detectCollidingChild(String identifierName,
                                     YangConstructType dataType)
            throws DataModelException {
        // Detect colliding child.
        detectCollidingChildUtil(identifierName, dataType, this);
    }

    @Override
    public void detectSelfCollision(String identifierName,
                                    YangConstructType dataType)
            throws DataModelException {
        if (getName().equals(identifierName)) {
            throw new DataModelException(
                    getErrorMsgCollision(COLLISION_DETECTION, getName(),
                                         getLineNumber(), getCharPosition(),
                                         INPUT, getFileName()));
        }
    }

    @Override
    public YangConstructType getYangConstructType() {
        return INPUT_DATA;
    }

    @Override
    public void validateDataOnEntry()
            throws DataModelException {
        //TODO: implement the method.
    }

    @Override
    public void validateDataOnExit()
            throws DataModelException {
        //TODO: implement the method.
    }

    @Override
    public List<YangLeaf> getListOfLeaf() {
        return unmodifiableList(listOfLeaf);
    }

    @Override
    public void setListOfLeaf(List<YangLeaf> leafsList) {
        listOfLeaf = leafsList;
    }

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

    @Override
    public List<YangLeafList> getListOfLeafList() {
        return unmodifiableList(listOfLeafList);
    }

    @Override
    public void setListOfLeafList(List<YangLeafList> listOfLeafList) {
        this.listOfLeafList = listOfLeafList;
    }

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
        return unmodifiableList(yangAugmentedInfo);
    }

    @Override
    public void setLeafNameSpaceAndAddToParentSchemaMap() {
        // Add namespace for all leafs.
        for (YangLeaf yangLeaf : listOfLeaf) {
            yangLeaf.setLeafNameSpaceAndAddToParentSchemaMap(getNameSpace());
        }
        // Add namespace for all leaf list.
        for (YangLeafList yangLeafList : listOfLeafList) {
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
