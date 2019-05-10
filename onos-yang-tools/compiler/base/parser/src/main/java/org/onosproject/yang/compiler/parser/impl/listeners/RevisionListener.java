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

import org.onosproject.yang.compiler.datamodel.YangModule;
import org.onosproject.yang.compiler.datamodel.YangRevision;
import org.onosproject.yang.compiler.datamodel.YangSubModule;
import org.onosproject.yang.compiler.datamodel.utils.Parsable;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;
import org.onosproject.yang.compiler.parser.impl.TreeWalkListener;

import java.util.Date;

import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.REVISION_DATA;
import static org.onosproject.yang.compiler.parser.antlrgencode.GeneratedYangParser.RevisionStatementContext;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorLocation.ENTRY;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorLocation.EXIT;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorMessageConstruction.constructListenerErrorMessage;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.INVALID_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.MISSING_CURRENT_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.MISSING_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerUtil.getValidDateFromString;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerValidation.checkStackIsNotEmpty;

/*
 * Reference: RFC6020 and YANG ANTLR Grammar
 *
 * ABNF grammar as per RFC6020
 * module-stmt         = optsep module-keyword sep identifier-arg-str
 *                       optsep
 *                       "{" stmtsep
 *                           module-header-stmts
 *                           linkage-stmts
 *                           meta-stmts
 *                           revision-stmts
 *                           body-stmts
 *                       "}" optsep
 *
 * revision-stmt       = revision-keyword sep revision-date optsep
 *                             (";" /
 *                              "{" stmtsep
 *                                  [description-stmt stmtsep]
 *                                  [reference-stmt stmtsep]
 *                              "}")
 *
 * ANTLR grammar rule
 * module_stmt : MODULE_KEYWORD IDENTIFIER LEFT_CURLY_BRACE module_body* RIGHT_CURLY_BRACE;
 *
 * revision_stmt : REVISION_KEYWORD DATE_ARG (STMTEND | LEFT_CURLY_BRACE revision_stmt_body RIGHT_CURLY_BRACE);
 * revision_stmt_body : description_stmt? reference_stmt?;
 */

/**
 * Represents listener based call back function corresponding to the "revision"
 * rule defined in ANTLR grammar file for corresponding ABNF rule in RFC 6020.
 */
public final class RevisionListener {

    /**
     * Creates a new revision listener.
     */
    private RevisionListener() {
    }

    /**
     * It is called when parser receives an input matching the grammar rule
     * (revision),perform validations and update the data model tree.
     *
     * @param listener Listener's object
     * @param ctx      context object of the grammar rule
     */
    public static void processRevisionEntry(TreeWalkListener listener,
                                            RevisionStatementContext ctx) {

        // Check for stack to be non empty.
        checkStackIsNotEmpty(listener, MISSING_HOLDER, REVISION_DATA, ctx.dateArgumentString().getText(), ENTRY);

        Date date = getValidDateFromString(ctx.dateArgumentString().getText(), ctx);

        YangRevision revisionNode = new YangRevision();
        revisionNode.setRevDate(date);

        listener.getParsedDataStack().push(revisionNode);
    }

    /**
     * It is called when parser exits from grammar rule (revision), it perform
     * validations and update the data model tree.
     *
     * @param listener Listener's object
     * @param ctx      context object of the grammar rule
     */
    public static void processRevisionExit(TreeWalkListener listener, RevisionStatementContext
            ctx) {

        // Check for stack to be non empty.
        checkStackIsNotEmpty(listener, MISSING_HOLDER, REVISION_DATA, ctx.dateArgumentString().getText(), EXIT);

        Parsable tmpRevisionNode = listener.getParsedDataStack().peek();
        if (tmpRevisionNode instanceof YangRevision) {
            listener.getParsedDataStack().pop();

            // Check for stack to be non empty.
            checkStackIsNotEmpty(listener, MISSING_HOLDER, REVISION_DATA, ctx.dateArgumentString().getText(),
                                 EXIT);

            Parsable tmpNode = listener.getParsedDataStack().peek();
            switch (tmpNode.getYangConstructType()) {
                case MODULE_DATA: {
                    YangModule module = (YangModule) tmpNode;
                    if (module.getRevision() != null) {
                        Date curRevisionDate = module.getRevision().getRevDate();
                        if (curRevisionDate.before(((YangRevision) tmpRevisionNode).getRevDate())) {
                            module.setRevision((YangRevision) tmpRevisionNode);
                        }
                    } else {
                        module.setRevision((YangRevision) tmpRevisionNode);
                    }
                    break;
                }
                case SUB_MODULE_DATA: {
                    YangSubModule subModule = (YangSubModule) tmpNode;
                    if (subModule.getRevision() != null) {
                        Date curRevisionDate = subModule.getRevision().getRevDate();
                        if (curRevisionDate.before(((YangRevision) tmpRevisionNode).getRevDate())) {
                            subModule.setRevision((YangRevision) tmpRevisionNode);
                        }
                    } else {
                        subModule.setRevision((YangRevision) tmpRevisionNode);
                    }
                    break;
                }
                default:
                    throw new ParserException(constructListenerErrorMessage(INVALID_HOLDER, REVISION_DATA,
                                                                            ctx.dateArgumentString().getText(),
                                                                            EXIT));
            }
        } else {
            throw new ParserException(constructListenerErrorMessage(MISSING_CURRENT_HOLDER, REVISION_DATA,
                                                                    ctx.dateArgumentString().getText(), EXIT));
        }
    }

    /**
     * Validate revision.
     *
     * @param listener Listener's object
     * @param ctx      context object of the grammar rule
     * @return validation result
     */
    private static boolean validateRevision(TreeWalkListener listener,
                                            RevisionStatementContext ctx) {
        // TODO to be implemented
        return true;
    }
}
