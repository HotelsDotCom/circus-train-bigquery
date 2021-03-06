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
package com.hotels.bdp.circustrain.bigquery.table.service.unpartitioned;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

import com.hotels.bdp.circustrain.bigquery.api.TableService;

public class UnpartitionedTableService implements TableService {

  private final Table table;

  public UnpartitionedTableService(Table table) {
    this.table = table;
  }

  @Override
  public Table getTable() {
    return new Table(table);
  }

  @Override
  public List<Partition> getPartitions() {
    return new ArrayList<>();
  }
}
