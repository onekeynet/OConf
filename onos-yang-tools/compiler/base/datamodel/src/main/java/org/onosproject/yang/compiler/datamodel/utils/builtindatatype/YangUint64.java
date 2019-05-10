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
import java.math.BigInteger;

/**
 * Handles the YANG's Uint16 data type processing.
 *
 * Uint64 represents integer values between 0 and 18446744073709551615, inclusively.
 */
public class YangUint64 extends DefaultLocationInfo
        implements YangBuiltInDataTypeInfo<YangUint64>, Serializable {

    private static final long serialVersionUID = 8006201661L;

    /**
     * YANG's min keyword.
     */
    private static final String MIN_KEYWORD = "min";

    /**
     * YANG's max keyword.
     */
    private static final String MAX_KEYWORD = "max";

    /**
     * Valid minimum value of YANG's Uint64.
     */
    public static final BigInteger MIN_VALUE = BigInteger.valueOf(0);

    /**
     * Valid maximum value of YANG's Uint64.
     */
    public static final BigInteger MAX_VALUE = new BigInteger("18446744073709551615");

    /**
     * Value of the object.
     */
    private BigInteger value;

    /**
     * Creates an object with the value initialized with value represented in
     * string.
     *
     * @param valueInString value of the object in string
     */
    public YangUint64(String valueInString) {

        if (valueInString.matches(MIN_KEYWORD)) {
            value = MIN_VALUE;
        } else if (valueInString.matches(MAX_KEYWORD)) {
            value = MAX_VALUE;
        } else {
            try {
                value = new BigInteger(valueInString);
            } catch (Exception e) {
                throw new DataTypeException("YANG file error : Input value \"" + valueInString + "\" is not a valid " +
                                                    "uint64.");
            }
        }

        if (value.compareTo(MIN_VALUE) < 0) {
            throw new DataTypeException("YANG file error : " + valueInString + " is lesser than minimum value "
                    + MIN_VALUE + ".");
        } else if (value.compareTo(MAX_VALUE) > 0) {
            throw new DataTypeException("YANG file error : " + valueInString + " is greater than maximum value "
                    + MAX_VALUE + ".");
        }
    }

    /**
     * Returns YANG's uint64 value.
     *
     * @return value of YANG's uint64
     */
    public BigInteger getValue() {
        return value;
    }

    @Override
    public int compareTo(YangUint64 another) {
        return value.compareTo(another.value);
    }

    @Override
    public YangDataTypes getYangType() {
        return YangDataTypes.UINT64;
    }
}
