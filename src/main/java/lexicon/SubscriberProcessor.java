package lexicon;

import java.util.ArrayList;
import java.util.List;

public class SubscriberProcessor {

    public List<Subscriber> findSubscribers(List<Subscriber> subscriberList, SubscriberFilter filter) {

        List<Subscriber> filteredList = new ArrayList<>();

        for (Subscriber s : subscriberList) {
            if(filter.matches(s)) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    public List<Subscriber> applyToMatching(
            List<Subscriber> subscriberList, SubscriberFilter filter, SubscriberAction action) {

        List<Subscriber> filteredList = new ArrayList<>();

        for (Subscriber s : subscriberList) {
            if (filter.matches(s)) {
                action.run(s);
                filteredList.add(s);
            }
        }
        return filteredList;
    }
}
