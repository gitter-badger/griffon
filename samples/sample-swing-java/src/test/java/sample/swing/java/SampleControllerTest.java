/*
 * Copyright 2008-2015 the original author or authors.
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
package sample.swing.java;

import griffon.core.artifact.ArtifactManager;
import griffon.core.test.GriffonUnitRule;
import griffon.core.test.TestFor;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.fieldIn;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

@TestFor(SampleController.class)
public class SampleControllerTest {
    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
    }

    @Inject
    private ArtifactManager artifactManager;

    private SampleController controller;

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule();

    @Test
    public void executeSayHelloActionWithNoInput() {
        SampleModel model = artifactManager.newInstance(SampleModel.class);

        controller.setModel(model);
        controller.invokeAction("sayHello");

        await().atMost(2, SECONDS)
            .until(fieldIn(model)
                .ofType(String.class)
                .andWithName("output"),
                notNullValue());
        assertEquals("Howdy stranger!", model.getOutput());
    }

    @Test
    public void executeSayHelloActionWithInput() {
        SampleModel model = artifactManager.newInstance(SampleModel.class);
        model.setInput("Griffon");

        controller.setModel(model);
        controller.invokeAction("sayHello");

        await().atMost(2, SECONDS)
            .until(fieldIn(model)
                .ofType(String.class)
                .andWithName("output"),
                notNullValue());
        assertEquals("Hello Griffon", model.getOutput());
    }
}
