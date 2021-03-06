/**
 * Copyright (C) 2018-2019 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hotels.bdp.circustrain.bigquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface RuntimeConfiguration {

  int getThreadPoolSize();

  public static final RuntimeConfiguration DEFAULT = new Default(Runtime.getRuntime().availableProcessors());

  static class Default implements RuntimeConfiguration {
    private static final Logger log = LoggerFactory.getLogger(Default.class);
    private final int threads;

    Default(int cores) { // ← easy to test with different core values
      threads = cores <= 0 ? 2 : cores + 1;
      log.debug("Default thread pool size set to {}", threads);
    }

    public int getThreadPoolSize() {
      return threads;
    }
  }
}
