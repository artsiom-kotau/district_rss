package by.roodxx.district.instagram.auth;

import by.roodxx.district.core.auth.BaseCredentials;

public class InstagramCredentials implements BaseCredentials {

    private final String clientId;
    private final String secretId;
    private String backUri;

    public InstagramCredentials(String clientId, String secretId) {
        this.clientId = clientId;
        this.secretId = secretId;
    }

    public InstagramCredentials(String clientId, String secretId, String backUri) {
        this.clientId = clientId;
        this.secretId = secretId;
        this.backUri = backUri;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecretId() {
        return secretId;
    }

    public String getBackUri() {
        return backUri;
    }

    public void setBackUri(String backUri) {
        this.backUri = backUri;
    }
}
