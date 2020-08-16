/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.compileflow.engine.process.preruntime.generator.impl;

import com.alibaba.compileflow.engine.definition.common.Node;
import com.alibaba.compileflow.engine.definition.common.action.HasInOutAction;
import com.alibaba.compileflow.engine.definition.common.action.IAction;
import com.alibaba.compileflow.engine.process.preruntime.generator.code.CodeTargetSupport;
import com.alibaba.compileflow.engine.runtime.impl.AbstractProcessRuntime;

/**
 * @author yusu
 */
public abstract class AbstractInOutActionNodeGenerator<N extends Node>
    extends AbstractActionNodeGenerator<N> {

    public AbstractInOutActionNodeGenerator(AbstractProcessRuntime runtime, N flowNode) {
        super(runtime, flowNode);
    }

    @Override
    public void generateCode(CodeTargetSupport codeTargetSupport) {
        HasInOutAction hasInOutAction = (HasInOutAction)flowNode;
        codeTargetSupport.addBodyLine("if (trigger) {");

        IAction outAction = hasInOutAction.getOutAction();
        generateActionMethodCode(codeTargetSupport, outAction);

        codeTargetSupport.addBodyLine("} else {");

        IAction inAction = hasInOutAction.getInAction();
        generateActionMethodCode(codeTargetSupport, inAction);
        codeTargetSupport.addBodyLine("running = false;");

        codeTargetSupport.addBodyLine("}");
    }

}