package barber.shop;

/**
 * Interface that allows to store information about the last state
 * of the customers' timetable and recover from tha state.
 */
public interface Serialize {
	void serializeCustomer();

	void serializeTime();

	void serializePlace();

	void terminate();
}
