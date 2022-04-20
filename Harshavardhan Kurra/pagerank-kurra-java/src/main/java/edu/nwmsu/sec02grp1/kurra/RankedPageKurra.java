package edu.nwmsu.sec02grp1.kurra;

import java.util.ArrayList;

public class RankedPageKurra {
    String voter;
    ArrayList<VotingPageKurra> voterList = new ArrayList<>();
    
    public RankedPageKurra(String voter, ArrayList<VotingPageKurra> voters){
        this.voter = voter;
        this.voterList = voters;
    }
}