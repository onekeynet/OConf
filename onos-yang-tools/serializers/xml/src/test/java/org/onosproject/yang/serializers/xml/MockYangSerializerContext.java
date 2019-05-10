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

package org.onosproject.yang.serializers.xml;

import org.onosproject.yang.model.SchemaContext;
import org.onosproject.yang.runtime.Annotation;
import org.onosproject.yang.runtime.DefaultAnnotation;
import org.onosproject.yang.runtime.YangSerializerContext;

import java.util.LinkedList;
import java.util.List;

import static org.onosproject.yang.runtime.impl.MockYangSchemaNodeProvider.processSchemaRegistry;
import static org.onosproject.yang.runtime.impl.MockYangSchemaNodeProvider.registry;

/**
 * Tests the default schema context provider methods.
 */
public class MockYangSerializerContext implements YangSerializerContext {

    private static final String NETCONF_NS =
            "urn:ietf:params:xml:ns:netconf:base:1.0";
    private static final String XMNLS_NC = "xmlns:xc";


    @Override
    public SchemaContext getContext() {
        processSchemaRegistry();
        return registry();
    }

    @Override
    public List<Annotation> getProtocolAnnotations() {
        Annotation annotation = new DefaultAnnotation(XMNLS_NC, NETCONF_NS);
        List<Annotation> protocolAnnotation = new LinkedList<>();
        protocolAnnotation.add(annotation);
        return protocolAnnotation;
    }
}
