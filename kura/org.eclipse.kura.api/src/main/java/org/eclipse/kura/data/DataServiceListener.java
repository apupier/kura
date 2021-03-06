/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech
 *******************************************************************************/
package org.eclipse.kura.data;

/**
 * Implementors of this interface will be able to handle {@link DataService}
 * events such as notifications of connection establishing, message arrival, etc.
 * In order to detect implementors, the {@link DataService} uses the The
 * whiteboard pattern {@link http://www.osgi.org/wiki/uploads/Links/whiteboard.pdf}.
 * <br>
 * All registered listeners are called synchronously by the {@link DataService} at the occurrence of the event.
 * It expected that implementers of this interface do NOT perform long running tasks in the implementation of this
 * interface.
 *
 * @deprecated As of {@link org.eclipse.kura.data} 1.1.0, use
 *             {@link DataService#addDataServiceListener(org.eclipse.kura.data.listener.DataServiceListener)}
 *             to register a listener to a DataService.
 */
@Deprecated
public interface DataServiceListener {

    /**
     * Notifies that the DataService has established a connection.
     */
    public void onConnectionEstablished();

    /**
     * Notifies that the DataService is about to cleanly disconnect. If
     * something needs to be done in reaction to this event, e.g. publishing a
     * special last message, it SHOULD be done before the method returns.
     * As soon as the method returns the DataService will start disconnecting.
     */
    public void onDisconnecting();

    /**
     * Notifies that the DataService has cleanly disconnected.
     */
    public void onDisconnected();

    /**
     * Notifies that the DataService has unexpectedly disconnected.
     */
    public void onConnectionLost(Throwable cause);

    /**
     * Notifies a message arrival.
     *
     * @param topic
     * @param payload
     * @param qos
     * @param retained
     */
    public void onMessageArrived(String topic, byte[] payload, int qos, boolean retained);

    /**
     * Notifies the a message has been published. There is no guarantee the
     * message has been actually transmitted over the wire or that it eventually
     * will. The only guarantee is that message byte have been passed to the
     * underlying OS.
     *
     * @param messageId
     */
    public void onMessagePublished(int messageId, String topic);

    /**
     * Confirms message delivery to the broker.
     *
     * @param messageId
     */
    public void onMessageConfirmed(int messageId, String topic);
}
