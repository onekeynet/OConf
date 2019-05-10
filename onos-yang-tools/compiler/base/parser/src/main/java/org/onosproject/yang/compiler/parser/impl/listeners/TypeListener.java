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

import org.onosproject.yang.compiler.datamodel.YangDerivedInfo;
import org.onosproject.yang.compiler.datamodel.YangDeviateReplace;
import org.onosproject.yang.compiler.datamodel.YangLeaf;
import org.onosproject.yang.compiler.datamodel.YangLeafList;
import org.onosproject.yang.compiler.datamodel.YangNode;
import org.onosproject.yang.compiler.datamodel.YangNodeIdentifier;
import org.onosproject.yang.compiler.datamodel.YangType;
import org.onosproject.yang.compiler.datamodel.YangTypeDef;
import org.onosproject.yang.compiler.datamodel.YangUnion;
import org.onosproject.yang.compiler.datamodel.exceptions.DataModelException;
import org.onosproject.yang.compiler.datamodel.utils.DataModelUtils;
import org.onosproject.yang.compiler.datamodel.utils.Parsable;
import org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes;
import org.onosproject.yang.compiler.linker.impl.YangResolutionInfoImpl;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;
import org.onosproject.yang.compiler.parser.impl.TreeWalkListener;
import org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType;

import static org.onosproject.yang.compiler.datamodel.utils.GeneratedLanguage.JAVA_GENERATION;
import static org.onosproject.yang.compiler.datamodel.utils.ResolvableStatus.UNRESOLVED;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.TYPE_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes.DERIVED;
import static org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes.INSTANCE_IDENTIFIER;
import static org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes.getType;
import static org.onosproject.yang.compiler.parser.antlrgencode.GeneratedYangParser.TypeStatementContext;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorLocation.ENTRY;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorLocation.EXIT;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorMessageConstruction.constructExtendedListenerErrorMessage;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorMessageConstruction.constructListenerErrorMessage;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.INVALID_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.MISSING_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerUtil.getValidNodeIdentifier;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerValidation.checkStackIsNotEmpty;
import static org.onosproject.yang.compiler.translator.tojava.YangDataModelFactory.getYangType;

/*
 * Reference: RFC6020 and YANG ANTLR Grammar
 *
 * ABNF grammar as per RFC6020
 *  type-stmt           = type-keyword sep identifier-ref-arg-str optsep
 *                        (";" /
 *                         "{" stmtsep
 *                            type-body-stmts
 *                         "}")
 *
 * ANTLR grammar rule
 * typeStatement : TYPE_KEYWORD string (STMTEND | LEFT_CURLY_BRACE typeBodyStatements RIGHT_CURLY_BRACE);
 */

/**
 * Represents listener based call back function corresponding to the "type" rule
 * defined in ANTLR grammar file for corresponding ABNF rule in RFC 6020.
 */
public final class TypeListener {

    /**
     * Creates a new type listener.
     */
    private TypeListener() {
    }

    /**
     * It is called when parser receives an input matching the grammar rule
     * (type), performs validation and updates the data model tree.
     *
     * @param listener listener's object
     * @param ctx      context object of the grammar rule
     */
    public static void processTypeEntry(TreeWalkListener listener,
                                        TypeStatementContext ctx) {

        // Check for stack to be non empty.
        checkStackIsNotEmpty(listener, MISSING_HOLDER, TYPE_DATA, ctx.string().getText(), ENTRY);

        // Validate node identifier.
        YangNodeIdentifier nodeIdentifier = getValidNodeIdentifier(ctx.string().getText(), TYPE_DATA,
                                                                   ctx);

        // Obtain the YANG data type.
        YangDataTypes yangDataTypes = getType(ctx.string().getText());

        // validate type sub-statement cardinality
        validateTypeSubStatementCardinality(ctx, yangDataTypes);

        // Create YANG type object and fill the values.
        YangType<?> type = getYangType(JAVA_GENERATION);
        type.setNodeId(nodeIdentifier);
        type.setDataType(yangDataTypes);

        type.setLineNumber(ctx.getStart().getLine());
        type.setCharPosition(ctx.getStart().getCharPositionInLine());
        type.setFileName(listener.getFileName());
        // Set default require instance value as true for instance identifier.
        setDefaultRequireInstanceForInstanceIdentifier(type);

        int errorLine = ctx.getStart().getLine();
        int errorPosition = ctx.getStart().getCharPositionInLine();

        Parsable tmpData = listener.getParsedDataStack().peek();
        switch (tmpData.getYangConstructType()) {
            case LEAF_DATA:
                YangLeaf leaf = (YangLeaf) tmpData;
                leaf.setDataType(type);

                /*
                 * If data type is derived, resolution information to be added
                 * in resolution list.
                 */
                if (yangDataTypes == DERIVED) {
                    // Parent YANG node of leaf to be added in resolution information.
                    Parsable leafData = listener.getParsedDataStack().pop();
                    Parsable parentNodeOfLeaf = listener.getParsedDataStack().peek();
                    listener.getParsedDataStack().push(leafData);

                    // Verify parent node of leaf
                    if (!(parentNodeOfLeaf instanceof YangNode)) {
                        throw new ParserException(constructListenerErrorMessage(INVALID_HOLDER, TYPE_DATA,
                                                                                ctx.string().getText(), EXIT));
                    }

                    // Create empty derived info and attach it to type extended info.
                    YangDerivedInfo<?> yangDerivedInfo = new YangDerivedInfo<>();
                    yangDerivedInfo.setLineNumber(ctx.getStart().getLine());
                    yangDerivedInfo.setCharPosition(ctx.getStart().getCharPositionInLine());
                    yangDerivedInfo.setFileName(listener.getFileName());
                    ((YangType<YangDerivedInfo>) type).setDataTypeExtendedInfo(yangDerivedInfo);

                    type.setResolvableStatus(UNRESOLVED);

                    // Add resolution information to the list
                    YangResolutionInfoImpl resolutionInfo =
                            new YangResolutionInfoImpl<YangType>(
                                    type, (YangNode) parentNodeOfLeaf, errorLine,
                                    errorPosition);
                    addToResolutionList(resolutionInfo, ctx);
                }
                break;
            case LEAF_LIST_DATA:
                YangLeafList leafList = (YangLeafList) tmpData;
                leafList.setDataType(type);

                /*
                 * If data type is derived, resolution information to be added
                 * in resolution list.
                 */
                if (yangDataTypes == DERIVED) {
                    // Parent YANG node of leaf list to be added in resolution information.
                    Parsable leafListData = listener.getParsedDataStack().pop();
                    Parsable parentNodeOfLeafList = listener.getParsedDataStack().peek();
                    listener.getParsedDataStack().push(leafListData);

                    // Verify parent node of leaf
                    if (!(parentNodeOfLeafList instanceof YangNode)) {
                        throw new ParserException(constructListenerErrorMessage(INVALID_HOLDER, TYPE_DATA,
                                                                                ctx.string().getText(), EXIT));
                    }

                    // Create empty derived info and attach it to type extended info.
                    YangDerivedInfo<?> yangDerivedInfo = new YangDerivedInfo<>();
                    yangDerivedInfo.setLineNumber(ctx.getStart().getLine());
                    yangDerivedInfo.setCharPosition(ctx.getStart().getCharPositionInLine());
                    yangDerivedInfo.setFileName(listener.getFileName());
                    ((YangType<YangDerivedInfo>) type).setDataTypeExtendedInfo(yangDerivedInfo);

                    // Add resolution information to the list
                    YangResolutionInfoImpl resolutionInfo =
                            new YangResolutionInfoImpl<YangType>(
                                    type, (YangNode) parentNodeOfLeafList,
                                    errorLine, errorPosition);
                    addToResolutionList(resolutionInfo, ctx);
                }
                break;
            case UNION_DATA:
                YangUnion unionNode = (YangUnion) tmpData;
                try {
                    unionNode.addType(type);
                } catch (DataModelException e) {
                    ParserException parserException = new ParserException(e.getMessage());
                    parserException.setLine(ctx.getStart().getLine());
                    parserException.setCharPosition(ctx.getStart().getCharPositionInLine());
                    throw parserException;
                }

                /*
                 * If data type is derived, resolution information to be added
                 * in resolution list.
                 */
                if (yangDataTypes == DERIVED) {

                    // Create empty derived info and attach it to type extended info.
                    YangDerivedInfo<?> yangDerivedInfo = new YangDerivedInfo<>();
                    yangDerivedInfo.setLineNumber(ctx.getStart().getLine());
                    yangDerivedInfo.setCharPosition(ctx.getStart().getCharPositionInLine());
                    yangDerivedInfo.setFileName(listener.getFileName());
                    ((YangType<YangDerivedInfo>) type).setDataTypeExtendedInfo(yangDerivedInfo);

                    type.setResolvableStatus(UNRESOLVED);

                    // Add resolution information to the list
                    YangResolutionInfoImpl resolutionInfo =
                            new YangResolutionInfoImpl<YangType>(
                                    type, unionNode, errorLine, errorPosition);
                    addToResolutionList(resolutionInfo, ctx);
                }

                break;
            case TYPEDEF_DATA:
                /* Prepare the base type info and set in derived type */
                YangTypeDef typeDef = (YangTypeDef) tmpData;
                typeDef.setDataType(type);

                /*
                 * If data type is derived, resolution information to be added
                 * in resolution list.
                 */
                if (yangDataTypes == DERIVED) {
                    // Create empty derived info and attach it to type extended info.
                    YangDerivedInfo<?> yangDerivedInfo = new YangDerivedInfo<>();
                    yangDerivedInfo.setLineNumber(ctx.getStart().getLine());
                    yangDerivedInfo.setCharPosition(ctx.getStart().getCharPositionInLine());
                    yangDerivedInfo.setFileName(listener.getFileName());
                    ((YangType<YangDerivedInfo>) type).setDataTypeExtendedInfo(yangDerivedInfo);

                    type.setResolvableStatus(UNRESOLVED);
                    // Add resolution information to the list
                    YangResolutionInfoImpl resolutionInfo =
                            new YangResolutionInfoImpl<YangType>(
                                    type, typeDef, errorLine, errorPosition);
                    addToResolutionList(resolutionInfo, ctx);
                }
                break;
            case DEVIATE_REPLACE:
                YangDeviateReplace replace = (YangDeviateReplace) tmpData;
                replace.setDataType(type);
                break;
            default:
                throw new ParserException(constructListenerErrorMessage(INVALID_HOLDER, TYPE_DATA,
                                                                        ctx.string().getText(), EXIT));
        }

        // Push the type to the stack.
        listener.getParsedDataStack().push(type);
    }

    /**
     * Sets the default require instance value as true when the type is instance identifier.
     *
     * @param type type to which the value has to be set
     */
    private static void setDefaultRequireInstanceForInstanceIdentifier(YangType<?> type) {

        if (type.getDataType() == INSTANCE_IDENTIFIER) {
            ((YangType<Boolean>) type).setDataTypeExtendedInfo(true);
        }
    }

    /**
     * It is called when parser exits from grammar rule (type), it perform
     * validations and update the data model tree.
     *
     * @param listener Listener's object
     * @param ctx      context object of the grammar rule
     */
    public static void processTypeExit(TreeWalkListener listener,
                                       TypeStatementContext ctx) {

        // Check for stack to be non empty.
        checkStackIsNotEmpty(listener, ListenerErrorType.MISSING_CURRENT_HOLDER, TYPE_DATA,
                             ctx.string().getText(), EXIT);

        Parsable parsableType = listener.getParsedDataStack().pop();
        if (!(parsableType instanceof YangType)) {
            throw new ParserException(constructListenerErrorMessage(INVALID_HOLDER, TYPE_DATA,
                                                                    ctx.string().getText(), EXIT));
        }
    }

    /**
     * Adds to resolution list.
     *
     * @param resolutionInfo resolution information
     * @param ctx            context object of the grammar rule
     */
    private static void addToResolutionList(YangResolutionInfoImpl<YangType> resolutionInfo,
                                            TypeStatementContext ctx) {
        try {
            DataModelUtils.addResolutionInfo(resolutionInfo);
        } catch (DataModelException e) {
            throw new ParserException(constructExtendedListenerErrorMessage(ListenerErrorType.UNHANDLED_PARSED_DATA,
                                                                            TYPE_DATA, ctx.string().getText(),
                                                                            ENTRY, e.getMessage()));
        }
    }

    /**
     * Validates type body statements cardinality.
     *
     * @param ctx          context object of the grammar rule
     * @param yangDataType yang data type
     */
    private static void validateTypeSubStatementCardinality(TypeStatementContext ctx,
                                                            YangDataTypes yangDataType) {
        if (ctx.typeBodyStatements() == null || ctx.typeBodyStatements().isEmpty()) {
            ParserException parserException;
            switch (yangDataType) {
                case UNION:
                    parserException = new ParserException("YANG file error : a type union" +
                                                                  " must have atleast one type statement.");
                    break;
                case ENUMERATION:
                    parserException = new ParserException("YANG file error : a type enumeration" +
                                                                  " must have atleast one enum statement.");
                    break;
                case BITS:
                    parserException = new ParserException("YANG file error : a type bits" +
                                                                  " must have atleast one bit statement.");
                    break;
                case DECIMAL64:
                    parserException = new ParserException("YANG file error : a type decimal64" +
                                                                  " must have fraction-digits statement.");
                    break;
                case LEAFREF:
                    parserException = new ParserException("YANG file error : a type leafref" +
                                                                  " must have one path statement.");
                    break;
                case IDENTITYREF:
                    parserException = new ParserException("YANG file error : a type identityref" +
                                                                  " must have base statement.");
                    break;
                default:
                    return;
            }
            parserException.setLine(ctx.getStart().getLine());
            parserException.setCharPosition(ctx.getStart().getCharPositionInLine());
            throw parserException;
        }
    }
}
