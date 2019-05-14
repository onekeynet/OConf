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
package org.onosproject.d.config;

import org.onosproject.yang.model.ListKey;
import org.onosproject.yang.model.NodeKey;
import org.onosproject.yang.model.ResourceId;
import org.onosproject.yang.model.SchemaId;

import com.google.common.annotations.Beta;

/**
 * Utility related to device ResourceId.
 */
@Beta
public abstract class DeviceResourceIds {

    // assuming following device tree structure
    // - "/"
    //    +- devices
    //         +- device (=device root node:ListKey)


    /**
     * SchemaId namespace for DCS defined nodes.
     */
    public static final String DCS_NAMESPACE = "org.onosproject.dcs";

    // FIXME '/' is problematic name from RFC 7950/7951 point of view
    /**
     * SchemaId name for root node.
     */
    public static final String ROOT_NAME = "/";
    /**
     * SchemaId name for devices node.
     */
    public static final String DEVICES_NAME = "devices";
    public static final SchemaId DEVICES_SCHEMA = new SchemaId(DEVICES_NAME, DCS_NAMESPACE);
    /**
     * SchemaId name for device node.
     */
    public static final String DEVICE_NAME = "device";
    public static final SchemaId DEVICE_SCHEMA = new SchemaId(DEVICE_NAME, DCS_NAMESPACE);
    /**
     * KeyLeaf {@code name}, which holds DeviceId information on device node.
     */
    public static final String DEVICE_ID_KL_NAME = "device-id";

    /**
     * Absolute ResourceId pointing at 'devices' node.
     */
    public static final ResourceId DEVICES_ID = ResourceId.builder()
            .addBranchPointSchema(ROOT_NAME, DCS_NAMESPACE)
            .addBranchPointSchema(DEVICES_NAME, DCS_NAMESPACE)
            .build();


    static final NodeKey<?> ROOT_NODE =
            NodeKey.builder().schemaId(ROOT_NAME, DCS_NAMESPACE).build();

    /**
     * nodeKeys index for root node.
     */
    private static final int ROOT_INDEX = 0;
    /**
     * nodeKeys index relative from root for devices node.
     */
    private static final int DEVICES_INDEX = 1;
    /**
     * nodeKeys index relative from root for device node.
     */
    private static final int DEVICE_INDEX = 2;

    /**
     * Converts root relative ResourceId used by DynamicConfigEvent.
     *
     * @param rootRelative resource Id
     * @return absolute ResourceId.
     */
    @Beta
    public static ResourceId toAbsolute(ResourceId rootRelative) {
        if (ResourceIds.startsWithRootNode(rootRelative)) {
            return rootRelative;
        }
        return ResourceIds.concat(ResourceIds.ROOT_ID, rootRelative);
    }

    /**
     * Tests if specified path points to root node of a Device.
     *
     * @param path to test.
     * @return true if path points to root node of a Device.
     */
    public static boolean isDeviceRootNode(ResourceId path) {
        if (ResourceIds.startsWithRootNode(path)) {
            return path.nodeKeys().size() == 3 &&
                    isUnderDeviceRootNode(path);
        } else {
            return path.nodeKeys().size() == 2 &&
                    isUnderDeviceRootNode(path);
        }
    }

    /**
     * Tests if specified path points to root node of a Device or it's subtree.
     *
     * @param path to test.
     * @return true if path points to root node of a Device.
     */
    public static boolean isUnderDeviceRootNode(ResourceId path) {
        int rootIdx = ResourceIds.startsWithRootNode(path) ? 0 : -1;
        return path.nodeKeys().size() >= rootIdx + 3 &&
                DEVICE_SCHEMA.equals(path.nodeKeys().get(rootIdx + DEVICE_INDEX).schemaId()) &&
                (path.nodeKeys().get(rootIdx + DEVICE_INDEX) instanceof ListKey) &&
                DEVICES_SCHEMA.equals(path.nodeKeys().get(rootIdx + DEVICES_INDEX).schemaId());    }

    /**
     * Tests if specified path points to root or devices node.
     *
     * @param path to test.
     * @return true if path points to root node of a Device.
     */
    public static boolean isRootOrDevicesNode(ResourceId path) {
        return isDevicesNode(path) ||
                isRootNode(path);
    }

    public static boolean isDevicesNode(ResourceId path) {
        int rootIdx = ResourceIds.startsWithRootNode(path) ? 0 : -1;
        return path.nodeKeys().size() == rootIdx + 2 &&
                DEVICES_SCHEMA.equals(path.nodeKeys().get(rootIdx + DEVICES_INDEX).schemaId());
    }

    public static boolean isRootNode(ResourceId path) {
        return path.nodeKeys().size() == 1 &&
                ROOT_NODE.equals(path.nodeKeys().get(ROOT_INDEX));
    }

}
