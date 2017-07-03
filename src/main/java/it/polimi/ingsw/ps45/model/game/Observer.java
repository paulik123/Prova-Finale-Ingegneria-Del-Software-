package it.polimi.ingsw.ps45.model.game;

import java.io.IOException;

/**
 * Interface used to define a notifier.
 */
public interface Observer {
	/**
	 * @param json the serialized message the observer gets notified with.
	 * @throws IOException if the method can't properly deserialize the message.
	 */
	public void notify(String json) throws IOException;
}
