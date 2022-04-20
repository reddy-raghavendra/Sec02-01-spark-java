package edu.nwmsu.sec02grp1.nalla;

import java.util.ArrayList;

public class RankedPageNalla {
    String voter;
    ArrayList<VotingPageNalla> voterList = new ArrayList<>();
    
    public RankedPageNalla(String voter, ArrayList<VotingPageNalla> voters){
        this.voter = voter;
        this.voterList = voters;
    }
}
