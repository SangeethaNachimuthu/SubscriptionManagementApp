package lexicon;

@FunctionalInterface
public interface SubscriberAction {

    public void run(Subscriber subscriber);
}
