package com.github.aceroni75.gwtboot.client.util;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class Places {
    public static Getter from(PlaceRequest request) {
        return new Getter(request);
    }

    public static Revealer using(PlaceManager placeManager) {
        return new Revealer(placeManager);
    }

    public static class Getter {
        private final PlaceRequest request;

        private Getter(PlaceRequest request) {
            this.request = request;
        }

        public Possible<String> get(String key) {
            String value = request.getParameter(key, null);
            return Possible.of(value).filter(Strings::isNotBlank);
        }

        public Possible<Long> getLong(String key) {
            return get(key).transform(Strings::longOrNull);
        }
    }

    public static class Revealer {
        private final PlaceManager placeManager;
        private final PlaceRequest.Builder builder;

        public Revealer(PlaceManager placeManager) {
            this.placeManager = placeManager;
            builder = new PlaceRequest.Builder();
        }

        public Revealer with(String key, Object value) {
            if (value != null) {
                builder.with(key, value.toString());
            }
            return this;
        }

        public void reveal(String nameToken) {
            placeManager.revealPlace(builder.nameToken(nameToken).build());
        }
    }
}
