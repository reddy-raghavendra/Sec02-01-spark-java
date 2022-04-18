package edu.nwmsu.sec02grp1.reddy;

import java.util.ArrayList;

public class RankedPageReddy {
    String voter;
    ArrayList<VotingPageReddy> voterList = new ArrayList<>();
    
    public RankedPageReddy(String voter, ArrayList<VotingPageReddy> voters){
        this.voter = voter;
        this.voterList = voters;
    }
}
