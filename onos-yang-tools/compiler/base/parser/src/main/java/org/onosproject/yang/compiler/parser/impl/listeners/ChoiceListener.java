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

import org.onosproject.yang.compiler.datamodel.YangAugment;
import org.onosproject.yang.compiler.datamodel.YangCase;
import org.onosproject.yang.compiler.datamodel.YangChoice;
import org.onosproject.yang.compiler.datamodel.YangContainer;
import org.onosproject.yang.compiler.datamodel.YangGrouping;
import org.onosproject.yang.compiler.datamodel.YangInput;
import org.onosproject.yang.compiler.datamodel.YangList;
import org.onosproject.yang.compiler.datamodel.YangModule;
import org.onosproject.yang.compiler.datamodel.YangNode;
import org.onosproject.yang.compiler.datamodel.YangNotification;
import org.onosproject.yang.compiler.datamodel.YangOutput;
import org.onosproject.yang.compiler.datamodel.YangSubModule;
import org.onosproject.yang.compiler.datamodel.exceptions.DataModelException;
import org.onosproject.yang.compiler.datamodel.utils.Parsable;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;
import org.onosproject.yang.compiler.parser.impl.TreeWalkListener;

import static org.onosproject.yang.compiler.datamodel.utils.GeneratedLanguage.JAVA_GENERATION;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.CHOICE_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.CONFIG_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.DEFAULT_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.DESCRIPTION_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.MANDATORY_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.REFERENCE_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.STATUS_DATA;
import static org.onosproject.yang.compiler.datamodel.utils.YangConstructType.WHEN_DATA;
import static org.onosproject.yang.compiler.parser.antlrgencode.GeneratedYangParser.ChoiceStatementContext;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerCollisionDetector.detectCollidingChildUtil;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorLocation.ENTRY;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorLocation.EXIT;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorMessageConstruction.constructExtendedListenerErrorMessage;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorMessageConstruction.constructListenerErrorMessage;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.INVALID_CONTENT;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.INVALID_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.MISSING_CURRENT_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.MISSING_HOLDER;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerErrorType.UNHANDLED_PARSED_DATA;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerUtil.getValidIdentifier;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerValidation.checkStackIsNotEmpty;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerValidation.getParentNodeConfig;
import static org.onosproject.yang.compiler.parser.impl.parserutils.ListenerValidation.validateCardinalityMaxOne;
import static org.onosproject.yang.compiler.translator.tojava.YangDataModelFactory.getYangChoiceNode;

/*
 * Reference: RFC6020 and YANG ANTLR Grammar
 *
 * ABNF grammar as per RFC6020
 *  choice-stmt         = choice-keyword sep identifier-arg-str optsep
 *                        (";" /
 *                         "{" stmtsep
 *                             ;; these stmts can appear in any order
 *                             [when-stmt stmtsep]
 *                             *(if-feature-stmt stmtsep)
 *                             [default-stmt stmtsep]
 *                             [config-stmt stmtsep]
 *                             [mandatory-stmt stmtsep]
 *                             [status-stmt stmtsep]
 *                             [description-stmt stmtsep]
 *                             [reference-stmt stmtsep]
 *                             *((short-case-stmt / case-stmt) stmtsep)
 *                         "}")
 *
 * ANTLR grammar rule
 * choiceStatement : CHOICE_KEYWORD identifier (STMTEND | LEFT_CURLY_BRACE (whenStatement | ifFeatureStatement
 *                 | defaultStatement | configStatement | mandatoryStatement | statusStatement | descriptionStatement
 *                 | referenceStatement | shortCaseStatement | caseStatement)* RIGHT_CURLY_BRACE);
 */

/**
 * Represents listener based call back function corresponding to the "choice"
 * rule defined in ANTLR grammar file for corresponding ABNF rule in RFC 6020.
 */
public final class ChoiceListener {

    /**
     * Create a new choice listener.
     */
    private ChoiceListener() {
    }

    /**
     * It is called when parser receives an input matching the grammar rule
     * (choice), perform validations and update the data model tree.
     *
     * @param listener Listener's object
     * @param ctx      context object of the grammar rule
     */
    public static void processChoiceEntry(TreeWalkListener listener,
                                          ChoiceStatementContext ctx) {

        // Check for stack to be non empty.
        checkStackIsNotEmpty(listener, MISSING_HOLDER, CHOICE_DATA, ctx.identifier().getText(), ENTRY);

        // Check validity of identifier and remove double quotes.
        String identifier = getValidIdentifier(ctx.identifier().getText(), CHOICE_DATA, ctx);

        // Validate sub statement cardinality.
        validateSubStatementsCardinality(ctx);

        Parsable curData = listener.getParsedDataStack().peek();

        // Check for identifier collision
        int line = ctx.getStart().getLine();
        int charPositionInLine = ctx.getStart().getCharPositionInLine();
        detectCollidingChildUtil(listener, line, charPositionInLine, identifier, CHOICE_DATA);

        if (curData instanceof YangModule || curData instanceof YangSubModule || curData instanceof YangContainer
                || curData instanceof YangList || curData instanceof YangCase || curData instanceof YangGrouping
                || curData instanceof YangAugment || curData instanceof YangInput || curData instanceof YangOutput
                || curData instanceof YangNotification) {

            YangChoice choiceNode = getYangChoiceNode(JAVA_GENERATION);
            choiceNode.setName(identifier);
            choiceNode.setLineNumber(line);
            choiceNode.setCharPosition(charPositionInLine);
            choiceNode.setFileName(listener.getFileName());
            /*
             * If "config" is not specified, the default is the same as the parent
             * schema node's "config" value.
             */
            if (ctx.configStatement().isEmpty()) {
                boolean parentConfig = getParentNodeConfig(listener);
                choiceNode.setConfig(parentConfig);
            }

            YangNode curNode = (YangNode) curData;
            try {
                curNode.addChild(choiceNode);
            } catch (DataModelException e) {
                throw new ParserException(constructExtendedListenerErrorMessage(UNHANDLED_PARSED_DATA,
                        CHOICE_DATA, ctx.identifier().getText(), ENTRY, e.getMessage()));
            }
            listener.getParsedDataStack().push(choiceNode);
        } else {
            throw new ParserException(constructListenerErrorMessage(INVALID_HOLDER,
                    CHOICE_DATA, ctx.identifier().getText(), ENTRY));
        }
    }

    /**
     * It is called when parser exits from grammar rule (choice), it perform
     * validations and update the data model tree.
     *
     * @param listener Listener's object
     * @param ctx      context object of the grammar rule
     */
    public static void processChoiceExit(TreeWalkListener listener,
                                         ChoiceStatementContext ctx) {

        // Check for stack to be non empty.
        checkStackIsNotEmpty(listener, MISSING_HOLDER, CHOICE_DATA, ctx.identifier().getText(), EXIT);

        if (listener.getParsedDataStack().peek() instanceof YangChoice) {
            YangChoice choiceNode = (YangChoice) listener.getParsedDataStack().peek();
            try {
                choiceNode.validateDataOnExit();
            } catch (DataModelException e) {
                throw new ParserException(constructListenerErrorMessage(INVALID_CONTENT, CHOICE_DATA,
                                                                        ctx.identifier().getText(), EXIT));
            }
            listener.getParsedDataStack().pop();
        } else {
            throw new ParserException(constructListenerErrorMessage(MISSING_CURRENT_HOLDER, CHOICE_DATA,
                    ctx.identifier().getText(), EXIT));
        }
    }

    /**
     * Validates the cardinality of choice sub-statements as per grammar.
     *
     * @param ctx context object of the grammar rule.
     */
    private static void validateSubStatementsCardinality(ChoiceStatementContext ctx) {

        validateCardinalityMaxOne(ctx.whenStatement(), WHEN_DATA, CHOICE_DATA, ctx.identifier().getText());
        validateCardinalityMaxOne(ctx.defaultStatement(), DEFAULT_DATA, CHOICE_DATA, ctx.identifier().getText());
        validateCardinalityMaxOne(ctx.configStatement(), CONFIG_DATA, CHOICE_DATA, ctx.identifier().getText());
        validateCardinalityMaxOne(ctx.mandatoryStatement(), MANDATORY_DATA, CHOICE_DATA, ctx.identifier().getText());
        validateCardinalityMaxOne(ctx.statusStatement(), STATUS_DATA, CHOICE_DATA, ctx.identifier().getText());
        validateCardinalityMaxOne(ctx.descriptionStatement(), DESCRIPTION_DATA, CHOICE_DATA,
                                  ctx.identifier().getText());
        validateCardinalityMaxOne(ctx.referenceStatement(), REFERENCE_DATA, CHOICE_DATA, ctx.identifier().getText());
    }
}
