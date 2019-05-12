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

package org.onosproject.yang.runtime.helperutils;

import static org.onosproject.yang.compiler.tool.YangCompilerManager.parseJarFile;
import static org.onosproject.yang.compiler.tool.YangCompilerManager.processYangModel;
import static org.onosproject.yang.compiler.tool.YangCompilerManager.setNodeInfo;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.onosproject.yang.compiler.tool.YangNodeInfo;
import org.onosproject.yang.model.YangModel;
import org.slf4j.Logger;

/**
 * Utilities for runtime to use apache tools.
 */
public final class YangApacheUtils {

    private static final String SLASH = File.separator;
    private static final String HYPHEN = "-";
    private static final String PERIOD = ".";
    private static final String YANG_RESOURCES = "yang/resources";
    private static final String SYSTEM = SLASH + "system" + SLASH;
    private static final String MAVEN = "mvn:";
    private static final String JAR = ".jar";
    private static final String USER_DIRECTORY = "user.dir";
    private static final Logger log = getLogger(YangApacheUtils.class);

    // Forbid construction.
    private YangApacheUtils() {
    }

    /**
     * Returns YANG model for generated module class.
     *
     * @param modClass generated module class
     * @return YANG model
     */
    public static YangModel getYangModel(Class<?> modClass) {
        // TODO Ugly implementation for reading yang files and related ser files.
        String jarPath = modClass.getResource("/odtn-oc/openconfig-types.yang").getFile()
                .split(":")[1].split("!")[0].split("\\.jar")[0];
        log.info("the JAR path is : \n\t{}", jarPath);
        String metaPath = jarPath + SLASH + YANG_RESOURCES + SLASH;
        List<YangNodeInfo> nodeInfo = new ArrayList<>();
        YangModel model = processJarParsingOperations(jarPath);
        if (model != null) {
            setNodeInfo(model, nodeInfo);
            // process model creations.
            if (!nodeInfo.isEmpty()) {
                return processYangModel(metaPath, nodeInfo,
                        model.getYangModelId(), false);
            } else {
                log.warn("node info is empty!");
            }
        } else {
            log.warn("model is null!");
        }
        return null;
    }

    /**
     * Process jar file for fetching YANG model.
     *
     * @param path jar file path
     * @return YANG model
     */
    private static YangModel processJarParsingOperations(String path) {
        //Deserialize data model and get the YANG node set.
        String jar = path + JAR;
        try {
            File file = new File(jar);
            if (file.exists()) {
                return parseJarFile(path + JAR, path);
            }
        } catch (IOException e) {
            log.error(" failed to parse the jar file in path {} : {} ", path,
                    e.getMessage());
        }
        return null;
    }
}