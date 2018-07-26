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

import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Table;

import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.Schema;

import com.hotels.bdp.circustrain.bigquery.conversion.BigQueryToHiveTypeConverter;

class HiveParitionKeyAdder {

  private final Table table;

  HiveParitionKeyAdder(Table table) {
    this.table = table;
  }

  void add(String partitionKey, Schema filteredTableSchema) {
    if (filteredTableSchema == null) {
      return;
    }

    BigQueryToHiveTypeConverter typeConverter = new BigQueryToHiveTypeConverter();
    for (Field field : filteredTableSchema.getFields()) {
      String fieldName = field.getName().toLowerCase().trim();
      if (partitionKey.equals(fieldName)) {
        FieldSchema fieldSchema = new FieldSchema();
        fieldSchema.setName(fieldName);
        fieldSchema.setType(typeConverter.convert(field.getType().toString()).toLowerCase());
        table.addToPartitionKeys(fieldSchema);
      }
    }
  }
}
