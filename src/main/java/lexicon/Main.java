package lexicon;

import java.util.ArrayList;
import java.util.List;

public class Main {

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

    private static final SubscriberProcessor processor = new SubscriberProcessor();

    void main() {

        List<Subscriber> subscriberList = new ArrayList<>();

        subscriberList.add(new Subscriber(1, "abc@gmail.com", true, Plan.BASIC, 5));
        subscriberList.add(new Subscriber(2, "glr@gmail.com", false, Plan.FREE, 8));
        subscriberList.add(new Subscriber(3, "ali@gmail.com", false, Plan.BASIC, 0));
        subscriberList.add(new Subscriber(4, "paul@gmail.com", true, Plan.PRO, 1));
        subscriberList.add(new Subscriber(5, "lily@gmail.com", true, Plan.BASIC, 12));
        subscriberList.add(new Subscriber(31, "ali@gmail.com", false, Plan.FREE, 0));

        for (Subscriber s : subscriberList) {
            System.out.println("Subscriber: " + s);
        }

        System.out.println("\n-------------Results----------");
        System.out.println("Active Subscribers:");
        System.out.println(processor.findSubscribers(subscriberList, activeSubscriber));
        System.out.println("\nExpiring Subscription: ");
        System.out.println(processor.findSubscribers(subscriberList, expiringSubscription));
        System.out.println("\nActive and Expiring Subscriber: ");
        System.out.println(processor.findSubscribers(subscriberList, activeAndExpiringSubscriber));
        System.out.println("\nSubscriber by Plan - FREE: ");
        System.out.println(processor.findSubscribers(subscriberList, subscriberByPlan));
        System.out.println("\nPaying Subscriber - BASIC or PRO: ");
        System.out.println(processor.findSubscribers(subscriberList, payingSubscriber));
        System.out.println("\nExtend Subscription: ");
        System.out.println(processor.applyToMatching(subscriberList, subscriberByPlan, extendSubscription));
        System.out.println("\nDeactivate Subscriber: ");
        System.out.println(processor.applyToMatching(subscriberList, expiringSubscription, deactivateSubscriber));
    }


}
