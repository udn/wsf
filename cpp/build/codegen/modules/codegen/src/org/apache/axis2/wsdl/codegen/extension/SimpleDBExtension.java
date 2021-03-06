/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.axis2.wsdl.codegen.extension;


import org.apache.axis2.wsdl.codegen.CodeGenConfiguration;

import java.lang.reflect.Method;

/** Extension for simple data binding. */
public class SimpleDBExtension extends AbstractDBProcessingExtension {

    private static final String ADB_INVOKE_CLASS_NAME =
            "org.apache.axis2.schema.ExtensionUtility";
    private static final String INVOKE_METHOD_NAME =
            "invoke";

    /**
     * 
     */
    public void engage(CodeGenConfiguration configuration) {
        //test the databinding type. If not just fall through
        if (testFallThrough(configuration.getDatabindingType())) {
            return;
        }
        try {
            //invoke the adb codegen by reflection
            Class adbGeneratorClass = Class.
                    forName(ADB_INVOKE_CLASS_NAME);
            Method invokeMethod = adbGeneratorClass.getMethod(
                    INVOKE_METHOD_NAME,
                    new Class[] { CodeGenConfiguration.class });
            invokeMethod.invoke(null, new Object[] { configuration });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
