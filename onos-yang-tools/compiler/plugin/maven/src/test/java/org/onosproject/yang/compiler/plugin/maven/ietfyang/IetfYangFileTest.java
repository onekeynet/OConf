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

package org.onosproject.yang.compiler.plugin.maven.ietfyang;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;
import org.onosproject.yang.compiler.parser.exceptions.ParserException;
import org.onosproject.yang.compiler.tool.YangCompilerManager;
import org.onosproject.yang.compiler.utils.io.YangPluginConfig;
import org.onosproject.yang.compiler.utils.io.impl.YangIoUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.onosproject.yang.compiler.utils.io.impl.YangFileScanner.getYangFiles;

/**
 * Test cases for testing IETF YANG files.
 */
public class IetfYangFileTest {

    private final YangCompilerManager utilManager = new YangCompilerManager();

    /**
     * Checks hierarchical intra with inter file type linking.
     * Reference: https://datatracker.ietf.org/doc/draft-zha-l3sm-l3vpn-onos-deployment
     */
    @Test
    public void l3vpnserviceyang()
            throws IOException, ParserException, MojoExecutionException {

        String dir = "target/ietfyang/l3vpnservice/";
        YangIoUtils.deleteDirectory(dir);
        String searchDir = "src/test/resources/ietfyang/l3vpnservice";
        Set<Path> paths = new HashSet<>();
        for (String file : getYangFiles(searchDir)) {
            paths.add(Paths.get(file));
        }

        utilManager.createYangFileInfoSet(paths);
        utilManager.parseYangFileInfoSet();
        utilManager.createYangNodeSet();
        utilManager.resolveDependenciesUsingLinker();

        YangPluginConfig yangPluginConfig = new YangPluginConfig();
        yangPluginConfig.setCodeGenDir(dir);

        utilManager.translateToJava(yangPluginConfig);
        String dir1 = System.getProperty("user.dir") + File.separator + dir;
        YangPluginConfig.compileCode(dir1);
        YangIoUtils.deleteDirectory("target/ietfyang/");
    }
}
