//package org.example.practice.NOTIFICATION;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
///**
// *  Design notification
// *
// *  We should be able to notify user via email, push notification and sms
// *  we should support the persistence of each notification
// *
// *   // out of the scope : rate limiter
// */
//
//
////Entity:
////User: id,name,email,mobile_number
////NotificationType: id,type (1,sms)(2,email)
////Notication: id,user,type,status
//
//class User{
//    private int id;
//    private String name;
//    private String email;
//    private String mobile_number;
//
//    User(int id,String name,String email,String mobile_number){
//        this.id = id;
//        this.name= name;
//        this.email = email;
//        this.mobile_number = mobile_number;
//    }
//    public int getId(){return id;}
//    public void setIt(){this.id = id;}
//    public String getEmail(){return this.email;}
//    /// getter setter;
//
//}
//
//class NotificationType{
//    private int id;
//    private String type;
//    NotificationType(int id,String type){
//        this.id = id;
//        this.type = type;
//    }
//    public String getType(){return type;}
//}
//
//class Notification{
//
//    private int id;
//    private User user;
//    private NotificationType type;
//    private String status;
//    private String message;
//
//    Notification(User user,NotificationType type,String status,String message){
//        this.user = user;
//        this.type = type;
//        this.status = status;
//        this.message = message;
//    }
//    //getter setter
//    public int getId(){
//        return id;
//    }
//
//}
//
//// repository
//
//interface UserRepository{
//    //crud
//    void addUser(User user);
//    User getUser(int id) throws Exception;
//    // delete update
//}
//class InMemoryRepo implements UserRepository{
//
//    Map<Integer,User> userMap = new HashMap<>();
//    public void addUser(User user){
//        userMap.put(user.getId(),user);
//    }
//    public User getUser(int id) throws Exception {
//        if(userMap.containsKey(id)){ return userMap.get(id);}
//        throw new Exception("User not found");
//    }
//
//}
//
//interface NotificationTypeRepository{
//    void addNotificationType(NotificationType type);
//    Optional<NotificationType>findByType(String type);
//}
//
//class InMemoryNotificationType implements NotificationTypeRepository{
//
//    Map<String,NotificationType> typeMap = new HashMap<>();
//
//    public void addNotificationType(NotificationType type){
//        typeMap.put(type.getType(),type);
//    }
//    public Optional<NotificationType>findByType(String type){
//        return Optional.ofNullable(typeMap.get(type));
//    }
//
//
//}
//
//
//interface NotificationRepository{
//    void addNotification(Notification Notification);
//    Notification getNotification(int id);
//}
//
//class InMemoryNotification implements NotificationRepository{
//
//    Map<Integer,Notification> notificationMap = new HashMap<>();
//    public void addNotification(Notification notification){
//        notificationMap.put(notification.getId(),notification);
//    }
//    public Notification getNotification(int id){
//        return notificationMap.get(id);
//    }
//
//}
//
//
//// service
//
//interface NotificationService{
//
//    void sendNotification(User user,String message);
//    // analytics
//}
//class EmailNotificationServiceImpl implements NotificationService{
//
//    NotificationRepository notirepo;
//    EmailNotificationServiceImpl(NotificationRepository notirepo){
//        this.notirepo = notirepo;
//    }
//
//    public void sendNotification(User user,String message){
//
//        Notification notification  = new Notification(user,new NotificationType(1,"email"),"Pending","Hi");
//        // email logic
//        System.out.println("Sending email");
//
//        notirepo.addNotification(notification);
//
//    }
//
//}
//class SmsNotificationService implements NotificationService{
//    NotificationRepository notirepo;
//    SmsNotificationService(NotificationRepository notirepo){
//        this.notirepo = notirepo;
//    }
//    //
//    @Override
//    public void sendNotification(User user, String message) {
//        System.out.println("Sending sms");
//    }
//}
//
//
//// Fcatory pattern
//
//class NotificationTypeFactory{
//
//    public NotificationService getType(String type){
//        if(type.equals("Email")){
//            return new EmailNotificationServiceImpl( new InMemoryNotification());
//        }else if(type.equals("Sms")){
//            return new SmsNotificationService(new InMemoryNotification());
//        }
//        return null;
//    }
//}
//
//
////controller
//
//
//class NotificationController{
//
//    NotificationTypeFactory factory;
//    NotificationController(NotificationTypeFactory factory){
//        this.factory = factory;
//    }
//
//    //
//    public void sendNotification(User user,String message,String type){
//
//        NotificationService notificationserivce =factory.getType(type);
//        notificationserivce.sendNotification(user,message);
//    }
//
//    public static void main(String[] args) {
//        NotificationController  notificationController = new NotificationController(new NotificationTypeFactory());
//        User user = new User(1,"Rahul","abc@example.com","12132423");
//        notificationController.sendNotification(user,"Hi","Email");
//        notificationController.sendNotification(user,"Hi","Sms");
//
//    }
//
//}
//




package org.example.practice.NOTIFICATION;

import java.util.*;

// ======================= ENUMS =======================

enum NotificationType {
    EMAIL,
    SMS,
    PUSH
}

enum NotificationStatus {
    PENDING,
    SENT,
    FAILED
}

// ======================= ENTITIES =======================

class User {
    private int id;
    private String name;
    private String email;
    private String mobileNumber;

    public User(int id, String name, String email, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getMobileNumber() { return mobileNumber; }
}

class Notification {
    private int id;
    private User user;
    private NotificationType type;
    private NotificationStatus status;
    private String message;

    public Notification(User user, NotificationType type, NotificationStatus status, String message) {
        this.user = user;
        this.type = type;
        this.status = status;
        this.message = message;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}

// ======================= REPOSITORIES =======================

interface NotificationRepository {
    void save(Notification notification);
    Notification findById(int id);
}

class InMemoryNotificationRepository implements NotificationRepository {

    private final Map<Integer, Notification> store = new HashMap<>();
    private int idCounter = 1;

    @Override
    public void save(Notification notification) {
        notification.setId(idCounter++);
        store.put(notification.getId(), notification);
    }

    @Override
    public Notification findById(int id) {
        return store.get(id);
    }
}

// ======================= SERVICES =======================

interface NotificationService {
    void send(User user, String message);
}

class EmailNotificationService implements NotificationService {

    private final NotificationRepository repository;

    public EmailNotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void send(User user, String message) {
        System.out.println("Sending EMAIL to " + user.getEmail());

        Notification notification = new Notification(
                user,
                NotificationType.EMAIL,
                NotificationStatus.SENT,
                message
        );

        repository.save(notification);
    }
}

class SmsNotificationService implements NotificationService {

    private final NotificationRepository repository;

    public SmsNotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void send(User user, String message) {
        System.out.println("Sending SMS to " + user.getMobileNumber());

        Notification notification = new Notification(
                user,
                NotificationType.SMS,
                NotificationStatus.SENT,
                message
        );

        repository.save(notification);
    }
}

class PushNotificationService implements NotificationService {

    private final NotificationRepository repository;

    public PushNotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void send(User user, String message) {
        System.out.println("Sending PUSH notification");

        Notification notification = new Notification(
                user,
                NotificationType.PUSH,
                NotificationStatus.SENT,
                message
        );

        repository.save(notification);
    }
}

// ======================= FACTORY =======================

class NotificationFactory {

    private final Map<NotificationType, NotificationService> serviceMap;

    public NotificationFactory(Map<NotificationType, NotificationService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public NotificationService getService(NotificationType type) {
        NotificationService service = serviceMap.get(type);
        if (service == null) {
            throw new IllegalArgumentException("Unsupported notification type: " + type);
        }
        return service;
    }
}

// ======================= CONTROLLER =======================

class NotificationController {

    private final NotificationFactory factory;

    public NotificationController(NotificationFactory factory) {
        this.factory = factory;
    }

    public void sendNotification(User user, String message, NotificationType type) {
        NotificationService service = factory.getService(type);
        service.send(user, message);
    }

    // ======================= MAIN =======================

    public static void main(String[] args) {

        NotificationRepository repository = new InMemoryNotificationRepository();

        Map<NotificationType, NotificationService> services = new HashMap<>();
        services.put(NotificationType.EMAIL, new EmailNotificationService(repository));
        services.put(NotificationType.SMS, new SmsNotificationService(repository));
        services.put(NotificationType.PUSH, new PushNotificationService(repository));

        NotificationFactory factory = new NotificationFactory(services);
        NotificationController controller = new NotificationController(factory);

        User user = new User(1, "Rahul", "abc@example.com", "9999999999");

        controller.sendNotification(user, "Hello via Email", NotificationType.EMAIL);
        controller.sendNotification(user, "Hello via SMS", NotificationType.SMS);
        controller.sendNotification(user, "Hello via Push", NotificationType.PUSH);
    }
}
