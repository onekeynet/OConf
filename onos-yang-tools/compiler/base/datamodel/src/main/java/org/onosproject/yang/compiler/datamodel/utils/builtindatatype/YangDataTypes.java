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

package org.onosproject.yang.compiler.datamodel.utils.builtindatatype;

/**
 * Represents ENUM to identify the YANG data type.
 */
public enum YangDataTypes {
    /**
     * Reference:RFC 6020.
     * <p>
     * int8 represents integer values between -128 and 127, inclusively.
     */
    INT8("int8"),

    /**
     * Reference:RFC 6020.
     * <p>
     * int16 represents integer values between -32768 and 32767, inclusively.
     */
    INT16("int16"),

    /**
     * Reference:RFC 6020.
     * <p>
     * int32 represents integer values between -2147483648 and 2147483647,
     * inclusively.
     */
    INT32("int32"),

    /**
     * Reference:RFC 6020.
     * <p>
     * int64 represents integer values between -9223372036854775808 and
     * 9223372036854775807, inclusively.
     */
    INT64("int64"),

    /**
     * Reference:RFC 6020.
     * <p>
     * uint8 represents integer values between 0 and 255, inclusively.
     */
    UINT8("uint8"),

    /**
     * Reference:RFC 6020.
     * <p>
     * uint16 represents integer values between 0 and 65535, inclusively.
     */
    UINT16("uint16"),

    /**
     * Reference:RFC 6020.
     * <p>
     * uint32 represents integer values between 0 and 4294967295, inclusively.
     */
    UINT32("uint32"),

    /**
     * Reference:RFC 6020.
     * <p>
     * uint64 represents integer values between 0 and 18446744073709551615,
     * inclusively.
     */
    UINT64("uint64"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The decimal64 type represents a subset of the real numbers, which can be
     * represented by decimal numerals. The value space of decimal64 is the set
     * of numbers that can be obtained by multiplying a 64-bit signed integer by
     * a negative power of ten, i.e., expressible as "i x 10^-n" where i is an
     * integer64 and n is an integer between 1 and 18, inclusively.
     */
    DECIMAL64("decimal64"), // TODO: need to implement in type.

    /**
     * Reference:RFC 6020.
     * <p>
     * The string built-in type represents human-readable strings in YANG. Legal
     * characters are tab, carriage return, line feed, and the legal characters
     * of Unicode and ISO/IEC 10646
     */
    STRING("string"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The boolean built-in type represents a boolean value.
     */
    BOOLEAN("boolean"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The enumeration built-in type represents values from a set of assigned
     * names.
     */
    ENUMERATION("enumeration"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The bits built-in type represents a bit set. That is, a bits value is a
     * set of flags identified by small integer position numbers starting at 0.
     * Each bit number has an assigned name.
     */
    BITS("bits"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The binary built-in type represents any binary data, i.e., a sequence of
     * octets.
     */
    BINARY("binary"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The leafref type is used to reference a particular leaf instance in the
     * data tree. The "path" sub-statement (Section 9.9.2) selects a set of leaf
     * instances, and the leafref value space is the set of values of these leaf
     * instances.
     * <p>
     * If the leaf with the leafref type represents configuration data, the leaf
     * it refers to MUST also represent configuration. Such a leaf puts a
     * constraint on valid data. All leafref nodes MUST reference existing leaf
     * instances or leafs with default values in use for the data to be valid.
     * <p>
     * There MUST NOT be any circular chains of leafrefs.
     * <p>
     * If the leaf that the leafref refers to is conditional based on one or
     * more features, then the leaf with the leafref type MUST also be
     * conditional based on at least the same set of features.
     */
    LEAFREF("leafref"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The identityref type is used to reference an existing identity.
     */
    IDENTITYREF("identityref"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The empty built-in type represents a leaf that does not have any value,
     * it conveys information by its presence or absence.
     * <p>
     * An empty type cannot have a default value.
     */
    EMPTY("empty"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The union built-in type represents a value that corresponds to one of its
     * member types.
     * <p>
     * When the type is "union", the "type" statement MUST be present. It is
     * used to repeatedly specify each member type of the union. It takes as an
     * argument a string that is the name of a member type.
     * <p>
     * A member type can be of any built-in or derived type, except it MUST NOT
     * be one of the built-in types "empty" or "leafref".
     * <p>
     * When a string representing a union data type is validated, the string is
     * validated against each member type, in the order they are specified in
     * the "type" statement, until a match is found.
     * <p>
     * Any default value or "units" property defined in the member types is not
     * inherited by the union type.
     */
    UNION("union"),

    /**
     * Reference:RFC 6020.
     * <p>
     * The instance-identifier built-in type is used to uniquely identify a
     * particular instance node in the data tree.
     * <p>
     * The syntax for an instance-identifier is a subset of the XPath
     * abbreviated syntax, formally defined by the rule "instance-identifier".
     * It is used to uniquely identify a node in the data tree. Predicates are
     * used only for specifying the values for the key nodes for list entries, a
     * value of a leaf-list entry, or a positional index for a list without
     * keys. For identifying list entries with keys, each predicate consists of
     * one equality test per key, and each key MUST have a corresponding
     * predicate.
     * <p>
     * If the leaf with the instance-identifier type represents configuration
     * data, and the "require-instance" property is "true", the node it refers
     * to MUST also represent configuration. Such a leaf puts a constraint on
     * valid data. All such leaf nodes MUST reference existing nodes or leaf
     * nodes with their default value in use for the data to be valid.
     */
    INSTANCE_IDENTIFIER("instance-identifier"),

    /**
     * Derived data type.
     */
    DERIVED("derived");

    /**
     * Defined type from the enum value.
     */
    private final String definedType;

    /**
     * Constructs type value from enum.
     *
     * @param definedType value of enum
     */
    YangDataTypes(String definedType) {
        this.definedType = definedType;
    }

    /**
     * Returns YANG data type for corresponding type name.
     *
     * @param name type name from YANG file.
     * @return YANG data type for corresponding type name.
     */
    public static YangDataTypes getType(String name) {
        name = name.replace("\"", "");
        for (YangDataTypes yangDataType : values()) {
            if (yangDataType.definedType.toLowerCase().equals(name)) {
                return yangDataType;
            }
        }
        return YangDataTypes.DERIVED;
    }

    /**
     * Returns whether the data type is of primitive data type.
     *
     * @return true, if data type can have primitive data type, false otherwise
     */
    public boolean isPrimitiveDataType() {
        return this == INT8 ||
                this == INT16 ||
                this == INT32 ||
                this == INT64 ||
                this == UINT8 ||
                this == UINT16 ||
                this == UINT32 ||
                this == UINT64 ||
                this == DECIMAL64 ||
                this == BOOLEAN ||
                this == EMPTY;
    }

    /**
     * Returns whether the data type is of non restricted type.
     *
     * @return true, if data type can't be restricted, false otherwise
     */
    public boolean isNonRestrictedType() {
        return this == BOOLEAN ||
                this == ENUMERATION ||
                this == BITS ||
                this == EMPTY ||
                this == UNION ||
                this == IDENTITYREF ||
                this == LEAFREF;
    }
}
