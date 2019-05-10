// Generated from GeneratedYang.g4 by ANTLR 4.5

package org.onosproject.yang.compiler.parser.antlrgencode;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GeneratedYangParser}.
 */
public interface GeneratedYangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#yangfile}.
	 * @param ctx the parse tree
	 */
	void enterYangfile(GeneratedYangParser.YangfileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#yangfile}.
	 * @param ctx the parse tree
	 */
	void exitYangfile(GeneratedYangParser.YangfileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#moduleStatement}.
	 * @param ctx the parse tree
	 */
	void enterModuleStatement(GeneratedYangParser.ModuleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#moduleStatement}.
	 * @param ctx the parse tree
	 */
	void exitModuleStatement(GeneratedYangParser.ModuleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#moduleBody}.
	 * @param ctx the parse tree
	 */
	void enterModuleBody(GeneratedYangParser.ModuleBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#moduleBody}.
	 * @param ctx the parse tree
	 */
	void exitModuleBody(GeneratedYangParser.ModuleBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#moduleHeaderStatement}.
	 * @param ctx the parse tree
	 */
	void enterModuleHeaderStatement(GeneratedYangParser.ModuleHeaderStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#moduleHeaderStatement}.
	 * @param ctx the parse tree
	 */
	void exitModuleHeaderStatement(GeneratedYangParser.ModuleHeaderStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#linkageStatements}.
	 * @param ctx the parse tree
	 */
	void enterLinkageStatements(GeneratedYangParser.LinkageStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#linkageStatements}.
	 * @param ctx the parse tree
	 */
	void exitLinkageStatements(GeneratedYangParser.LinkageStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#metaStatements}.
	 * @param ctx the parse tree
	 */
	void enterMetaStatements(GeneratedYangParser.MetaStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#metaStatements}.
	 * @param ctx the parse tree
	 */
	void exitMetaStatements(GeneratedYangParser.MetaStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#revisionStatements}.
	 * @param ctx the parse tree
	 */
	void enterRevisionStatements(GeneratedYangParser.RevisionStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#revisionStatements}.
	 * @param ctx the parse tree
	 */
	void exitRevisionStatements(GeneratedYangParser.RevisionStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#bodyStatements}.
	 * @param ctx the parse tree
	 */
	void enterBodyStatements(GeneratedYangParser.BodyStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#bodyStatements}.
	 * @param ctx the parse tree
	 */
	void exitBodyStatements(GeneratedYangParser.BodyStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#yangVersionStatement}.
	 * @param ctx the parse tree
	 */
	void enterYangVersionStatement(GeneratedYangParser.YangVersionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#yangVersionStatement}.
	 * @param ctx the parse tree
	 */
	void exitYangVersionStatement(GeneratedYangParser.YangVersionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#namespaceStatement}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceStatement(GeneratedYangParser.NamespaceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#namespaceStatement}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceStatement(GeneratedYangParser.NamespaceStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#prefixStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrefixStatement(GeneratedYangParser.PrefixStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#prefixStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrefixStatement(GeneratedYangParser.PrefixStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void enterImportStatement(GeneratedYangParser.ImportStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void exitImportStatement(GeneratedYangParser.ImportStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#importStatementBody}.
	 * @param ctx the parse tree
	 */
	void enterImportStatementBody(GeneratedYangParser.ImportStatementBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#importStatementBody}.
	 * @param ctx the parse tree
	 */
	void exitImportStatementBody(GeneratedYangParser.ImportStatementBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#revisionDateStatement}.
	 * @param ctx the parse tree
	 */
	void enterRevisionDateStatement(GeneratedYangParser.RevisionDateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#revisionDateStatement}.
	 * @param ctx the parse tree
	 */
	void exitRevisionDateStatement(GeneratedYangParser.RevisionDateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#includeStatement}.
	 * @param ctx the parse tree
	 */
	void enterIncludeStatement(GeneratedYangParser.IncludeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#includeStatement}.
	 * @param ctx the parse tree
	 */
	void exitIncludeStatement(GeneratedYangParser.IncludeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#organizationStatement}.
	 * @param ctx the parse tree
	 */
	void enterOrganizationStatement(GeneratedYangParser.OrganizationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#organizationStatement}.
	 * @param ctx the parse tree
	 */
	void exitOrganizationStatement(GeneratedYangParser.OrganizationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#contactStatement}.
	 * @param ctx the parse tree
	 */
	void enterContactStatement(GeneratedYangParser.ContactStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#contactStatement}.
	 * @param ctx the parse tree
	 */
	void exitContactStatement(GeneratedYangParser.ContactStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#descriptionStatement}.
	 * @param ctx the parse tree
	 */
	void enterDescriptionStatement(GeneratedYangParser.DescriptionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#descriptionStatement}.
	 * @param ctx the parse tree
	 */
	void exitDescriptionStatement(GeneratedYangParser.DescriptionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#referenceStatement}.
	 * @param ctx the parse tree
	 */
	void enterReferenceStatement(GeneratedYangParser.ReferenceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#referenceStatement}.
	 * @param ctx the parse tree
	 */
	void exitReferenceStatement(GeneratedYangParser.ReferenceStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#revisionStatement}.
	 * @param ctx the parse tree
	 */
	void enterRevisionStatement(GeneratedYangParser.RevisionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#revisionStatement}.
	 * @param ctx the parse tree
	 */
	void exitRevisionStatement(GeneratedYangParser.RevisionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#revisionStatementBody}.
	 * @param ctx the parse tree
	 */
	void enterRevisionStatementBody(GeneratedYangParser.RevisionStatementBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#revisionStatementBody}.
	 * @param ctx the parse tree
	 */
	void exitRevisionStatementBody(GeneratedYangParser.RevisionStatementBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#subModuleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSubModuleStatement(GeneratedYangParser.SubModuleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#subModuleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSubModuleStatement(GeneratedYangParser.SubModuleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#submoduleBody}.
	 * @param ctx the parse tree
	 */
	void enterSubmoduleBody(GeneratedYangParser.SubmoduleBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#submoduleBody}.
	 * @param ctx the parse tree
	 */
	void exitSubmoduleBody(GeneratedYangParser.SubmoduleBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#submoduleHeaderStatement}.
	 * @param ctx the parse tree
	 */
	void enterSubmoduleHeaderStatement(GeneratedYangParser.SubmoduleHeaderStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#submoduleHeaderStatement}.
	 * @param ctx the parse tree
	 */
	void exitSubmoduleHeaderStatement(GeneratedYangParser.SubmoduleHeaderStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#belongstoStatement}.
	 * @param ctx the parse tree
	 */
	void enterBelongstoStatement(GeneratedYangParser.BelongstoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#belongstoStatement}.
	 * @param ctx the parse tree
	 */
	void exitBelongstoStatement(GeneratedYangParser.BelongstoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#belongstoStatementBody}.
	 * @param ctx the parse tree
	 */
	void enterBelongstoStatementBody(GeneratedYangParser.BelongstoStatementBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#belongstoStatementBody}.
	 * @param ctx the parse tree
	 */
	void exitBelongstoStatementBody(GeneratedYangParser.BelongstoStatementBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#extensionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExtensionStatement(GeneratedYangParser.ExtensionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#extensionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExtensionStatement(GeneratedYangParser.ExtensionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#extensionBody}.
	 * @param ctx the parse tree
	 */
	void enterExtensionBody(GeneratedYangParser.ExtensionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#extensionBody}.
	 * @param ctx the parse tree
	 */
	void exitExtensionBody(GeneratedYangParser.ExtensionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#argumentStatement}.
	 * @param ctx the parse tree
	 */
	void enterArgumentStatement(GeneratedYangParser.ArgumentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#argumentStatement}.
	 * @param ctx the parse tree
	 */
	void exitArgumentStatement(GeneratedYangParser.ArgumentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#argumentBody}.
	 * @param ctx the parse tree
	 */
	void enterArgumentBody(GeneratedYangParser.ArgumentBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#argumentBody}.
	 * @param ctx the parse tree
	 */
	void exitArgumentBody(GeneratedYangParser.ArgumentBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#yinElementStatement}.
	 * @param ctx the parse tree
	 */
	void enterYinElementStatement(GeneratedYangParser.YinElementStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#yinElementStatement}.
	 * @param ctx the parse tree
	 */
	void exitYinElementStatement(GeneratedYangParser.YinElementStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#identityStatement}.
	 * @param ctx the parse tree
	 */
	void enterIdentityStatement(GeneratedYangParser.IdentityStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#identityStatement}.
	 * @param ctx the parse tree
	 */
	void exitIdentityStatement(GeneratedYangParser.IdentityStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#identityBody}.
	 * @param ctx the parse tree
	 */
	void enterIdentityBody(GeneratedYangParser.IdentityBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#identityBody}.
	 * @param ctx the parse tree
	 */
	void exitIdentityBody(GeneratedYangParser.IdentityBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#baseStatement}.
	 * @param ctx the parse tree
	 */
	void enterBaseStatement(GeneratedYangParser.BaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#baseStatement}.
	 * @param ctx the parse tree
	 */
	void exitBaseStatement(GeneratedYangParser.BaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#featureStatement}.
	 * @param ctx the parse tree
	 */
	void enterFeatureStatement(GeneratedYangParser.FeatureStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#featureStatement}.
	 * @param ctx the parse tree
	 */
	void exitFeatureStatement(GeneratedYangParser.FeatureStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#featureBody}.
	 * @param ctx the parse tree
	 */
	void enterFeatureBody(GeneratedYangParser.FeatureBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#featureBody}.
	 * @param ctx the parse tree
	 */
	void exitFeatureBody(GeneratedYangParser.FeatureBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#dataDefStatement}.
	 * @param ctx the parse tree
	 */
	void enterDataDefStatement(GeneratedYangParser.DataDefStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#dataDefStatement}.
	 * @param ctx the parse tree
	 */
	void exitDataDefStatement(GeneratedYangParser.DataDefStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#ifFeatureStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfFeatureStatement(GeneratedYangParser.IfFeatureStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#ifFeatureStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfFeatureStatement(GeneratedYangParser.IfFeatureStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#unitsStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnitsStatement(GeneratedYangParser.UnitsStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#unitsStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnitsStatement(GeneratedYangParser.UnitsStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#typedefStatement}.
	 * @param ctx the parse tree
	 */
	void enterTypedefStatement(GeneratedYangParser.TypedefStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#typedefStatement}.
	 * @param ctx the parse tree
	 */
	void exitTypedefStatement(GeneratedYangParser.TypedefStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#typeStatement}.
	 * @param ctx the parse tree
	 */
	void enterTypeStatement(GeneratedYangParser.TypeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#typeStatement}.
	 * @param ctx the parse tree
	 */
	void exitTypeStatement(GeneratedYangParser.TypeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#typeBodyStatements}.
	 * @param ctx the parse tree
	 */
	void enterTypeBodyStatements(GeneratedYangParser.TypeBodyStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#typeBodyStatements}.
	 * @param ctx the parse tree
	 */
	void exitTypeBodyStatements(GeneratedYangParser.TypeBodyStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#decimal64Specification}.
	 * @param ctx the parse tree
	 */
	void enterDecimal64Specification(GeneratedYangParser.Decimal64SpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#decimal64Specification}.
	 * @param ctx the parse tree
	 */
	void exitDecimal64Specification(GeneratedYangParser.Decimal64SpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#fractionDigitStatement}.
	 * @param ctx the parse tree
	 */
	void enterFractionDigitStatement(GeneratedYangParser.FractionDigitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#fractionDigitStatement}.
	 * @param ctx the parse tree
	 */
	void exitFractionDigitStatement(GeneratedYangParser.FractionDigitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#numericalRestrictions}.
	 * @param ctx the parse tree
	 */
	void enterNumericalRestrictions(GeneratedYangParser.NumericalRestrictionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#numericalRestrictions}.
	 * @param ctx the parse tree
	 */
	void exitNumericalRestrictions(GeneratedYangParser.NumericalRestrictionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#rangeStatement}.
	 * @param ctx the parse tree
	 */
	void enterRangeStatement(GeneratedYangParser.RangeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#rangeStatement}.
	 * @param ctx the parse tree
	 */
	void exitRangeStatement(GeneratedYangParser.RangeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#commonStatements}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatements(GeneratedYangParser.CommonStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#commonStatements}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatements(GeneratedYangParser.CommonStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#stringRestrictions}.
	 * @param ctx the parse tree
	 */
	void enterStringRestrictions(GeneratedYangParser.StringRestrictionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#stringRestrictions}.
	 * @param ctx the parse tree
	 */
	void exitStringRestrictions(GeneratedYangParser.StringRestrictionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#lengthStatement}.
	 * @param ctx the parse tree
	 */
	void enterLengthStatement(GeneratedYangParser.LengthStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#lengthStatement}.
	 * @param ctx the parse tree
	 */
	void exitLengthStatement(GeneratedYangParser.LengthStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#patternStatement}.
	 * @param ctx the parse tree
	 */
	void enterPatternStatement(GeneratedYangParser.PatternStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#patternStatement}.
	 * @param ctx the parse tree
	 */
	void exitPatternStatement(GeneratedYangParser.PatternStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#defaultStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultStatement(GeneratedYangParser.DefaultStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#defaultStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultStatement(GeneratedYangParser.DefaultStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#enumSpecification}.
	 * @param ctx the parse tree
	 */
	void enterEnumSpecification(GeneratedYangParser.EnumSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#enumSpecification}.
	 * @param ctx the parse tree
	 */
	void exitEnumSpecification(GeneratedYangParser.EnumSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#enumStatement}.
	 * @param ctx the parse tree
	 */
	void enterEnumStatement(GeneratedYangParser.EnumStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#enumStatement}.
	 * @param ctx the parse tree
	 */
	void exitEnumStatement(GeneratedYangParser.EnumStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#enumStatementBody}.
	 * @param ctx the parse tree
	 */
	void enterEnumStatementBody(GeneratedYangParser.EnumStatementBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#enumStatementBody}.
	 * @param ctx the parse tree
	 */
	void exitEnumStatementBody(GeneratedYangParser.EnumStatementBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#leafrefSpecification}.
	 * @param ctx the parse tree
	 */
	void enterLeafrefSpecification(GeneratedYangParser.LeafrefSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#leafrefSpecification}.
	 * @param ctx the parse tree
	 */
	void exitLeafrefSpecification(GeneratedYangParser.LeafrefSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#pathStatement}.
	 * @param ctx the parse tree
	 */
	void enterPathStatement(GeneratedYangParser.PathStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#pathStatement}.
	 * @param ctx the parse tree
	 */
	void exitPathStatement(GeneratedYangParser.PathStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#requireInstanceStatement}.
	 * @param ctx the parse tree
	 */
	void enterRequireInstanceStatement(GeneratedYangParser.RequireInstanceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#requireInstanceStatement}.
	 * @param ctx the parse tree
	 */
	void exitRequireInstanceStatement(GeneratedYangParser.RequireInstanceStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#instanceIdentifierSpecification}.
	 * @param ctx the parse tree
	 */
	void enterInstanceIdentifierSpecification(GeneratedYangParser.InstanceIdentifierSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#instanceIdentifierSpecification}.
	 * @param ctx the parse tree
	 */
	void exitInstanceIdentifierSpecification(GeneratedYangParser.InstanceIdentifierSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#identityrefSpecification}.
	 * @param ctx the parse tree
	 */
	void enterIdentityrefSpecification(GeneratedYangParser.IdentityrefSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#identityrefSpecification}.
	 * @param ctx the parse tree
	 */
	void exitIdentityrefSpecification(GeneratedYangParser.IdentityrefSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#unionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterUnionSpecification(GeneratedYangParser.UnionSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#unionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitUnionSpecification(GeneratedYangParser.UnionSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#bitsSpecification}.
	 * @param ctx the parse tree
	 */
	void enterBitsSpecification(GeneratedYangParser.BitsSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#bitsSpecification}.
	 * @param ctx the parse tree
	 */
	void exitBitsSpecification(GeneratedYangParser.BitsSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#bitStatement}.
	 * @param ctx the parse tree
	 */
	void enterBitStatement(GeneratedYangParser.BitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#bitStatement}.
	 * @param ctx the parse tree
	 */
	void exitBitStatement(GeneratedYangParser.BitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#bitBodyStatement}.
	 * @param ctx the parse tree
	 */
	void enterBitBodyStatement(GeneratedYangParser.BitBodyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#bitBodyStatement}.
	 * @param ctx the parse tree
	 */
	void exitBitBodyStatement(GeneratedYangParser.BitBodyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#positionStatement}.
	 * @param ctx the parse tree
	 */
	void enterPositionStatement(GeneratedYangParser.PositionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#positionStatement}.
	 * @param ctx the parse tree
	 */
	void exitPositionStatement(GeneratedYangParser.PositionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#statusStatement}.
	 * @param ctx the parse tree
	 */
	void enterStatusStatement(GeneratedYangParser.StatusStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#statusStatement}.
	 * @param ctx the parse tree
	 */
	void exitStatusStatement(GeneratedYangParser.StatusStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#configStatement}.
	 * @param ctx the parse tree
	 */
	void enterConfigStatement(GeneratedYangParser.ConfigStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#configStatement}.
	 * @param ctx the parse tree
	 */
	void exitConfigStatement(GeneratedYangParser.ConfigStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#mandatoryStatement}.
	 * @param ctx the parse tree
	 */
	void enterMandatoryStatement(GeneratedYangParser.MandatoryStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#mandatoryStatement}.
	 * @param ctx the parse tree
	 */
	void exitMandatoryStatement(GeneratedYangParser.MandatoryStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#presenceStatement}.
	 * @param ctx the parse tree
	 */
	void enterPresenceStatement(GeneratedYangParser.PresenceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#presenceStatement}.
	 * @param ctx the parse tree
	 */
	void exitPresenceStatement(GeneratedYangParser.PresenceStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#orderedByStatement}.
	 * @param ctx the parse tree
	 */
	void enterOrderedByStatement(GeneratedYangParser.OrderedByStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#orderedByStatement}.
	 * @param ctx the parse tree
	 */
	void exitOrderedByStatement(GeneratedYangParser.OrderedByStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#mustStatement}.
	 * @param ctx the parse tree
	 */
	void enterMustStatement(GeneratedYangParser.MustStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#mustStatement}.
	 * @param ctx the parse tree
	 */
	void exitMustStatement(GeneratedYangParser.MustStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#errorMessageStatement}.
	 * @param ctx the parse tree
	 */
	void enterErrorMessageStatement(GeneratedYangParser.ErrorMessageStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#errorMessageStatement}.
	 * @param ctx the parse tree
	 */
	void exitErrorMessageStatement(GeneratedYangParser.ErrorMessageStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#errorAppTagStatement}.
	 * @param ctx the parse tree
	 */
	void enterErrorAppTagStatement(GeneratedYangParser.ErrorAppTagStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#errorAppTagStatement}.
	 * @param ctx the parse tree
	 */
	void exitErrorAppTagStatement(GeneratedYangParser.ErrorAppTagStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#minElementsStatement}.
	 * @param ctx the parse tree
	 */
	void enterMinElementsStatement(GeneratedYangParser.MinElementsStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#minElementsStatement}.
	 * @param ctx the parse tree
	 */
	void exitMinElementsStatement(GeneratedYangParser.MinElementsStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#maxElementsStatement}.
	 * @param ctx the parse tree
	 */
	void enterMaxElementsStatement(GeneratedYangParser.MaxElementsStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#maxElementsStatement}.
	 * @param ctx the parse tree
	 */
	void exitMaxElementsStatement(GeneratedYangParser.MaxElementsStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#valueStatement}.
	 * @param ctx the parse tree
	 */
	void enterValueStatement(GeneratedYangParser.ValueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#valueStatement}.
	 * @param ctx the parse tree
	 */
	void exitValueStatement(GeneratedYangParser.ValueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#groupingStatement}.
	 * @param ctx the parse tree
	 */
	void enterGroupingStatement(GeneratedYangParser.GroupingStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#groupingStatement}.
	 * @param ctx the parse tree
	 */
	void exitGroupingStatement(GeneratedYangParser.GroupingStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#containerStatement}.
	 * @param ctx the parse tree
	 */
	void enterContainerStatement(GeneratedYangParser.ContainerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#containerStatement}.
	 * @param ctx the parse tree
	 */
	void exitContainerStatement(GeneratedYangParser.ContainerStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterLeafStatement(GeneratedYangParser.LeafStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitLeafStatement(GeneratedYangParser.LeafStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#leafListStatement}.
	 * @param ctx the parse tree
	 */
	void enterLeafListStatement(GeneratedYangParser.LeafListStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#leafListStatement}.
	 * @param ctx the parse tree
	 */
	void exitLeafListStatement(GeneratedYangParser.LeafListStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#listStatement}.
	 * @param ctx the parse tree
	 */
	void enterListStatement(GeneratedYangParser.ListStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#listStatement}.
	 * @param ctx the parse tree
	 */
	void exitListStatement(GeneratedYangParser.ListStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#keyStatement}.
	 * @param ctx the parse tree
	 */
	void enterKeyStatement(GeneratedYangParser.KeyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#keyStatement}.
	 * @param ctx the parse tree
	 */
	void exitKeyStatement(GeneratedYangParser.KeyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#uniqueStatement}.
	 * @param ctx the parse tree
	 */
	void enterUniqueStatement(GeneratedYangParser.UniqueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#uniqueStatement}.
	 * @param ctx the parse tree
	 */
	void exitUniqueStatement(GeneratedYangParser.UniqueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#choiceStatement}.
	 * @param ctx the parse tree
	 */
	void enterChoiceStatement(GeneratedYangParser.ChoiceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#choiceStatement}.
	 * @param ctx the parse tree
	 */
	void exitChoiceStatement(GeneratedYangParser.ChoiceStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#shortCaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterShortCaseStatement(GeneratedYangParser.ShortCaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#shortCaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitShortCaseStatement(GeneratedYangParser.ShortCaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(GeneratedYangParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(GeneratedYangParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#anyxmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterAnyxmlStatement(GeneratedYangParser.AnyxmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#anyxmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitAnyxmlStatement(GeneratedYangParser.AnyxmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#usesStatement}.
	 * @param ctx the parse tree
	 */
	void enterUsesStatement(GeneratedYangParser.UsesStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#usesStatement}.
	 * @param ctx the parse tree
	 */
	void exitUsesStatement(GeneratedYangParser.UsesStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refineStatement}.
	 * @param ctx the parse tree
	 */
	void enterRefineStatement(GeneratedYangParser.RefineStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refineStatement}.
	 * @param ctx the parse tree
	 */
	void exitRefineStatement(GeneratedYangParser.RefineStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refineContainerStatements}.
	 * @param ctx the parse tree
	 */
	void enterRefineContainerStatements(GeneratedYangParser.RefineContainerStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refineContainerStatements}.
	 * @param ctx the parse tree
	 */
	void exitRefineContainerStatements(GeneratedYangParser.RefineContainerStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refineLeafStatements}.
	 * @param ctx the parse tree
	 */
	void enterRefineLeafStatements(GeneratedYangParser.RefineLeafStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refineLeafStatements}.
	 * @param ctx the parse tree
	 */
	void exitRefineLeafStatements(GeneratedYangParser.RefineLeafStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refineLeafListStatements}.
	 * @param ctx the parse tree
	 */
	void enterRefineLeafListStatements(GeneratedYangParser.RefineLeafListStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refineLeafListStatements}.
	 * @param ctx the parse tree
	 */
	void exitRefineLeafListStatements(GeneratedYangParser.RefineLeafListStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refineListStatements}.
	 * @param ctx the parse tree
	 */
	void enterRefineListStatements(GeneratedYangParser.RefineListStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refineListStatements}.
	 * @param ctx the parse tree
	 */
	void exitRefineListStatements(GeneratedYangParser.RefineListStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refineChoiceStatements}.
	 * @param ctx the parse tree
	 */
	void enterRefineChoiceStatements(GeneratedYangParser.RefineChoiceStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refineChoiceStatements}.
	 * @param ctx the parse tree
	 */
	void exitRefineChoiceStatements(GeneratedYangParser.RefineChoiceStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refineCaseStatements}.
	 * @param ctx the parse tree
	 */
	void enterRefineCaseStatements(GeneratedYangParser.RefineCaseStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refineCaseStatements}.
	 * @param ctx the parse tree
	 */
	void exitRefineCaseStatements(GeneratedYangParser.RefineCaseStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refineAnyxmlStatements}.
	 * @param ctx the parse tree
	 */
	void enterRefineAnyxmlStatements(GeneratedYangParser.RefineAnyxmlStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refineAnyxmlStatements}.
	 * @param ctx the parse tree
	 */
	void exitRefineAnyxmlStatements(GeneratedYangParser.RefineAnyxmlStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#augmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAugmentStatement(GeneratedYangParser.AugmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#augmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAugmentStatement(GeneratedYangParser.AugmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#whenStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhenStatement(GeneratedYangParser.WhenStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#whenStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhenStatement(GeneratedYangParser.WhenStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#rpcStatement}.
	 * @param ctx the parse tree
	 */
	void enterRpcStatement(GeneratedYangParser.RpcStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#rpcStatement}.
	 * @param ctx the parse tree
	 */
	void exitRpcStatement(GeneratedYangParser.RpcStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#inputStatement}.
	 * @param ctx the parse tree
	 */
	void enterInputStatement(GeneratedYangParser.InputStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#inputStatement}.
	 * @param ctx the parse tree
	 */
	void exitInputStatement(GeneratedYangParser.InputStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#outputStatement}.
	 * @param ctx the parse tree
	 */
	void enterOutputStatement(GeneratedYangParser.OutputStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#outputStatement}.
	 * @param ctx the parse tree
	 */
	void exitOutputStatement(GeneratedYangParser.OutputStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#notificationStatement}.
	 * @param ctx the parse tree
	 */
	void enterNotificationStatement(GeneratedYangParser.NotificationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#notificationStatement}.
	 * @param ctx the parse tree
	 */
	void exitNotificationStatement(GeneratedYangParser.NotificationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#deviationStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeviationStatement(GeneratedYangParser.DeviationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#deviationStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeviationStatement(GeneratedYangParser.DeviationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#deviateNotSupportedStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeviateNotSupportedStatement(GeneratedYangParser.DeviateNotSupportedStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#deviateNotSupportedStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeviateNotSupportedStatement(GeneratedYangParser.DeviateNotSupportedStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#deviateAddStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeviateAddStatement(GeneratedYangParser.DeviateAddStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#deviateAddStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeviateAddStatement(GeneratedYangParser.DeviateAddStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#deviateDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeviateDeleteStatement(GeneratedYangParser.DeviateDeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#deviateDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeviateDeleteStatement(GeneratedYangParser.DeviateDeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#deviateReplaceStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeviateReplaceStatement(GeneratedYangParser.DeviateReplaceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#deviateReplaceStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeviateReplaceStatement(GeneratedYangParser.DeviateReplaceStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#compilerAnnotationStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompilerAnnotationStatement(GeneratedYangParser.CompilerAnnotationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#compilerAnnotationStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompilerAnnotationStatement(GeneratedYangParser.CompilerAnnotationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#compilerAnnotationBodyStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompilerAnnotationBodyStatement(GeneratedYangParser.CompilerAnnotationBodyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#compilerAnnotationBodyStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompilerAnnotationBodyStatement(GeneratedYangParser.CompilerAnnotationBodyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#appDataStructureStatement}.
	 * @param ctx the parse tree
	 */
	void enterAppDataStructureStatement(GeneratedYangParser.AppDataStructureStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#appDataStructureStatement}.
	 * @param ctx the parse tree
	 */
	void exitAppDataStructureStatement(GeneratedYangParser.AppDataStructureStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#dataStructureKeyStatement}.
	 * @param ctx the parse tree
	 */
	void enterDataStructureKeyStatement(GeneratedYangParser.DataStructureKeyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#dataStructureKeyStatement}.
	 * @param ctx the parse tree
	 */
	void exitDataStructureKeyStatement(GeneratedYangParser.DataStructureKeyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#appExtendedStatement}.
	 * @param ctx the parse tree
	 */
	void enterAppExtendedStatement(GeneratedYangParser.AppExtendedStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#appExtendedStatement}.
	 * @param ctx the parse tree
	 */
	void exitAppExtendedStatement(GeneratedYangParser.AppExtendedStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#defaultDenyWriteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultDenyWriteStatement(GeneratedYangParser.DefaultDenyWriteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#defaultDenyWriteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultDenyWriteStatement(GeneratedYangParser.DefaultDenyWriteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#defaultDenyAllStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultDenyAllStatement(GeneratedYangParser.DefaultDenyAllStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#defaultDenyAllStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultDenyAllStatement(GeneratedYangParser.DefaultDenyAllStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#anydataStatement}.
	 * @param ctx the parse tree
	 */
	void enterAnydataStatement(GeneratedYangParser.AnydataStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#anydataStatement}.
	 * @param ctx the parse tree
	 */
	void exitAnydataStatement(GeneratedYangParser.AnydataStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#unknownStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnknownStatement(GeneratedYangParser.UnknownStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#unknownStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnknownStatement(GeneratedYangParser.UnknownStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#unknownStatement2}.
	 * @param ctx the parse tree
	 */
	void enterUnknownStatement2(GeneratedYangParser.UnknownStatement2Context ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#unknownStatement2}.
	 * @param ctx the parse tree
	 */
	void exitUnknownStatement2(GeneratedYangParser.UnknownStatement2Context ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#yangStatement}.
	 * @param ctx the parse tree
	 */
	void enterYangStatement(GeneratedYangParser.YangStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#yangStatement}.
	 * @param ctx the parse tree
	 */
	void exitYangStatement(GeneratedYangParser.YangStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#stmtEnd}.
	 * @param ctx the parse tree
	 */
	void enterStmtEnd(GeneratedYangParser.StmtEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#stmtEnd}.
	 * @param ctx the parse tree
	 */
	void exitStmtEnd(GeneratedYangParser.StmtEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#stmtSep}.
	 * @param ctx the parse tree
	 */
	void enterStmtSep(GeneratedYangParser.StmtSepContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#stmtSep}.
	 * @param ctx the parse tree
	 */
	void exitStmtSep(GeneratedYangParser.StmtSepContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(GeneratedYangParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(GeneratedYangParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#unknown}.
	 * @param ctx the parse tree
	 */
	void enterUnknown(GeneratedYangParser.UnknownContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#unknown}.
	 * @param ctx the parse tree
	 */
	void exitUnknown(GeneratedYangParser.UnknownContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#unknown2}.
	 * @param ctx the parse tree
	 */
	void enterUnknown2(GeneratedYangParser.Unknown2Context ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#unknown2}.
	 * @param ctx the parse tree
	 */
	void exitUnknown2(GeneratedYangParser.Unknown2Context ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(GeneratedYangParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(GeneratedYangParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#dateArgumentString}.
	 * @param ctx the parse tree
	 */
	void enterDateArgumentString(GeneratedYangParser.DateArgumentStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#dateArgumentString}.
	 * @param ctx the parse tree
	 */
	void exitDateArgumentString(GeneratedYangParser.DateArgumentStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#version}.
	 * @param ctx the parse tree
	 */
	void enterVersion(GeneratedYangParser.VersionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#version}.
	 * @param ctx the parse tree
	 */
	void exitVersion(GeneratedYangParser.VersionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(GeneratedYangParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(GeneratedYangParser.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#length}.
	 * @param ctx the parse tree
	 */
	void enterLength(GeneratedYangParser.LengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#length}.
	 * @param ctx the parse tree
	 */
	void exitLength(GeneratedYangParser.LengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(GeneratedYangParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(GeneratedYangParser.PathContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#position}.
	 * @param ctx the parse tree
	 */
	void enterPosition(GeneratedYangParser.PositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#position}.
	 * @param ctx the parse tree
	 */
	void exitPosition(GeneratedYangParser.PositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#status}.
	 * @param ctx the parse tree
	 */
	void enterStatus(GeneratedYangParser.StatusContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#status}.
	 * @param ctx the parse tree
	 */
	void exitStatus(GeneratedYangParser.StatusContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#config}.
	 * @param ctx the parse tree
	 */
	void enterConfig(GeneratedYangParser.ConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#config}.
	 * @param ctx the parse tree
	 */
	void exitConfig(GeneratedYangParser.ConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#mandatory}.
	 * @param ctx the parse tree
	 */
	void enterMandatory(GeneratedYangParser.MandatoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#mandatory}.
	 * @param ctx the parse tree
	 */
	void exitMandatory(GeneratedYangParser.MandatoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#orderedBy}.
	 * @param ctx the parse tree
	 */
	void enterOrderedBy(GeneratedYangParser.OrderedByContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#orderedBy}.
	 * @param ctx the parse tree
	 */
	void exitOrderedBy(GeneratedYangParser.OrderedByContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#minValue}.
	 * @param ctx the parse tree
	 */
	void enterMinValue(GeneratedYangParser.MinValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#minValue}.
	 * @param ctx the parse tree
	 */
	void exitMinValue(GeneratedYangParser.MinValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#maxValue}.
	 * @param ctx the parse tree
	 */
	void enterMaxValue(GeneratedYangParser.MaxValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#maxValue}.
	 * @param ctx the parse tree
	 */
	void exitMaxValue(GeneratedYangParser.MaxValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(GeneratedYangParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(GeneratedYangParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#unique}.
	 * @param ctx the parse tree
	 */
	void enterUnique(GeneratedYangParser.UniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#unique}.
	 * @param ctx the parse tree
	 */
	void exitUnique(GeneratedYangParser.UniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#refine}.
	 * @param ctx the parse tree
	 */
	void enterRefine(GeneratedYangParser.RefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#refine}.
	 * @param ctx the parse tree
	 */
	void exitRefine(GeneratedYangParser.RefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#requireInstance}.
	 * @param ctx the parse tree
	 */
	void enterRequireInstance(GeneratedYangParser.RequireInstanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#requireInstance}.
	 * @param ctx the parse tree
	 */
	void exitRequireInstance(GeneratedYangParser.RequireInstanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#augment}.
	 * @param ctx the parse tree
	 */
	void enterAugment(GeneratedYangParser.AugmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#augment}.
	 * @param ctx the parse tree
	 */
	void exitAugment(GeneratedYangParser.AugmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#deviation}.
	 * @param ctx the parse tree
	 */
	void enterDeviation(GeneratedYangParser.DeviationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#deviation}.
	 * @param ctx the parse tree
	 */
	void exitDeviation(GeneratedYangParser.DeviationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(GeneratedYangParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(GeneratedYangParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#fraction}.
	 * @param ctx the parse tree
	 */
	void enterFraction(GeneratedYangParser.FractionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#fraction}.
	 * @param ctx the parse tree
	 */
	void exitFraction(GeneratedYangParser.FractionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#appDataStructure}.
	 * @param ctx the parse tree
	 */
	void enterAppDataStructure(GeneratedYangParser.AppDataStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#appDataStructure}.
	 * @param ctx the parse tree
	 */
	void exitAppDataStructure(GeneratedYangParser.AppDataStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#extendedName}.
	 * @param ctx the parse tree
	 */
	void enterExtendedName(GeneratedYangParser.ExtendedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#extendedName}.
	 * @param ctx the parse tree
	 */
	void exitExtendedName(GeneratedYangParser.ExtendedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedYangParser#yangConstruct}.
	 * @param ctx the parse tree
	 */
	void enterYangConstruct(GeneratedYangParser.YangConstructContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedYangParser#yangConstruct}.
	 * @param ctx the parse tree
	 */
	void exitYangConstruct(GeneratedYangParser.YangConstructContext ctx);
}