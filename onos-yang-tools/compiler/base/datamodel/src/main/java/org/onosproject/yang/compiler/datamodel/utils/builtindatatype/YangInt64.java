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

import org.onosproject.yang.compiler.datamodel.DefaultLocationInfo;

import java.io.Serializable;

import static java.lang.Long.compare;
import static java.lang.Long.parseLong;
import static org.onosproject.yang.compiler.datamodel.utils.builtindatatype.YangDataTypes.INT64;

/**
 * Handles the YANG's int8 data type processing.
 *
 * int8 represents integer values between -9223372036854775808 and 9223372036854775807, inclusively.
 */
public class YangInt64 extends DefaultLocationInfo
        implements YangBuiltInDataTypeInfo<YangInt64>, Serializable {

    private static final long serialVersionUID = 8006201665L;

    /**
     * YANG's min keyword.
     */
    private static final String MIN_KEYWORD = "min";

    /**
     * YANG's max keyword.
     */
    private static final String MAX_KEYWORD = "max";

    /**
     * Valid minimum value of YANG's int64.
     */
    public static final Long MIN_VALUE = 0x8000000000000000L;

    /**
     * Valid maximum value of YANG's int64.
     */
    public static final long MAX_VALUE = 0x7fffffffffffffffL;

    /**
     * The value of YANG's int64.
     */
    private final long value;

    /**
     * Creates an object with the value initialized with value represented in
     * string.
     *
     * @param valueInString value of the object in string
     */
    public YangInt64(String valueInString) {

        if (valueInString.matches(MIN_KEYWORD)) {
            value = MIN_VALUE;
        } else if (valueInString.matches(MAX_KEYWORD)) {
            value = MAX_VALUE;
        } else {
            try {
                value = parseLong(valueInString);
            } catch (Exception e) {
                throw new DataTypeException("YANG file error : Input value \"" + valueInString + "\" is not a valid " +
                        "int64.");
            }
        }
    }

    /**
     * Returns YANG's int64 value.
     *
     * @return value of YANG's int64
     */
    public long getValue() {
        return value;
    }

    @Override
    public int compareTo(YangInt64 anotherYangInt64) {
        return compare(value, anotherYangInt64.value);
    }

    @Override
    public YangDataTypes getYangType() {
        return INT64;
    }
}
