package com.todoapk.data.source.session;

import android.content.Context;
import android.content.SharedPreferences;

public interface SessionRepository<T> {
    public final String SHARED_PREFERENCE_NAME = "SessionSharedPreferences";

    T initialize(T sessionData);
    T getSessionData();
    void setSessionData(T sessionData);
    void destroy();
    void update(T sessionData);
}
