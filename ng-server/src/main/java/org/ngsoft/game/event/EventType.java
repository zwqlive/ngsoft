package org.ngsoft.game.event;

/**
 * Created by will on 2015-3-20.
 */
public enum EventType {
    PlayeyLevelUP(1);

    private int id;
    private EventType(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }
}
