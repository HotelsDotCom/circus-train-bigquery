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
package com.hotels.bdp.circustrain.bigquery.client;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.hotels.hcommon.hive.metastore.client.api.CloseableMetaStoreClient;

public class BigQueryMetastoreClientFactoryTest {

  private final BigQueryMetastoreClientFactory factory = new BigQueryMetastoreClientFactory(null, null, null, null);

  @Test
  public void acceptsBigQueryUri() {
    assertThat(factory.accepts("bigquery://my-project-id"), is(true));
  }

  @Test
  public void rejectsThriftUri() {
    assertThat(factory.accepts("thrift://my-thrift-uri"), is(false));
  }

  @Test
  public void newInstance() {
    CloseableMetaStoreClient client = factory.newInstance(null, "testName");
    assertThat(client, instanceOf(BigQueryMetastoreClient.class));
  }
}
