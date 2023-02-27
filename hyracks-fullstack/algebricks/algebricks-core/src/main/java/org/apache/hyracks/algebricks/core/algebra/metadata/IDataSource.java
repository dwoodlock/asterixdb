/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.hyracks.algebricks.core.algebra.metadata;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.hyracks.algebricks.core.algebra.base.LogicalVariable;
import org.apache.hyracks.algebricks.core.algebra.properties.FunctionalDependency;
import org.apache.hyracks.algebricks.core.algebra.properties.INodeDomain;

public interface IDataSource<T> {
    public T getId();

    public Object[] getSchemaTypes();

    public IDataSourcePropertiesProvider getPropertiesProvider();

    public void computeFDs(List<LogicalVariable> scanVariables, List<FunctionalDependency> fdList);

    // https://issues.apache.org/jira/browse/ASTERIXDB-1619

    /**
     * Return true if this data source is the start of the job pipeline making its scan op the start of the job pipeline
     * instead of an ETS op, for example. This flag is used to disable the Hyracks op generation of the input
     * operators to the data scan (i.e. ETS op that is an input to the data scan will not be generated).
     */
    public boolean isScanAccessPathALeaf();

    public INodeDomain getDomain();

    public Map<String, Serializable> getProperties();

    default boolean compareProperties() {
        return false;
    }
}
