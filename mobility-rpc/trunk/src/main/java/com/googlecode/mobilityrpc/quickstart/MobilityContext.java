/**
 * Copyright 2011 Niall Gallagher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.mobilityrpc.quickstart;

import com.googlecode.mobilityrpc.network.ConnectionId;
import com.googlecode.mobilityrpc.session.MobilitySession;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Niall Gallagher
 */
public class MobilityContext {

    private static final ThreadLocal<MobilitySession> threadLocalSessions = new ThreadLocal<MobilitySession>();

    public static void setCurrentSession(MobilitySession session) {
        threadLocalSessions.set(session);
    }
    public static MobilitySession getCurrentSession() {
        MobilitySession currentSession = threadLocalSessions.get();
        if (currentSession == null) {
            throw new IllegalStateException("No current session");
        }
        return currentSession;
    }

    private static final ThreadLocal<ConnectionId> threadLocalConnectionIds = new ThreadLocal<ConnectionId>();

    public static void setConnectionId(ConnectionId connectionId) {
        threadLocalConnectionIds.set(connectionId);
    }
    public static ConnectionId getCurrentConnectionId() {
        ConnectionId currentConnectionId = threadLocalConnectionIds.get();
        if (currentConnectionId == null) {
            throw new IllegalStateException("No current connection id");
        }
        return currentConnectionId;
    }

    private static final Map<Class<?>, Object> singletonObjectRegistry = new ConcurrentHashMap<Class<?>, Object>();

    public static <C, T extends C> void setSingletonObject(Class<C> cls, T object) {
        singletonObjectRegistry.put(cls, object);
    }

    public static <C, T extends C> T getSingletonObject(Class<C> cls) {
        @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
        T result = (T) singletonObjectRegistry.get(cls);
        return result;
    }

    private static final Map<UUID, Object> uuidObjectRegistry = new ConcurrentHashMap<UUID, Object>();

    public static void setUuidObject(UUID uuid, Object object) {
        uuidObjectRegistry.put(uuid, object);
    }

    public static Object getUuidObject(UUID uuid) {
        return uuidObjectRegistry.get(uuid);
    }
}
