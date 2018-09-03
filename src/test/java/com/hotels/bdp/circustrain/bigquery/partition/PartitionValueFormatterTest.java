/**
 * Copyright (C) 2018 Expedia Inc.
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
package com.hotels.bdp.circustrain.bigquery.partition;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.junit.Test;

public class PartitionValueFormatterTest {

  private final List<FieldSchema> cols = new ArrayList<>();
  private final FieldSchema fieldSchema = new FieldSchema();

  private final String partitionKey = "key";
  private String value = "value";

  private void setSchema(String type) {
    fieldSchema.setName(partitionKey);
    fieldSchema.setType(type);
    cols.add(fieldSchema);
  }

  @Test
  public void formatNotNeeded() {
    setSchema("INTEGER");
    PartitionValueFormatter formatter = new PartitionValueFormatter(partitionKey, cols);
    assertEquals(value, formatter.format(value));
  }

  @Test
  public void formatStringValue() {
    setSchema("STRING");
    String expected = "\"" + value + "\"";
    PartitionValueFormatter formatter = new PartitionValueFormatter(partitionKey, cols);
    assertThat(formatter.format(value), is(expected));
  }

  @Test
  public void formatTimestampValue() {
    setSchema("TIMESTAMP");
    String expected = "\"" + value + "\"";
    PartitionValueFormatter formatter = new PartitionValueFormatter(partitionKey, cols);
    assertEquals(expected, formatter.format(value));
  }

  @Test
  public void formatDateValue() {
    setSchema("DATE");
    String expected = "\"" + value + "\"";
    PartitionValueFormatter formatter = new PartitionValueFormatter(partitionKey, cols);
    assertEquals(expected, formatter.format(value));
  }

  @Test
  public void formatTimestampFromUnixTimestampValue() {
    value = "1483228800.0";
    fieldSchema.setName(partitionKey);
    fieldSchema.setType("TIMESTAMP");
    cols.add(fieldSchema);
    String expected = "\"2017-01-01 00:00:00.000\"";
    PartitionValueFormatter formatter = new PartitionValueFormatter(partitionKey, cols);
    assertEquals(expected, formatter.format(value));
  }

  @Test
  public void formatTimestampWithMilliseconds() {
    value = "1408452095.22";
    fieldSchema.setName(partitionKey);
    fieldSchema.setType("TIMESTAMP");
    cols.add(fieldSchema);
    String expected = "\"2014-08-19 12:41:35.220\"";
    PartitionValueFormatter formatter = new PartitionValueFormatter(partitionKey, cols);
    assertEquals(expected, formatter.format(value));
  }

}
