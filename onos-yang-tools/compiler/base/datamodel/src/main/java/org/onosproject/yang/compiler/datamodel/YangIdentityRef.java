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
import org.onosproject.yang.compiler.datamodel.utils.ResolvableStatus;
import org.onosproject.yang.compiler.datamodel.utils.YangConstructType;

import java.io.Serializable;

import static org.onosproject.yang.compiler.datamodel.utils.ResolvableStatus.INTRA_FILE_RESOLVED;
import static org.onosproject.yang.compiler.datamodel.utils.ResolvableStatus.RESOLVED;
import static org.onosproject.yang.compiler.datamodel.utils.ResolvableStatus.UNRESOLVED;

/*-
 * Reference RFC 6020.
 *
 * The identityref type is used to reference an existing identity.
 *
 *  The identityref's base Statement :
 *       The "base" statement, which is a substatement to the "type"
 *  statement, MUST be present if the type is "identityref".  The
 *  argument is the name of an identity, as defined by an "identity"
 *  statement.  If a prefix is present on the identity name, it refers to
 *  an identity defined in the module that was imported with that prefix.
 *  Otherwise, an identity with the matching name MUST be defined in the
 *  current module or an included submodule.
 *  Valid values for an identityref are any identities derived from the
 *  identityref's base identity.  On a particular server, the valid
 *  values are further restricted to the set of identities defined in the
 *  modules supported by the server.
 */

/**
 * Represents data model node to maintain information defined in YANG identityref.
 */
public class YangIdentityRef
        extends YangNode
        implements Cloneable, Parsable, Resolvable, Serializable {

    private static final long serialVersionUID = 806201692L;

    // Get referred identity parent information.
    private YangIdentity referredIdentity;

    // YANG node identifier.
    private YangNodeIdentifier baseIdentity;

    /**
     * Status of resolution. If completely resolved enum value is "RESOLVED",
     * if not enum value is "UNRESOLVED", in case reference of grouping/typedef/identityref/base
     * is added to uses/type/identityref/base but it's not resolved value of enum should be
     * "INTRA_FILE_RESOLVED".
     */
    private ResolvableStatus resolvableStatus;

    /**
     * Resolution for interfile grouping.
     */
    private boolean isIdentityForInterFileGroupingResolution;

    /**
     * Flag to indicate whether the leaf ref is inside grouping.
     */
    private boolean inGrouping;

    // Creates a specific identityref of node.
    public YangIdentityRef() {
        super(YangNodeType.IDENTITYREF_NODE, null);
        baseIdentity = new YangNodeIdentifier();
        resolvableStatus = UNRESOLVED;
    }

    @Override
    public void addToChildSchemaMap(YangSchemaNodeIdentifier schemaNodeIdentifier,
            YangSchemaNodeContextInfo yangSchemaNodeContextInfo)
            throws DataModelException {
        // Do nothing.
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
        return YangSchemaNodeType.YANG_NON_DATA_NODE;
    }

    @Override
    public ResolvableStatus getResolvableStatus() {
        return resolvableStatus;
    }

    @Override
    public void setResolvableStatus(ResolvableStatus resolvableStatus) {
        this.resolvableStatus = resolvableStatus;
    }

    @Override
    public Object resolve()
            throws DataModelException {

        // Check if the derived info is present.
        YangIdentity identity = getReferredIdentity();

        if (identity == null) {
            throw new DataModelException("Linker Error: Identity information is missing. " +
                    getName() + " in " +
                    getLineNumber() + " at " +
                    getCharPosition() +
                    " in " + getFileName() + "\"");
        }

        while (identity.getBaseNode() != null) {
            if (identity.getBaseNode().getResolvableStatus() != RESOLVED) {
                setResolvableStatus(INTRA_FILE_RESOLVED);
                return null;
            }
            identity = identity.getBaseNode().getReferredIdentity();
        }
        return null;
    }

    /**
     * Returns the YANG base node identifier.
     *
     * @return the YANG base node identifier
     */
    public YangNodeIdentifier getBaseIdentity() {
        return baseIdentity;
    }

    /**
     * Sets the YANG node identifier.
     *
     * @param baseIdentity the YANG node identifier to set
     */
    public void setBaseIdentity(YangNodeIdentifier baseIdentity) {
        this.baseIdentity = baseIdentity;
    }

    /**
     * Returns the name of identity.
     *
     * @return the identity name
     */
    @Override
    public String getName() {
        return baseIdentity.getName();
    }

    /**
     * Sets the name of identity.
     *
     * @param name the identity name to set
     */
    @Override
    public void setName(String name) {
        baseIdentity.setName(name);
    }

    /**
     * Sets node identifier.
     *
     * @param nodeIdentifier the node identifier
     */
    public void setNodeIdentifier(YangNodeIdentifier nodeIdentifier) {
        this.baseIdentity = nodeIdentifier;
    }

    /**
     * Returns prefix associated with base.
     *
     * @return prefix associated with base
     */
    public String getPrefix() {
        return baseIdentity.getPrefix();
    }

    /**
     * Sets prefix associated with base.
     *
     * @param prefix prefix associated with base
     */
    public void setPrefix(String prefix) {
        baseIdentity.setPrefix(prefix);
    }

    @Override
    public YangConstructType getYangConstructType() {
        return YangConstructType.IDENTITYREF_DATA;
    }

    @Override
    public void validateDataOnEntry()
            throws DataModelException {
    }

    @Override
    public void validateDataOnExit()
            throws DataModelException {
    }

    /**
     * Returns the parent identity node.
     *
     * @return the parent identity node
     */
    public YangIdentity getReferredIdentity() {
        return referredIdentity;
    }

    /**
     * Sets the parent identity node.
     *
     * @param referredIdentity the parent identity node to set
     */
    public void setReferredIdentity(YangIdentity referredIdentity) {
        this.referredIdentity = referredIdentity;
    }

    public boolean isIdentityForInterFileGroupingResolution() {
        return isIdentityForInterFileGroupingResolution;
    }

    public void setIdentityForInterFileGroupingResolution(
            boolean identityForInterFileGroupingResolution) {
        isIdentityForInterFileGroupingResolution =
                identityForInterFileGroupingResolution;
    }

    @Override
    public YangIdentityRef clone()
            throws CloneNotSupportedException {
        YangIdentityRef identityRef = (YangIdentityRef) super.clone();
        return identityRef;
    }

    @Override
    public String getJavaPackage() {
        //TODO: throw exception
        return null;
    }

    @Override
    public String getJavaClassNameOrBuiltInType() {
        //TODO: throw exception
        return null;
    }

    @Override
    public String getJavaAttributeName() {
        throw new RuntimeException("Attribute name is not applicable ");
    }

    /**
     * Returns flag value indicating whether leafref is inside grouping.
     *
     * @return true if leafref is in grouping, false otherwise
     */
    public boolean isInGrouping() {
        return inGrouping;
    }

    /**
     * Sets in grouping flag.
     *
     * @param inGrouping flag
     */
    public void setInGrouping(boolean inGrouping) {
        this.inGrouping = inGrouping;
    }

    @Override
    public YangNode clone(YangUses yangUses, boolean isDeviation, boolean isAnyData)
            throws CloneNotSupportedException {
        return (YangNode) super.clone();
    }
}
