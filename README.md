# Sec02-01-spark-java
This Repository is a BigData course project where we are finding the page rank of a set of markdown pages using apache beam as a runner , apache spark as a data processor.
## What is Page Rank?
PageRank is one of the methods Google uses to determine a page’s relevance or importance. PageRank works by counting the number and quality of links to a page to determine a rough estimate of how important the website is. The underlying assumption is that more important websites are likely to receive more links from other websites.It is only one part of the story when it comes to the Google listing, but the other aspects are discussed elsewhere (and are ever changing) and PageRank is interesting enough to deserve a paper of its own.
Page Rank is a topic much discussed by Search Engine Optimisation (SEO) experts. At the heart of PageRank is a mathematical formula that seems scary to look at but is actually fairly simple to understand.
## How is it implemented?
*PageRank extends this idea by not counting links from all pages equally, and by normalizing by the number of links on a page. PageRank is defined as follows:
We assume page A has pages T1...Tn which point to it (i.e., are citations). The parameter d is a damping factor which can be set between 0 and 1. We usually set d to 0.85. There are more details about d in the next section. Also C(A) is defined as the number of links going out of page A. The PageRank of a page A is given as follows:*
>PR(A) = (1-d) + d (PR(T1)/C(T1) + ... + PR(Tn)/C(Tn))

*Note that the PageRanks form a probability distribution over web pages, so the sum of all web pages' PageRanks will be one.*

## Spark Java
Spark Framework is a simple and expressive Java/Kotlin web framework DSL built for rapid development. Sparks intention is to provide an alternative for Kotlin/Java developers that want to develop their web applications as expressive as possible and with minimal boilerplate. With a clear philosophy Spark is designed not only to make you more productive, but also to make your code better under the influence of Spark’s sleek, declarative and expressive syntax.

## Collaborators
* [Sushma Nalla](https://github.com/SushmaNalla)
* [Raghavendra Reddy](https://github.com/reddy-raghavendra)  
   <img src="https://media-exp1.licdn.com/dms/image/C5603AQH4GSd0J57Zag/profile-displayphoto-shrink_400_400/0/1648489753605?e=1656547200&v=beta&t=ovpNpcl5M0h-urCUuQFvAZ8oTs5LkJh52n7vkvYt7rA" width="200" height="200">
* [Harshavardhan Kurra](https://github.com/harshakurra123)
* [Hemanth Reddy Telluri](https://github.com/hemanth8056)
* [Venkata Gopi Siva Sai Nallapati](https://github.com/NVGSSAI)
* [Saikumar Mylavarapu](https://github.com/saikumar438)

## Wiki Pages and Folder Links
* ### Venkata Gopi Siva Sai Nallapati
    * [Link to  Folder Readme](https://github.com/reddy-raghavendra/Sec02-01-spark-java/tree/main/Venkata%20Gopi%20Siva%20Sai%20Nallapati)
    * [Link to wiki](https://github.com/reddy-raghavendra/Sec02-01-spark-java/wiki/Sai-Nallapati)
    * [Link to Commits](https://github.com/reddy-raghavendra/Sec02-01-spark-java/commits?author=NVGSSAI)

* ### Sushma Nalla
    * [Link to  Folder Readme](https://github.com/reddy-raghavendra/Sec02-01-spark-java/tree/main/Sushma%20Nalla)
    * [Link to wiki](https://github.com/reddy-raghavendra/Sec02-01-spark-java/wiki/Sushma-Nalla)

* ### Raghavendra Reddy
    ![my image](https://media-exp1.licdn.com/dms/image/C5603AQH4GSd0J57Zag/profile-displayphoto-shrink_400_400/0/1648489753605?e=1656547200&v=beta&t=ovpNpcl5M0h-urCUuQFvAZ8oTs5LkJh52n7vkvYt7rA)
    * [Link to Folder Readme](https://github.com/reddy-raghavendra/Sec02-01-spark-java/blob/main/Raghavendra%20Reddy)
    * [Link to Wiki](https://github.com/reddy-raghavendra/Sec02-01-spark-java/wiki/Raghavendra-Reddy)

* ### Hemaanth Venkata Reddy Telluri
    * [Link to Folder Readme](https://github.com/reddy-raghavendra/Sec02-01-spark-java/blob/main/Hemanth%20Reddy%20Telluri/README.md)
    * [Link to Wiki](https://github.com/reddy-raghavendra/Sec02-01-spark-java/wiki/Hemanth-Venkata-Reddy-Telluri)

* ### Saikumar Mylavarapu
     * [Link to Folder Readme](https://github.com/reddy-raghavendra/Sec02-01-spark-java/blob/main/Saikumar%20Mylavarapu/README.md)
     * [Link to Wiki](https://github.com/reddy-raghavendra/Sec02-01-spark-java/wiki/Saikumar-Mylavarapu)

* ### Harshavardhan Kurra
     * [Link to Folder Readme](https://github.com/reddy-raghavendra/Sec02-01-spark-java/blob/main/Harshavardhan%20Kurra/README.md)
     * [Link to Wiki](https://github.com/reddy-raghavendra/Sec02-01-spark-java/wiki/Harshavardhan-Kurra)

## Individual comments
* ### Raghavendra Reddy

   * Created a PageRank file name which reads the links on each page and finds the rank of the pages.
   * There are two jobs created, the first job maps the pages to outgoing links with initial rank.
   * Used custom classes for ranks and votes to store the data
   * The second job mapper takes the voters from the first job and maps individually.
   * Job 2 updater finds the page rank using the page rank formulla




* ### Sushma Nalla







* ### Harshavardhan Kurra

This is harshavardhan Kurra comment's section



* ### Venkata Gopi Siva Sai Nallapati
I have added my own folder and space in the readme.<br>
I have worked on creating Wiki pages and modified readme details.<br>
I have Created a Custom Java Maven Project For finding the Page Rank.<br>
I have Added a Custom Java file (MinimalPageRankNallapati) for finding the page rank.<br>
I have Added 2 more java files NallapatiVotingPage and NallapatiRanked Page.<br>







* ### Hemaanth Venkata Reddy Telluri









* ### Saikumar Mylavarapu
   * Created a folder with my name.
   * Created a wiki page with my name and have added my issues.
   * Created a page rank project with java, which reads the pages from the web04 directory.
   * Created a PageRank file name which reads the links on each page and finds the rank of the pages.
   * Added VotingPage and RankedPage java files.
