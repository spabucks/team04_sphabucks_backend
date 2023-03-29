package sphabucks.global.alarm.service;

import sphabucks.global.alarm.model.ReceivingNotification;
import sphabucks.global.alarm.vo.RequestReceivingNotification;
import sphabucks.global.auth.vo.RequestHead;

import java.util.List;

public interface IReceivingNotificationService {
    void addReceivingNotification(RequestHead requestHead, RequestReceivingNotification requestReceivingNotification);
    List<ReceivingNotification> getReceivingNotification(RequestHead requestHead);




}
