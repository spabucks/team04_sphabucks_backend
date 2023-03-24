package sphabucks.alarm.service;

import sphabucks.alarm.model.ReceivingNotification;
import sphabucks.alarm.vo.RequestGetNotification;
import sphabucks.alarm.vo.RequestReceivingNotification;

import java.util.List;

public interface IReceivingNotificationService {
    void addReceivingNotification(RequestReceivingNotification requestReceivingNotification);
    List<ReceivingNotification> getReceivingNotification(RequestGetNotification requestGetNotification);




}
