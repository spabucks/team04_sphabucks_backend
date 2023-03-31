package sphabucks.global.alarm.service;

import sphabucks.global.alarm.model.ReceivingNotification;
import sphabucks.global.alarm.vo.RequestReceivingNotification;

import java.util.List;

public interface IReceivingNotificationService {
    void addReceivingNotification(String userId, RequestReceivingNotification requestReceivingNotification);
    List<ReceivingNotification> getReceivingNotification(String userId);




}
