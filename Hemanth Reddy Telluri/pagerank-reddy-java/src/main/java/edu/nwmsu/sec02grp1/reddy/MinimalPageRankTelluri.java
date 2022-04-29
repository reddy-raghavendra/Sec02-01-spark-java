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
import org.apache.beam.sdk.transforms.Combine;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.Max;
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

public class MinimalPageRankTelluri {
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
  static class Job1Finalizer extends DoFn<KV<String, Iterable<String>>, KV<String, RankedPageTelluri>> {
    @ProcessElement
    public void processElement(@Element KV<String, Iterable<String>> element,
        OutputReceiver<KV<String, RankedPageTelluri>> receiver) {
      Integer contributorVotes = 0;
      if (element.getValue() instanceof Collection) {
        contributorVotes = ((Collection<String>) element.getValue()).size();
      }
      ArrayList<VotingPageTelluri> voters = new ArrayList<VotingPageTelluri>();
      for (String voterName : element.getValue()) {
        if (!voterName.isEmpty()) {
          voters.add(new VotingPageTelluri(voterName, contributorVotes));
        }
      }
      receiver.output(KV.of(element.getKey(), new RankedPageTelluri(element.getKey(), voters)));
    }
  }

  static class Job2Mapper extends DoFn<KV<String, RankedPageTelluri>, KV<String, RankedPageTelluri>> {
    @ProcessElement
    public void processElement(@Element KV<String, RankedPageTelluri> element,
        OutputReceiver<KV<String, RankedPageTelluri>> receiver) {
      int votes = 0;
      ArrayList<VotingPageTelluri> voters = element.getValue().getVoterList();
      if (voters instanceof Collection) {
        votes = ((Collection<VotingPageTelluri>) voters).size();
      }
      for (VotingPageTelluri vp : voters) {
        String pageName = vp.getVoterName();
        double pageRank = vp.getPageRank();
        String contributingPageName = element.getKey();
        double contributingPageRank = element.getValue().getRank();
        VotingPageTelluri contributor = new VotingPageTelluri(contributingPageName, votes, contributingPageRank);
        ArrayList<VotingPageTelluri> arr = new ArrayList<>();
        arr.add(contributor);
        receiver.output(KV.of(vp.getVoterName(), new RankedPageTelluri(pageName, pageRank, arr)));
      }
    }
  }

  static class Job2Updater extends DoFn<KV<String, Iterable<RankedPageTelluri>>, KV<String, RankedPageTelluri>> {
    @ProcessElement
    public void processElement(@Element KV<String, Iterable<RankedPageTelluri>> element,
        OutputReceiver<KV<String, RankedPageTelluri>> receiver) {
      Double dampingFactor = 0.85;
      Double updatedRank = (1 - dampingFactor);
      ArrayList<VotingPageTelluri> newVoters = new ArrayList<>();
      for (RankedPageTelluri rankPage : element.getValue()) {
        if (rankPage != null) {
          for (VotingPageTelluri votingPage : rankPage.getVoterList()) {
            newVoters.add(votingPage);
            updatedRank += (dampingFactor) * votingPage.getPageRank() / (double) votingPage.getContributorVotes();
          }
        }
      }
      receiver.output(KV.of(element.getKey(), new RankedPageTelluri(element.getKey(), updatedRank, newVoters)));

    }

  }

  // static class Job2 extends DoFn<KV<String, RankedPageReddy>, KV<String,
  // RankedPageReddy>>{
  // @ProcessElement
  // public void processElement(@Element KV<String, RankedPageReddy> element,
  // OutputReceiver<KV<String, RankedPageReddy>> receiver){
  // PCollection<KV<String,RankedPageReddy>> job2Mapper =
  // element.getKey().apply(ParDo.of(new Job2Mapper()));

  // PCollection<KV<String,Iterable<RankedPageReddy>>> job2MapperGrpByKey =
  // job2Mapper.apply(GroupByKey.create());

  // PCollection<KV<String, RankedPageReddy>> job2Updater =
  // job2MapperGrpByKey.apply(ParDo.of(new Job2Updater()));
  // }
  // }

  static class Job3 extends DoFn<KV<String, RankedPageTelluri>, KV<Double, String>> {
    @ProcessElement
    public void processElement(@Element KV<String, RankedPageTelluri> element,
        OutputReceiver<KV<Double, String>> receiver) {
      double maxRank = Integer.MIN_VALUE;
      receiver.output(KV.of(element.getValue().getRank(), element.getKey()));
    }
  }

  public static void main(String[] args) {

    PipelineOptions options = PipelineOptionsFactory.create();
    Pipeline p = Pipeline.create(options);

    // constant folder
    final String folderName = "web04";
    // Calling method with each files
    PCollection<KV<String, String>> pColLinksGo = reddyPcolLinks(p, folderName, "go.md");
    PCollection<KV<String, String>> pColLinksJava = reddyPcolLinks(p, folderName, "java.md");
    PCollection<KV<String, String>> pColLinksPython = reddyPcolLinks(p, folderName, "python.md");
    PCollection<KV<String, String>> pColLinksReadme = reddyPcolLinks(p, folderName, "readme.md");
    // Add all the PCollection to PCollection list
    PCollectionList<KV<String, String>> pColList = PCollectionList.of(pColLinksGo).and(pColLinksJava)
        .and(pColLinksPython).and(pColLinksReadme);
    // Merge all the key value lists to single list
    PCollection<KV<String, String>> pColListMerged = pColList.apply(Flatten.<KV<String, String>>pCollections());
    // group by key
    PCollection<KV<String, Iterable<String>>> pColGroupByKey = pColListMerged.apply(GroupByKey.create());
    // Convert to a custom Value object (RankedPage) in preparation for Job 2
    PCollection<KV<String, RankedPageTelluri>> job2in = pColGroupByKey.apply(ParDo.of(new Job1Finalizer()));

    PCollection<KV<String, RankedPageTelluri>> job2out = null;
    int iterations = 40;
    for (int i = 1; i <= iterations; i++) {
      // use job2in to calculate job2 out
      PCollection<KV<String, RankedPageTelluri>> job2Mapper = job2in.apply(ParDo.of(new Job2Mapper()));

      PCollection<KV<String, Iterable<RankedPageTelluri>>> job2MapperGrpByKey = job2Mapper.apply(GroupByKey.create());

      job2out = job2MapperGrpByKey.apply(ParDo.of(new Job2Updater()));
      // update job2in so it equals the new job2out
      job2in = job2out;
    }

    PCollection<KV<Double, String>> job3 = job2out.apply(ParDo.of(new Job3()));

    PCollection<KV<Double, String>> maxRank = job3.apply(Combine.globally(Max.of(new RankedPageTelluri())));
    // Combine.globally(Max.of(job3));
    // Change the KV pairs to String using toString of kv
    PCollection<String> pColStringLists = maxRank.apply(
        MapElements.into(
            TypeDescriptors.strings()).via(
                kvtoString -> kvtoString.toString()));
    // Write the output to the file

    pColStringLists.apply(TextIO.write().to("PageRank-Telluri"));

    p.run().waitUntilFinish();
  }

  private static PCollection<KV<String, String>> reddyPcolLinks(Pipeline p, final String folderName,
      final String fileName) {
    // Fetching the data from the destination
    PCollection<String> pColInputLines = p.apply(TextIO.read().from(folderName + "/" + fileName));
    // taking the lines only which starts with [
    PCollection<String> pColLinkLines = pColInputLines.apply(Filter.by((String line) -> line.startsWith("[")));

    // Take the outgoing links from the format []("")
    PCollection<String> pColLinks = pColLinkLines.apply(
        MapElements.into(
            TypeDescriptors.strings())
            .via(
                (String linkLine) -> linkLine.substring(linkLine.indexOf("(") + 1, linkLine.indexOf(")"))));

    // Map the links with the file name passed to it
    PCollection<KV<String, String>> pColkvs = pColLinks.apply(
        MapElements.into(
            TypeDescriptors.kvs(TypeDescriptors.strings(), TypeDescriptors.strings())).via(
                outgoingLink -> KV.of(fileName, outgoingLink)));
    // Return the KV pairs
    return pColkvs;
  }
}