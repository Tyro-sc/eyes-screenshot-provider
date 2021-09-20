/*
 * Copyright © 2021 Ovea (d.avenante@gmail.com)
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
package sc.tyro.provider

import com.applitools.eyes.selenium.Eyes
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import sc.tyro.core.component.Component

import static org.mockito.Mockito.*
import static sc.tyro.core.Config.setScreenshotProvider
import static sc.tyro.core.Tyro.takeScreenshot

@DisplayName("Screenshot Tests")
class EyesProviderTest {
    private Eyes eyes = mock(Eyes)

    @BeforeEach
    void before() {
        screenshotProvider = new EyesProvider(eyes)
        reset(eyes)
    }

    @Test
    @DisplayName("Should take window screenshot with Eyes")
    void eyesWindowScreenshot() {
        takeScreenshot('eyes')

        verify(eyes, times(1)).checkWindow('eyes')
    }

    @Test
    @DisplayName("Should take component screenshot with Eyes")
    void eyesComponentScreenshot() {
        Component cmp = mock(Component)
        when(cmp.id()).thenReturn("cmpId")

        takeScreenshot('eyes', cmp)

        verify(eyes, times(1)).checkElement(By.id(cmp.id()), 'eyes')
    }
}