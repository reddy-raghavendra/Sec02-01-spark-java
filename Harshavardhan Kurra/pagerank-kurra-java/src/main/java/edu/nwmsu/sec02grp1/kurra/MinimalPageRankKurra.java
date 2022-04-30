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
package edu.nwmsu.sec02grp1.kurra;

import java.io.Serializable;
import java.util.ArrayList;

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
import java.util.Collection;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
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

public class MinimalPageRankKurra{
  // DEFINE DOFNS
  // ==================================================================
  // You can make your pipeline assembly code less verbose by defining
  // your DoFns statically out-of-line.
  // Each DoFn<InputT, OutputT> takes previous output
  // as input of type InputT
  // and transforms it to OutputT.
  // We pass this DoFn to a ParDo in our pipeline.
  /**
   * DoFn Job1Finalizer takes KV(String, String List of outlinks) and transforms
   * the value into our custom RankedPage Value holding the page's rank and list
   * of voters.
   * 
   * The output of the Job1 Finalizer creates the initial input into our
   * iterative Job 2.
   */
  static class Job1Finalizer extends DoFn<KV<String, Iterable<String>>, KV<String, RankedPageKurra>> {
    @ProcessElement
    public void processElement(@Element KV<String, Iterable<String>> element,
        OutputReceiver<KV<String, RankedPageKurra>> receiver) {
      Integer contributorVotes = 0;
      if (element.getValue() instanceof Collection) {
        contributorVotes = ((Collection<String>) element.getValue()).size();
      }
      ArrayList<VotingPageKurra> voters = new ArrayList<VotingPageKurra>();
      for (String voterName : element.getValue()) {
        if (!voterName.isEmpty()) {
          voters.add(new VotingPageKurra(voterName, contributorVotes));
        }
      }
      receiver.output(KV.of(element.getKey(), new RankedPageKurra(element.getKey(), voters)));
    }
  }

  static class Job2Mapper extends DoFn<KV<String, RankedPageKurra>, KV<String, RankedPageKurra>> {
    @ProcessElement
    public void processElement(@Element KV<String, RankedPageKurra> element,
      OutputReceiver<KV<String, RankedPageKurra>> receiver) {
      int votes = 0;
      ArrayList<VotingPageKurra> voters = element.getValue().getVoterList();
      if(voters instanceof Collection){
        votes = ((Collection<VotingPageKurra>) voters).size();
      }
      for(VotingPageKurra vp: voters){
        String pageName = vp.getVoterName();
        double pageRank = vp.getPageRank();
        String contributingPageName = element.getKey();
        double contributingPageRank = element.getValue().getRank();
        VotingPageKurra contributor = new VotingPageKurra(contributingPageName,votes,contributingPageRank);
        ArrayList<VotingPageKurra> arr = new ArrayList<>();
        arr.add(contributor);
        receiver.output(KV.of(vp.getVoterName(), new RankedPageKurra(pageName, pageRank, arr)));        
      }
    }
  }

  static class Job2Updater extends DoFn<KV<String, Iterable<RankedPageKurra>>, KV<String, RankedPageKurra>> {
    @ProcessElement
    public void processElement(@Element KV<String, Iterable<RankedPageKurra>> element,
      OutputReceiver<KV<String, RankedPageKurra>> receiver) {
        Double dampingFactor = 0.85;
        Double updatedRank = (1 - dampingFactor);
        ArrayList<VotingPageKurra> newVoters = new ArrayList<>();
        for(RankedPageKurra rankPage:element.getValue()){
          if (rankPage != null) {
            for(VotingPageKurra votingPage:rankPage.getVoterList()){
              newVoters.add(votingPage);
              updatedRank += (dampingFactor) * votingPage.getPageRank() / (double)votingPage.getContributorVotes();
            }
          }
        }
        receiver.output(KV.of(element.getKey(),new RankedPageKurra(element.getKey(), updatedRank, newVoters)));

    }

  }

  static class Job3 extends DoFn<KV<String, RankedPageKurra>, KV<Double, String>>{
    @ProcessElement
     public void processElement(@Element KV<String, RankedPageKurra> element,
      OutputReceiver<KV<Double, String>> receiver){
        receiver.output(KV.of(element.getValue().getRank(),element.getKey()));
    }
  }

  public static void main(String[] args) {

    PipelineOptions options = PipelineOptionsFactory.create();
    Pipeline p = Pipeline.create(options);

    // constant folder
    final String folderName = "web04";
    // Calling method with each files
    PCollection<KV<String, String>> pColLinksGo = kurraPcolLinks(p, folderName, "go.md");
    PCollection<KV<String, String>> pColLinksJava = kurraPcolLinks(p, folderName, "java.md");
    PCollection<KV<String, String>> pColLinksPython = kurraPcolLinks(p, folderName, "python.md");
    PCollection<KV<String, String>> pColLinksReadme = kurraPcolLinks(p, folderName, "readme.md");
    PCollectionList<KV<String, String>> pColList = PCollectionList.of(pColLinksGo).and(pColLinksJava)
        .and(pColLinksPython).and(pColLinksReadme);
    PCollection<KV<String, String>> pColListMerged = pColList.apply(Flatten.<KV<String, String>>pCollections());
    PCollection<KV<String, Iterable<String>>> pColGroupByKey = pColListMerged.apply(GroupByKey.create());
    PCollection<KV<String, RankedPageKurra>> job2in = pColGroupByKey.apply(ParDo.of(new Job1Finalizer()));

    PCollection<String> pColStringLists = job2in.apply(
        MapElements.into(
            TypeDescriptors.strings()).via(
                kvtoString -> kvtoString.toString()));
    pColStringLists.apply(TextIO.write().to("PageRank-Kurra"));

    p.run().waitUntilFinish();
  }

  private static PCollection<KV<String, String>> kurraPcolLinks(Pipeline p, final String folderName,
      final String fileName) {
    PCollection<String> pColInputLines = p.apply(TextIO.read().from(folderName + "/" + fileName));
    PCollection<String> pColLinkLines = pColInputLines.apply(Filter.by((String line) -> line.startsWith("[")));

    PCollection<String> pColLinks = pColLinkLines.apply(
        MapElements.into(
            TypeDescriptors.strings())
            .via(
                (String linkLine) -> linkLine.substring(linkLine.indexOf("(") + 1, linkLine.indexOf(")"))));

    PCollection<KV<String, String>> pColkvs = pColLinks.apply(
        MapElements.into(
            TypeDescriptors.kvs(TypeDescriptors.strings(), TypeDescriptors.strings())).via(
                outgoingLink -> KV.of(fileName, outgoingLink)));
    // Return the KV pairs
    return pColkvs;
  }
}
