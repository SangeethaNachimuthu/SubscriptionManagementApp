package lexicon;


public class Main {

    private static final SubscriberProcessor processor = new SubscriberProcessor();

    public static SubscriberFilter activeSubscriber = (Subscriber::isActive);
    public static SubscriberFilter expiringSubscription = (subscriber ->
            subscriber.getMonthsRemaining() == 1 || subscriber.getMonthsRemaining() == 0);
    public static SubscriberFilter activeAndExpiringSubscriber = (subscriber ->
            (subscriber.isActive() && (subscriber.getMonthsRemaining() == 1 || subscriber.getMonthsRemaining() == 0)));
    public static SubscriberFilter subscriberByPlan = subscriber ->
            subscriber.getPlan().equals(Plan.FREE);
    public static SubscriberFilter payingSubscriber = subscriber ->
            (subscriber.getPlan().equals(Plan.BASIC) || subscriber.getPlan().equals(Plan.PRO));
    public static SubscriberAction extendSubscription = subscriber ->
            subscriber.setMonthsRemaining(subscriber.getMonthsRemaining() + 1) ;
    public static SubscriberAction deactivateSubscriber = subscriber ->
            subscriber.setActive(false);


    void main() {

        SubscriberDAO dao = new SubscriberDAO();

        dao.save(new Subscriber(1, "abc@gmail.com", true, Plan.BASIC, 5));
        dao.save(new Subscriber(2, "gill@gmail.com", true, Plan.FREE, 5));
        dao.save(new Subscriber(3, "ali@gmail.com", false, Plan.BASIC, 0));
        dao.save(new Subscriber(5, "lily@gmail.com", true, Plan.BASIC, 12));
        dao.save(new Subscriber(31, "ali@gmail.com", false, Plan.FREE, 0));
        dao.save(new Subscriber(4, "paul@gmail.com", true, Plan.PRO, 1));

        for (Subscriber s : dao.findAll()) {
            System.out.println("Subscriber: " + s);
        }

        System.out.println("\n-------------Results----------");
        System.out.println("Active Subscribers:");
        System.out.println(processor.findSubscribers(dao.findAll(), activeSubscriber));
        System.out.println("\nExpiring Subscription: ");
        System.out.println(processor.findSubscribers(dao.findAll(), expiringSubscription));
        System.out.println("\nActive and Expiring Subscriber: ");
        System.out.println(processor.findSubscribers(dao.findAll(), activeAndExpiringSubscriber));
        System.out.println("\nSubscriber by Plan - FREE: ");
        System.out.println(processor.findSubscribers(dao.findAll(), subscriberByPlan));
        System.out.println("\nPaying Subscriber - BASIC or PRO: ");
        System.out.println(processor.findSubscribers(dao.findAll(), payingSubscriber));
        System.out.println("\nExtend Subscription: ");
        System.out.println(processor.applyToMatching(dao.findAll(), subscriberByPlan, extendSubscription));
        System.out.println("\nDeactivate Subscriber: ");
        System.out.println(processor.applyToMatching(dao.findAll(), expiringSubscription, deactivateSubscriber));
    }


}
