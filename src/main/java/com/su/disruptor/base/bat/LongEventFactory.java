package com.su.disruptor.base.bat;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory<LongEvent>
{
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}