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
package com.hotels.bdp.circustrain.bigquery.extraction.container;

import org.apache.hadoop.hive.metastore.api.Partition;

import com.google.cloud.storage.Storage;

import com.hotels.bdp.circustrain.bigquery.util.AvroConstants;
import com.hotels.bdp.circustrain.bigquery.util.SchemaExtractor;

public class UpdatePartitionSchemaPostExtractionAction implements PostExtractionAction {

  private final Partition partition;
  private final Storage storage;
  private final ExtractionUri extractionUri;

  public UpdatePartitionSchemaPostExtractionAction(Partition partition, Storage storage, ExtractionUri extractionUri) {
    this.partition = partition;
    this.storage = storage;
    this.extractionUri = extractionUri;
  }

  @Override
  public void run() {
    String schema = SchemaExtractor.getSchemaFromStorage(storage, extractionUri);
    partition.getSd().getSerdeInfo().putToParameters(AvroConstants.SCHEMA_PARAMETER, schema);
  }

}
