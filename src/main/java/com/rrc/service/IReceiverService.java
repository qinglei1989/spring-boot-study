package com.rrc.service;

import com.rrc.entity.MessageCollect;

public interface IReceiverService {
    public void collectReceive(MessageCollect messageCollect);

    public void commentReceive(MessageCollect messageComment);
}
