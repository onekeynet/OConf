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

package org.onosproject.yang.compiler.translator.tojava;

import org.onosproject.yang.compiler.datamodel.javadatamodel.JavaFileInfo;
import org.onosproject.yang.compiler.utils.io.YangPluginConfig;

/**
 * Represents cached java file handle, which supports the addition of member attributes and
 * methods.
 */
public class JavaFileInfoTranslator extends JavaFileInfo {

    private static final long serialVersionUID = 806102633L;

    /**
     * The type(s) of java source file(s) to be generated when the cached file
     * handle is closed.
     */
    private transient int genFileTypes;

    /**
     * File generation directory path.
     */
    private transient String relativeFilePath;

    /**
     * File generation base directory path.
     */
    private transient String codeGenDirFilePath;

    /**
     * Plugin configuration for naming convention.
     */
    private transient YangPluginConfig pluginConfig;

    /**
     * Returns the types of files being generated corresponding to the YANG
     * definition.
     *
     * @return the types of files being generated corresponding to the YANG
     * definition
     */
    public int getGeneratedFileTypes() {
        return genFileTypes;
    }

    /**
     * Sets the types of files being generated corresponding to the YANG
     * definition.
     *
     * @param fileTypes the types of files being generated corresponding to the
     *                  YANG definition
     */
    public void setGeneratedFileTypes(int fileTypes) {
        genFileTypes = fileTypes;
    }

    /**
     * Adds the types of files being generated corresponding to the YANG
     * definition.
     *
     * @param fileTypes the types of files being generated corresponding to the
     *                  YANG definition
     */
    public void addGeneratedFileTypes(int fileTypes) {
        genFileTypes |= fileTypes;
    }

    /**
     * Sets directory package path for code generation.
     *
     * @param path directory package path for code generation
     */
    public void setPackageFilePath(String path) {
        relativeFilePath = path;
    }

    /**
     * Returns directory package path for code generation.
     *
     * @return directory package path for code generation
     */
    public String getPackageFilePath() {
        return relativeFilePath;
    }

    /**
     * Returns base directory package path for code generation.
     *
     * @return directory package path for code generation
     */
    public String getBaseCodeGenPath() {
        return codeGenDirFilePath;
    }

    /**
     * Sets base directory package path for code generation.
     *
     * @param path base directory path
     */
    public void setBaseCodeGenPath(String path) {
        codeGenDirFilePath = path;
    }

    /**
     * Returns plugin configurations.
     *
     * @return the pluginConfig
     */
    public YangPluginConfig getPluginConfig() {
        return pluginConfig;
    }

    /**
     * Sets plugin configurations.
     *
     * @param pluginConfig the pluginConfig to set
     */
    public void setPluginConfig(YangPluginConfig pluginConfig) {
        this.pluginConfig = pluginConfig;
    }
}
