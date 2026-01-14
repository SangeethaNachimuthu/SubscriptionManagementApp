package lexicon;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static SubscriberFilter activeSubscriber = (subscriber -> subscriber.isActive());
    public static SubscriberFilter inactiveSubscriber = (subscriber -> !subscriber.isActive());

    void main() {

        List<Subscriber> subscriberList = new ArrayList<>();

        subscriberList.add(new Subscriber(1, "abc@gmail.com", true, Plan.BASIC, 5));
        subscriberList.add(new Subscriber(2, "glr@gmail.com", false, Plan.FREE, 8));
        subscriberList.add(new Subscriber(3, "ali@gmail.com", false, Plan.BASIC, 5));
        subscriberList.add(new Subscriber(4, "paul@gmail.com", true, Plan.PRO, 1));
        subscriberList.add(new Subscriber(5, "lily@gmail.com", true, Plan.BASIC, 12));

        for (Subscriber s : subscriberList) {
            System.out.println("Subscriber: " + s);
        }

        System.out.println("-------------Results----------");
        System.out.println("Active Subscribers:");
        System.out.println(findSubscriber(subscriberList, activeSubscriber));
    }

    public static List<Subscriber> findSubscriber(List<Subscriber> subscriberList, SubscriberFilter filter) {

        List<Subscriber> filteredList = new ArrayList<>();

        for (Subscriber s : subscriberList) {
            if(filter.matches(s)) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

}
