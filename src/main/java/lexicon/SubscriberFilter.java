package lexicon;

@FunctionalInterface
public interface SubscriberFilter {

    public boolean matches(Subscriber subscriber);
}
