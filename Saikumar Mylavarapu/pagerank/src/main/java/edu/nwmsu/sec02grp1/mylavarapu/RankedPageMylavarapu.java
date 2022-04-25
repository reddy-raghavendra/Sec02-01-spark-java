package edu.nwmsu.sec02grp1.mylavarapu;

import java.io.Serializable;
import java.util.ArrayList;

public class RankedPageMylavarapu implements Serializable{
    String voter;
    ArrayList<VotingPageMylavarapu> voterList = new ArrayList<>();
    
    public RankedPageMylavarapu(String voter, ArrayList<VotingPageMylavarapu> voters){
        this.voter = voter;
        this.voterList = voters;
    }

    @Override
    public String toString() {
        return voter + voterList;
    }
}