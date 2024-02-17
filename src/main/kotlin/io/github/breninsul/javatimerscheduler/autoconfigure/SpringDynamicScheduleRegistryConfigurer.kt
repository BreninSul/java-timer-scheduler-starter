/*
 * MIT License
 *
 * Copyright (c) 2024 BreninSul
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.breninsul.javatimerscheduler.autoconfigure

import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

/**
 * [SpringDynamicScheduleRegistryConfigurer] is a class that provides configuration for dynamic task scheduling in Spring framework.
 *
 * @property executor A [ScheduledExecutorService] that will be used to create dynamic tasks.
 */
open class SpringDynamicScheduleRegistryConfigurer(protected val executor: ScheduledExecutorService) : SchedulingConfigurer {
    /**
     * This method configures tasks for dynamic scheduling
     *
     * @param taskRegistrar A [ScheduledTaskRegistrar] that registers tasks for scheduling
     */
    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        with(SpringDynamicScheduleRegistry) {
            registrar = taskRegistrar
        }
        Executors.newScheduledThreadPool(0, Thread.ofVirtual().factory())
        taskRegistrar.setScheduler(executor)
    }
}
