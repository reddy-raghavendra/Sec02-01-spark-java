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
package edu.nwmsu.sec02grp1.reddy;

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

import java.util.Arrays;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.values.TypeDescriptors;

import io.grpc.netty.shaded.io.netty.handler.pcap.PcapWriteHandler;

/**
 * An example that counts words in Shakespeare.
 *
 * <p>
 * This class, {@link MinimalWordCount}, is the first in a series of four
 * successively more
 * detailed 'word count' examples. Here, for simplicity, we don't show any
 * error-checking or
 * argument processing, and focus on construction of the pipeline, which chains
 * together the
 * application of core transforms.
 *
 * <p>
 * Next, see the {@link WordCount} pipeline, then the
 * {@link DebuggingWordCount}, and finally the
 * {@link WindowedWordCount} pipeline, for more detailed examples that introduce
 * additional
 * concepts.
 *
 * <p>
 * Concepts:
 *
 * <pre>
 *   1. Reading data from text files
 *   2. Specifying 'inline' transforms
 *   3. Counting items in a PCollection
 *   4. Writing data to text files
 * </pre>
 *
 * <p>
 * No arguments are required to run this pipeline. It will be executed with the
 * DirectRunner. You
 * can see the results in the output files in your current working directory,
 * with names like
 * "wordcounts-00001-of-00005. When running on a distributed service, you would
 * use an appropriate
 * file service.
 */
public class MinimalPageRankReddy {

  public static void main(String[] args) {

    PipelineOptions options = PipelineOptionsFactory.create();
    Pipeline p = Pipeline.create(options);

// constant folder
    final String folderName = "web04";
// Calling method with each files
    PCollection<KV<String,String>> pColLinksGo = reddyPcolLinks(p, folderName, "go.md");    
    PCollection<KV<String,String>> pColLinksJava = reddyPcolLinks(p, folderName, "java.md");    
    PCollection<KV<String,String>> pColLinksPython = reddyPcolLinks(p, folderName, "python.md");    
    PCollection<KV<String,String>> pColLinksReadme = reddyPcolLinks(p, folderName, "readme.md");    
// Add all the PCollection to PCollection list
    PCollectionList<KV<String,String>> pColList = PCollectionList.of(pColLinksGo).and(pColLinksJava).and(pColLinksPython).and(pColLinksReadme);
// Merge all the key value lists to single list
    PCollection<KV<String,String>> pColListMerged =  pColList.apply(Flatten.<KV<String,String>>pCollections());
//group by key
  PCollection<KV<String,Iterable<String>>> pColGroupByKey = pColListMerged.apply(GroupByKey.create());
// Change the KV pairs to String using toString of kv 
    PCollection<String> pColStringLists = pColGroupByKey.apply(
      MapElements.into(
        TypeDescriptors.strings()
      ).via(
        kvtoString -> kvtoString.toString()
      )
    );
//Write the output to the file 
    pColStringLists.apply(TextIO.write().to("PageRank-Reddy"));

    p.run().waitUntilFinish();
  }

  private static PCollection<KV<String,String>> reddyPcolLinks(Pipeline p, final String folderName, final String fileName) {
// Fetching the data from the destination
    PCollection<String> pColInputLines = p.apply(TextIO.read().from(folderName + "/" + fileName));
// taking the lines only which starts with [ 
    PCollection<String> pColLinkLines = pColInputLines.apply(Filter.by((String line) -> line.startsWith("[")));

//Take the outgoing links from the format []("") 
    PCollection<String> pColLinks = pColLinkLines.apply(
      MapElements.into(
        TypeDescriptors.strings()
        )
      .via(
          (String linkLine) -> linkLine.substring(linkLine.indexOf("(")+1,linkLine.indexOf(")"))
    ));

// Map the links with the file name passed to it
    PCollection<KV<String,String>> pColkvs = pColLinks.apply(
      MapElements.into(
        TypeDescriptors.kvs(TypeDescriptors.strings(),TypeDescriptors.strings())
      ).via(
              outgoingLink -> KV.of(fileName,outgoingLink)
      )
    );
//Return the KV pairs 
    return pColkvs;
  }
}
