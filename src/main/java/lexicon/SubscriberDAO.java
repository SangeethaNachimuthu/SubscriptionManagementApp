package lexicon;

import java.util.ArrayList;
import java.util.List;

public class SubscriberDAO {
// define a list or collection
    private final List<Subscriber> storage = new ArrayList<>();

    public void save(Subscriber subscriber) {

        storage.add(subscriber);
    }

    public List<Subscriber> findAll() {

        return storage;
    }

    public void findById(int id) {

        for (Subscriber subscriber : storage) {
            if (subscriber.getId() == id) {
                System.out.println(subscriber.toString());
            }
        }
    }
}
