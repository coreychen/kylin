/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package org.apache.kylin.engine.flink;

import org.apache.flink.configuration.JobManagerOptions;
import org.apache.flink.configuration.TaskManagerOptions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Unit test for {@link FlinkOnYarnConfigMapping}.
 */
public class FlinkOnYarnConfigMappingTest {

    private static List<String> flinkOnYarnConfigOptionKeys;

    static {
        flinkOnYarnConfigOptionKeys = new ArrayList<>();
        flinkOnYarnConfigOptionKeys.add("-yjm");
        flinkOnYarnConfigOptionKeys.add("-ytm");
        flinkOnYarnConfigOptionKeys.add("-ys");
    }

    @Test
    public void testFlinkOnYarnJMMemOption() {
        String flinkonYarnJMMemOption = "-yjm";
        Map<String, String> map = FlinkOnYarnConfigMapping.flinkOnYarnConfigMap;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(flinkonYarnJMMemOption)) {
                String flinkConfigOption = entry.getKey();

                boolean matchedAnyOne;
                matchedAnyOne = flinkConfigOption.equals(JobManagerOptions.JOB_MANAGER_HEAP_MEMORY.key());
                if (!matchedAnyOne) {
                    if (JobManagerOptions.JOB_MANAGER_HEAP_MEMORY.hasDeprecatedKeys()) {
                        Iterator<String> deprecatedKeyIterator = JobManagerOptions.JOB_MANAGER_HEAP_MEMORY
                                .deprecatedKeys().iterator();
                        while (deprecatedKeyIterator.hasNext()) {
                            matchedAnyOne = matchedAnyOne && flinkConfigOption.equals(deprecatedKeyIterator.next());
                        }
                    }
                }

                Assert.assertTrue(matchedAnyOne);
            }
        }
    }

    @Test
    public void testFlinkOnYarnTMMemOption() {
        String flinkonYarnTMMemOption = "-ytm";
        Map<String, String> map = FlinkOnYarnConfigMapping.flinkOnYarnConfigMap;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(flinkonYarnTMMemOption)) {
                String flinkConfigOption = entry.getKey();

                boolean matchedAnyOne;
                matchedAnyOne = flinkConfigOption.equals(TaskManagerOptions.TASK_MANAGER_HEAP_MEMORY.key());
                if (!matchedAnyOne) {
                    if (TaskManagerOptions.TASK_MANAGER_HEAP_MEMORY.hasDeprecatedKeys()) {
                        Iterator<String> deprecatedKeyIterator = TaskManagerOptions.TASK_MANAGER_HEAP_MEMORY
                                .deprecatedKeys().iterator();
                        while (deprecatedKeyIterator.hasNext()) {
                            matchedAnyOne = matchedAnyOne && flinkConfigOption.equals(deprecatedKeyIterator.next());
                        }
                    }
                }

                Assert.assertTrue(matchedAnyOne);
            }
        }
    }

    @Test
    public void testFlinkOnYarnTMSlotOption() {
        String flinkonYarnTMSlotOption = "-ys";
        Map<String, String> map = FlinkOnYarnConfigMapping.flinkOnYarnConfigMap;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(flinkonYarnTMSlotOption)) {
                String flinkConfigOption = entry.getKey();

                boolean matchedAnyOne;
                matchedAnyOne = flinkConfigOption.equals(TaskManagerOptions.NUM_TASK_SLOTS.key());
                if (!matchedAnyOne) {
                    if (TaskManagerOptions.NUM_TASK_SLOTS.hasDeprecatedKeys()) {
                        Iterator<String> deprecatedKeyIterator = TaskManagerOptions.NUM_TASK_SLOTS
                                .deprecatedKeys().iterator();
                        while (deprecatedKeyIterator.hasNext()) {
                            matchedAnyOne = matchedAnyOne && flinkConfigOption.equals(deprecatedKeyIterator.next());
                        }
                    }
                }

                Assert.assertTrue(matchedAnyOne);
            }
        }
    }

    @Test
    public void testFlinkOnYarnConfigOptionKeySet() {
        for (String flinkOnYarnCnfigOptionKey : FlinkOnYarnConfigMapping.flinkOnYarnConfigMap.values()) {
            Assert.assertTrue(flinkOnYarnConfigOptionKeys.contains(flinkOnYarnCnfigOptionKey));
        }
    }

}