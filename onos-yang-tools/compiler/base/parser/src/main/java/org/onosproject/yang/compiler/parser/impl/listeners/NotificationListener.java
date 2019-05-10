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

import org.onosproject.yang.compiler.datamodel.RpcNotificationContainer;
import org.onosproject.yang.compiler.datamodel.YangNode;
import org.onosproject.yang.compiler.datamodel.YangNotification;
import org.onosproject.yang.compiler.datamodel.exceptions.DataModelException;
import org.onosproject.yang.compiler.datamodel.utils.Parsable;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;
import org.onosproject.yang.compiler.parser.impl.TreeWalkListener;
import org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType;

import static org.onosproject.yang.compiler.datamodel.utils.GeneratedLanguage.JAVA_GENERATION;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.DESCRIPTION_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.NOTIFICATION_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.REFERENCE_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.STATUS_DATA;
import static org.onosproject.yang.compiler.parser.antlrgencode.GeneratedYangParser.NotificationStatementContext;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerCollisionDetector.detectCollidingChildUtil;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorLocation.ENTRY;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorLocation.EXIT;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorMessageConstruction.constructExtendedListenerErrorMessage;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorMessageConstruction.constructListenerErrorMessage;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.MISSING_CURRENT_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.MISSING_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.UNHANDLED_PARSED_DATA;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerUtil.getValidIdentifier;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerValidation.checkStackIsNotEmpty;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerValidation.validateCardinalityMaxOne;
import static org.onosproject.yang.compiler.translator.tojava.YangDataModelFactory.getYangNotificationNode;

/*
 * Reference: RFC6020 and YANG ANTLR Grammar
 *
 * ABNF grammar as per RFC6020
 *  notification-stmt   = notification-keyword sep
 *                        identifier-arg-str optsep
 *                        (";" /
 *                         "{" stmtsep
 *                             ;; these stmts can appear in any order
 *                             *(if-feature-stmt stmtsep)
 *                             [status-stmt stmtsep]
 *                             [description-stmt stmtsep]
 *                             [reference-stmt stmtsep]
 *                             *((typedef-stmt /
 *                                grouping-stmt) stmtsep)
 *                             *(data-def-stmt stmtsep)
 *                         "}")
 *
 * ANTLR grammar rule
 *    notificationStatement : NOTIFICATION_KEYWORD identifier (STMTEND | LEFT_CURLY_BRACE (ifFeatureStatement
 *                          | statusStatement | descriptionStatement | referenceStatement | typedefStatement
 *                          | groupingStatement | dataDefStatement)* RIGHT_CURLY_BRACE);
 */

/**
 * Represents listener based call back function corresponding to the "notification"
 * rule defined in ANTLR grammar file for corresponding ABNF rule in RFC 6020.
 */
public final class NotificationListener {

    /**
     * Creates a new notification listener.
     */
    private NotificationListener() {
    }

    /**
     * It is called when parser receives an input matching the grammar rule
     * (notification), performs validation and updates the data model tree.
     *
     * @param listener listener's object
     * @param ctx      context object of the grammar rule
     */
    public static void processNotificationEntry(TreeWalkListener listener,
                                                NotificationStatementContext ctx) {

        // Check for stack to be non empty.
        checkStackIsNotEmpty(listener, MISSING_HOLDER, NOTIFICATION_DATA,
                             ctx.identifier().getText(), ENTRY);

        String identifier = getValidIdentifier(ctx.identifier().getText(),
                                               NOTIFICATION_DATA, ctx);

        // Validate sub statement cardinality.
        validateSubStatementsCardinality(ctx);

        // Check for identifier collision
        int line = ctx.getStart().getLine();
        int charPositionInLine = ctx.getStart().getCharPositionInLine();
        detectCollidingChildUtil(listener, line, charPositionInLine, identifier,
                                 NOTIFICATION_DATA);

        Parsable curData = listener.getParsedDataStack().peek();
        if (curData instanceof RpcNotificationContainer) {

            YangNotification notification = getYangNotificationNode(JAVA_GENERATION);
            notification.setName(identifier);

            notification.setLineNumber(ctx.getStart().getLine());
            notification.setCharPosition(ctx.getStart().getCharPositionInLine());
            notification.setFileName(listener.getFileName());
            ((RpcNotificationContainer) curData).setNotificationPresenceFlag(true);
            YangNode curNode = (YangNode) curData;
            try {
                curNode.addChild(notification);
            } catch (DataModelException e) {
                throw new ParserException(constructExtendedListenerErrorMessage(UNHANDLED_PARSED_DATA,
                                                                                NOTIFICATION_DATA,
                                                                                ctx.identifier().getText(),
                                                                                ENTRY, e.getMessage()));
            }
            listener.getParsedDataStack().push(notification);
        } else {
            throw new ParserException(constructListenerErrorMessage(ListenerErrorType.INVALID_HOLDER, NOTIFICATION_DATA,
                                                                    ctx.identifier().getText(), ENTRY));
        }
    }

    /**
     * It is called when parser exits from grammar rule (notification), it perform
     * validations and updates the data model tree.
     *
     * @param listener listener's object
     * @param ctx      context object of the grammar rule
     */
    public static void processNotificationExit(TreeWalkListener listener,
                                               NotificationStatementContext ctx) {

        // Check for stack to be non empty.
        checkStackIsNotEmpty(listener, MISSING_HOLDER, NOTIFICATION_DATA,
                             ctx.identifier().getText(), EXIT);

        if (listener.getParsedDataStack().peek() instanceof YangNotification) {
            listener.getParsedDataStack().pop();
        } else {
            throw new ParserException(constructListenerErrorMessage(MISSING_CURRENT_HOLDER, NOTIFICATION_DATA,
                                                                    ctx.identifier().getText(), EXIT));
        }
    }

    /**
     * Validates the cardinality of notification sub-statements as per grammar.
     *
     * @param ctx context object of the grammar rule
     */
    private static void validateSubStatementsCardinality(NotificationStatementContext ctx) {

        validateCardinalityMaxOne(ctx.statusStatement(), STATUS_DATA,
                                  NOTIFICATION_DATA, ctx.identifier().getText());
        validateCardinalityMaxOne(ctx.descriptionStatement(),
                                  DESCRIPTION_DATA, NOTIFICATION_DATA,
                                  ctx.identifier().getText());
        validateCardinalityMaxOne(ctx.referenceStatement(), REFERENCE_DATA,
                                  NOTIFICATION_DATA,
                                  ctx.identifier().getText());
    }
}
