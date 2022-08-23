package org.igogrzeg.recipes.security;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Component
public class SessionRegistry {

    private static final HashMap<String, String> SESSIONS = new HashMap<>();

    public String registerSession(final String email) {
        if (email == null) {
            throw new RuntimeException("Email needs to be provided !! ");
        }
        final String sessionId = generateSessionId();
        SESSIONS.put(sessionId, email);
        return sessionId;
    }

    public String getUsernameForSession(final String sessionId) {
        return SESSIONS.get(sessionId);
    }

    public String generateSessionId() {
        return new String(
                Base64.getEncoder().encode(
                        UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
                )
        );
    }

}
