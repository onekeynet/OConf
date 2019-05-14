/*
 * Copyright 2015-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basis for components which need to export listener mechanism.
 */
public abstract class AbstractListenerManager<E extends Event, L extends EventListener<E>>
        implements ListenerService<E, L> {

    private final ListenerRegistry<E, L> listenerRegistry = new ListenerRegistry<>();
    protected static final Logger log = LoggerFactory.getLogger(AbstractListenerManager.class);

    @Override
    public void addListener(L listener) {
        listenerRegistry.addListener(listener);
    }

    @Override
    public void removeListener(L listener) {
        listenerRegistry.removeListener(listener);
    }


    /**
     * Safely posts the specified event to the local event dispatcher.
     * If there is no event dispatcher or if the event is null, this method
     * is a noop.
     *
     * @param event event to be posted; may be null
     */
    protected void post(E event) {
        if (event != null) {
            for (EventListener el : listenerRegistry.listeners) {
                el.event(event);
            }
        } else {
            log.info("Nothing to post because the event is null.");
        }
    }

}
