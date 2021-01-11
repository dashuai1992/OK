package com.very.ok.enums;

public enum StateEnum {
	
	DELETED(0),
    NORMAL(1);

    private int val;

    private StateEnum(final int val) {
        this.val = val;
    }

    public int getVal(){
        return this.val;
    }

}
