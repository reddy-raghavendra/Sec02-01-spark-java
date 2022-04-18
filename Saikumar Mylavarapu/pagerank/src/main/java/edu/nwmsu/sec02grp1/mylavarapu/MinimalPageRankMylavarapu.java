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
package edu.nwmsu.sec02grp1.mylavarapu;

// beam-playground:
//   name: MinimalWordCount
//   description: An example that counts words in Shakespeare's works.
//   multifile: false
//   pipeline_options:
//   categories:
//     - Combiners
//     - Filtering
//     - IO
//     - Core Transforms

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;
import org.apache.beam.sdk.values.PDone;
import org.apache.beam.sdk.values.TypeDescriptor;

public class MinimalPageRankMylavarapu {

  public static void main(String[] args) {

    PipelineOptions options = PipelineOptionsFactory.create();

    Pipeline p = Pipeline.create(options);

    String folder="web04";
    String file="go.md";
    
   PCollection<KV<String,String>> pColKV1 = MylavarapuKVPairMapper01(p,"go.md",folder);
   PCollection<KV<String,String>> pColKV2 = MylavarapuKVPairMapper01(p,"python.md",folder);
   PCollection<KV<String,String>> pColKV3 = MylavarapuKVPairMapper01(p,"java.md",folder);
   PCollection<KV<String,String>> pColKV4 = MylavarapuKVPairMapper01(p,"README.md",folder);

   
    PCollectionList<KV<String, String>> pCollectionList = PCollectionList.of(pColKV1).and(pColKV2).and(pColKV3).and(pColKV4);
    PCollection<KV<String, String>> list = pCollectionList.apply(Flatten.<KV<String,String>>pCollections());
    PCollection<String> pLinksString = list.apply(MapElements.into(TypeDescriptors.strings()).via((mergeOut)->mergeOut.toString()));
    pLinksString.apply(TextIO.write().to("OutputMylavarapu"));  
    p.run().waitUntilFinish();
  }

  public static PCollection<KV<String,String>> MylavarapuKVPairMapper01(Pipeline p, String file, String folder){
   
    String path = folder + "/" + file;
     PCollection<String> pcolInput = p.apply(TextIO.read().from(path));
     PCollection<String> plinkLines = pcolInput.apply(Filter.by((String line) -> line.startsWith("[")));
     PCollection<String> pcolLinks = plinkLines.apply(MapElements.into((TypeDescriptors.strings()))
     .via((String linkLine) ->linkLine.substring(linkLine.indexOf("(")+1, linkLine.length()-1)));
     PCollection<KV<String,String>> pCollectionKVPairs =  pcolLinks.apply(MapElements.into(TypeDescriptors.kvs(TypeDescriptors.strings(), TypeDescriptors.strings()))
     .via((String outLink) -> KV.of(file,outLink)));
    return pCollectionKVPairs;
  }

}
