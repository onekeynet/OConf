/*
 * Copyright 2017-present Open Networking Foundation
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

package org.onosproject.yang.runtime;

import org.onosproject.yang.model.SchemaContext;

import java.util.List;

/*
 * Example usage of getProtocolAnnotations.
 * Reference 6241:
 * <rpc message-id="101"
 * xmlns="urn:ietf:params:xml:ns:netconf:base:1.0">
 * <edit-config>
 * <target>
 * <running/>
 * </target>
 * <config xmlns:xc="urn:ietf:params:xml:ns:netconf:base:1.0">
 * <top xmlns="http://example.com/schema/1.2/config">
 * <interface xc:operation="replace">
 * <name>Ethernet0/0</name>
 * <mtu>1500</mtu>
 * <address>
 * <name>192.0.2.4</name>
 * <prefix-length>24</prefix-length>
 * </address>
 * </interface>
 * </top>
 * </config>
 * </edit-config>
 * </rpc>
 * In above example annotation inside <config> is associated with protocol.
 * YANG related data has dependency on the same.
 */

/**
 * Representation of a context for decoding YANG models via serializers.
 */
public interface YangSerializerContext {

    /**
     * Returns schema context of root node "/".
     *
     * @return schema context provider
     */
    SchemaContext getContext();

    /**
     * Returns dependent annotations which are present as a part of protocol
     * specific information and YANG related data could have dependency on
     * the same.
     *
     * @return list of annotations
     */
    List<Annotation> getProtocolAnnotations();
}
